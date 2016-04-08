package hu.gdf.oop.fogadoiroda.gui;

import hu.gdf.oop.fogadoiroda.data.entity.User;
import hu.gdf.oop.fogadoiroda.data.repository.BetEventRepository;
import hu.gdf.oop.fogadoiroda.data.repository.UserRepository;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private List<User> list;
    private final UserRepository repository = new UserRepository();
    private final BetEventRepository betEventRepository = new BetEventRepository();
    private int sortColumnIndex = -1;
    private boolean asc = true;

    public void loadData() {
        this.list = repository.findAll();
    }

    public void sortData(int columnIndex) {
        asc = columnIndex == sortColumnIndex ? !asc : true;
        sortColumnIndex = columnIndex;
        switch (columnIndex) {
            case 0:
                list.sort((o1, o2) -> comp(o1.getId(), o2.getId(), asc));
                break;
            case 1:
                list.sort((o1, o2) -> comp(o1.getUsername(), o2.getUsername(), asc));
                break;
            case 3:
                list.sort((o1, o2) -> comp(o1.getEmail(), o2.getEmail(), asc));
                break;
            case 4:
                list.sort((o1, o2) -> comp(o1.getAuthority(), o2.getAuthority(), asc));
                break;
            case 5:
                list.sort((o1, o2) -> comp(o1.getBalance(), o2.getBalance(), asc));
                break;
            case 6:
                list.sort((o1, o2) -> comp(o1.getCreated(), o2.getCreated(), asc));
                break;
            case 2:
            case 7:
            case 8:
                // jelszóra, státuszra és dirty flagre nem rendezünk
        }
    }

    private <T extends Comparable> int comp(T first, T second, boolean asc) {
        if (first == null) {
            return second == null ? 0 : asc ? 1 : -1;
        } else {
            return second == null ? asc ? -1 : 1 : asc ? first.compareTo(second) : second.compareTo(first);
        }
    }

    @Override
    public int getRowCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User selected = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return selected.getId();
            case 1:
                return selected.getUsername();
            case 2:
                return selected.getPassword();
            case 3:
                return selected.getEmail();
            case 4:
                return selected.getAuthority();
            case 5:
                return selected.getBalance();
            case 6:
                return selected.getCreated();
            case 7:
                return selected.isActive();
            case 8:
                return selected.isDirty();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 5:
                return Integer.class;
            case 6:
                return LocalDateTime.class;
            case 7:
            case 8:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Felhasználónév";
            case 2:
                return "Jelszó";
            case 3:
                return "Email";
            case 4:
                return "Jogosultság";
            case 5:
                return "Egyenleg";
            case 6:
                return "Létrehozva";
            case 7:
                return "Aktív-e";
            case 8:
                return "Változott-e";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        User selected = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                selected.setUsername((String) value);
                break;
            case 2:
                selected.setPassword((String) value);
                break;
            case 3:
                selected.setEmail((String) value);
                break;
            case 4:
                selected.setAuthority((String) value);
                break;
            case 5:
                selected.setBalance((Integer) value);
        }
        selected.setDirty(true);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addRow() {
        User user = new User();
        list.add(user);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void deleteRow(int rowIndex) {
        User selected = list.get(rowIndex);
        if (selected.getId() != null) {
            if (selected.equals(ApplicationGUI.loggedInUser)) {
                throw new ApplicationException("Saját maga nem törölhető!");
            }
            if ("OPERATOR".equals(selected.getAuthority()) && countOperators() == 1) {
                throw new ApplicationException("Az utolsó operátor nem törölhető!");
            }
            if (betEventRepository.countByUser(selected) > 0) {
                throw new ApplicationException("A felhasználó már hozott létre eseményt, ezért nem törölhető!");
            }
            User user = repository.findOne(selected.getId());
            if (user != null) {
                repository.delete(user);
            }
        }
        list.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void inactivateRow(int rowIndex) {
        User selected = list.get(rowIndex);
        selected.setActive(false);
        selected.setDirty(true);
        fireTableCellUpdated(rowIndex, 8);
    }

    public void activateRow(int rowIndex) {
        User selected = list.get(rowIndex);
        selected.setActive(true);
        selected.setDirty(true);
        fireTableCellUpdated(rowIndex, 8);
    }

    public void saveRows() {
        if (list != null && !list.isEmpty()) {
            list.stream().filter(u -> u.isDirty()).forEach(u -> {
                if (u.getId() == null) {
                    if (u.getPassword() == null) u.setPassword("123456");
                    if (u.getAuthority() == null) u.setAuthority("USER");
                    if (u.getBalance() == null) u.setBalance(0);
                    repository.create(u);
                } else {
                    repository.update(u);
                }
            });
        }
    }

    public boolean isActive(int rowNumber) {
        return list.get(rowNumber).isActive();
    }

    public boolean isNew(int rowNumber) {
        return list.get(rowNumber).getId() == null;
    }

    private long countOperators() {
        return list.stream().filter(u -> "OPERATOR".equals(u.getAuthority())).count();
    }

}

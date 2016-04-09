/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gdf.oop.fogadoiroda.gui;

import hu.gdf.oop.fogadoiroda.data.entity.BetEvent;
import hu.gdf.oop.fogadoiroda.data.entity.Outcome;
import hu.gdf.oop.fogadoiroda.data.repository.OutcomeRepository;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class OutcomeTableModel extends AbstractTableModel {

    private BetEvent parent;
    private List<Outcome> list;
    private OutcomeRepository repository = new OutcomeRepository();

    public void loadData(BetEvent event) {
        list = repository.findWithBetEventId(event.getId());
        parent = event;
    }

    public void emptyTable() {
        list = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        Outcome selected = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                result = selected.getId();
                break;
            case 1:
                result = selected.getTitle();
                break;
            case 2:
                result = selected.isWon();
                break;
            case 3:
                result = selected.getSumBetAmount();
                break;
            case 4:
                result = selected.isDirty();
        }
        return result;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String result = null;
        switch (columnIndex) {
            case 0:
                result = "ID";
                break;
            case 1:
                result = "Megnevezés";
                break;
            case 2:
                result = "Nyert";
                break;
            case 3:
                result = "Összes fogadás";
                break;
            case 4:
                result = "Változott";
        }
        return result;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> result;
        switch (columnIndex) {
            case 0:
            case 3:
                result = Integer.class;
                break;
            case 2:
            case 4:
                result = Boolean.class;
                break;
            default:
                result = String.class;
        }

        return result;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        boolean result = false;
        switch (columnIndex) {
            case 1:
            case 2:
                result = true;
                break;
        }

        return result;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Outcome selected = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                selected.setTitle((String) value);
                break;
            case 2:
                selected.setWon((boolean) value);
                break;
        }
        selected.setDirty(true);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addRow() {
        Outcome outcome = new Outcome();
        list.add(outcome);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void deleteRow(int rowIndex, BetEventsTreeModel treeModel) {
        Outcome selected = list.get(rowIndex);
        delete(selected);
        list.remove(rowIndex);
        treeModel.removeNode(selected);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void deleteAll(BetEventsTreeModel treeModel) {
        list.forEach(o -> {
            delete(o);
            treeModel.removeNode(o);
        });
        list.clear();
    }

    private void delete(Outcome outcome) {
        if (outcome.getId() != null) {
            if (parent.getStatus() == 1 || parent.getStatus() == 2) {
                throw new ApplicationException("Az esemény nyilvános, ezért nem módosítható!");
            }
            Outcome o = repository.findOne(outcome.getId());
            if (o != null) {
                repository.delete(o);
            }
        }
    }

    public void saveRows(BetEventsTreeModel treeModel) {
        if (list != null && !list.isEmpty()) {
            list.stream().filter(o -> o.isDirty()).forEach(o -> {
                if (o.getId() == null) {
                    if (o.getBetEventId() == null) {
                        o.setBetEventId(parent.getId());
                    }
                    if (o.getTitle() == null) {
                        o.setTitle("Kimenetel");
                    }
                    if (o.getSumBetAmount() == null) {
                        o.setSumBetAmount(0);
                    }
                    repository.create(o);
                    treeModel.createNode(parent, o);
                } else {
                    repository.update(o);
                    treeModel.setNodeTitle(o, o.getTitle());
                }
            });
        }
    }

}

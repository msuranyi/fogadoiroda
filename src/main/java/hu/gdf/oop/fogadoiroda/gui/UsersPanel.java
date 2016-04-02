package hu.gdf.oop.fogadoiroda.gui;

import hu.gdf.oop.fogadoiroda.data.entity.User;
import hu.gdf.oop.fogadoiroda.data.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsersPanel extends JPanel implements ActionListener{


    String[] columnNames = {"ID",
            "Felhasználónév",
            "Jelszó",
            "Email",
            "Jogosultságok",
            "Státusz",
            "Egyenleg",
            "Létrehozva"
    };

    String[] editableColumns = {
            "Felhasználónév",
            "Jelszó",
            "Email",
            "Jogosultságok",
            "Státusz"
    };

    UserRepository userRepository = new UserRepository();
    DataTable userTable;
    JTable table;
    JScrollPane scrollPane;

    JPanel controlPanel;
    JButton btnSaveData;
    JButton btnAddRow;
    JButton btnDeleteRow;


    public UsersPanel() {
        setTableData();
        initComponents();
    }

    private void setTableData(){
        List<User> userList = userRepository.findAll();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        for(int i = 0; i<userList.size();i++){
            data.add(userList.get(i).toArray());
        }
        userTable = new DataTable(columnNames,data);
        userTable.setEditableColumns(editableColumns);
    }

    private void initComponents(){

        table = new JTable(userTable);
        table.setDefaultRenderer(Object.class, new DataTableRenderer());
        table.setDefaultRenderer(Integer.class, new DataTableRenderer());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(table);

        controlPanel = new JPanel();
        btnSaveData = new JButton("Változások mentése");
        btnAddRow = new JButton("Felhasználó hozzáadása");
        btnDeleteRow = new JButton("Felhasználó törlése");
        btnSaveData.addActionListener(this);
        btnAddRow.addActionListener(this);
        btnDeleteRow.addActionListener(this);
        controlPanel.add(btnSaveData);
        controlPanel.add(btnAddRow);
        controlPanel.add(btnDeleteRow);

        this.setLayout(new BorderLayout());
        this.add(table.getTableHeader(), BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(controlPanel,BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src.equals(btnAddRow)) {
            addRow();
        }else
        if(src.equals(btnDeleteRow)){
            deleteRow();
        }else
        if(src.equals(btnSaveData)){
            saveData();
        }
    }

    private void addRow(){
        userTable.addRow(User.getEmptyModel());
        this.repaint();
    }

    private void deleteRow(){
        userTable.deleteRow(table.getSelectedRow());
    }

    private void saveData(){
        ArrayList<Object[]> data = userTable.data;
        for(int i=0; i<userTable.columnStatus.size();i++){
            DataTable.ColumnStatus status = userTable.columnStatus.get(i);
            boolean change = (status == DataTable.ColumnStatus.CHANGED || status == DataTable.ColumnStatus.DISABLED);
            if(change){
                User user = userRepository.findOne((int)data.get(i)[0]);
                if(status == DataTable.ColumnStatus.DISABLED){
                    userRepository.delete(user);
                }else{
                    if(user == null){
                        user = new User();
                        user.setUsername((String)data.get(i)[1]);
                        user.setPassword((String) data.get(i)[2]);
                        user.setEmail((String) data.get(i)[3]);
                        user.setAuthority((String) data.get(i)[4]);
                        user.setActive((boolean) data.get(i)[5]);
                        userRepository.create(user);
                    }else{
                        user.setUsername((String)data.get(i)[1]);
                        user.setPassword((String) data.get(i)[2]);
                        user.setEmail((String) data.get(i)[3]);
                        user.setAuthority((String) data.get(i)[4]);
                        user.setActive((boolean) data.get(i)[5]);
                        userRepository.update(user);
                    }
                }
            }
        }
    }
}

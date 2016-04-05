/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gdf.oop.fogadoiroda.gui;

import hu.gdf.oop.fogadoiroda.data.entity.User;
import hu.gdf.oop.fogadoiroda.data.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JPasswordField;

/**
 *
 * @author marci
 */
public class UsersPanel extends javax.swing.JPanel {

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
            "ID",
            "Felhasználónév",
            "Jelszó",
            "Email",
            "Jogosultságok",
            "Státusz",
            "Egyenleg"
    };
    
    UserRepository userRepository = new UserRepository();
    DataTable userTable;
    
    private void setTableData(){
        List<User> userList = userRepository.findAll();
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        for(int i = 0; i<userList.size();i++){
            data.add(userList.get(i).toArray());
        }
        userTable = new DataTable(columnNames,data);
        userTable.setEditableColumns(editableColumns);
    }
    private void addRow(){
        userTable.addRow(User.getEmptyModel());
        table.revalidate();
        table.repaint();
    }

    private void deleteRow(){
        userTable.deleteRow(table.getSelectedRow());
        table.revalidate();
        table.repaint();
    }

    private void saveData(){
        ArrayList<Object[]> data = userTable.data;
        for(int i=0; i<userTable.rowStatus.size();i++){
            DataTable.RowStatus status = userTable.rowStatus.get(i);
            boolean change = (status == DataTable.RowStatus.CHANGED || status == DataTable.RowStatus.DISABLED);
            if(change){
                try{
                    int userId = (int)(data.get(i)[0]);
                User user = userRepository.findOne(userId);
                if(status == DataTable.RowStatus.DISABLED){
                    userRepository.delete(user);
                }else{
                    user.setUsername((String)data.get(i)[1]);
                    user.setPassword((String) data.get(i)[2]);
                    user.setEmail((String) data.get(i)[3]);
                    user.setAuthority((String) data.get(i)[4]);
                    user.setActive((boolean) data.get(i)[5]);
                    user.setBalance((int)data.get(i)[6]);
                    userRepository.update(user);
                }
                }catch(NullPointerException e){
                    if(status == DataTable.RowStatus.CHANGED){
                        User user = new User();
                        user.setUsername((String)data.get(i)[1]);
                        user.setPassword((String) data.get(i)[2]);
                        user.setEmail((String) data.get(i)[3]);
                        user.setAuthority((String) data.get(i)[4]);
                        user.setActive((boolean) data.get(i)[5]);
                        user.setBalance((int)data.get(i)[6]);
                        user.setCreated(LocalDateTime.now());
                        userRepository.create(user);
                    }
                }
            }
        }
        userTable.resetRowStatus();
    }
    
    /**
     * Creates new form UsersPanel
     */
    public UsersPanel() {
        setTableData();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        controlPanel = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        table.setModel(userTable);
        JPasswordField pwf = new JPasswordField();
        DefaultCellEditor editor = new DefaultCellEditor(pwf);
        table.getColumnModel().getColumn(2).setCellEditor(editor);
        PasswordCellRenderer pcr = new PasswordCellRenderer();
        table.getColumnModel().getColumn(2).setCellRenderer(pcr);
        jScrollPane1.setViewportView(table);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnSave.setText("Mentés");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCreate.setText("Létrehozás");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setText("Törlés");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCreate)
                    .addComponent(btnDelete))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        add(controlPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       saveData();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        addRow();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteRow();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UsersPanel extends javax.swing.JPanel {

    private ApplicationCallback callback;
    private UserTableModel userTable;

    public void loadData() {
        userTable.loadData();
        table.revalidate();
        table.repaint();
    }

    private void setTableData() {
        userTable = new UserTableModel();
    }

    private void addTableListeners() {
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow > -1 && table.getSelectedRowCount() == 1 && !userTable.isSameUser(selectedRow)) {
                btnDelete.setEnabled(true);
                boolean active = userTable.isActive(selectedRow);
                btnActivate.setEnabled(!active);
                btnInactivate.setEnabled(active);
            } else {
                btnDelete.setEnabled(false);
                btnActivate.setEnabled(false);
                btnInactivate.setEnabled(false);
            }
        });
        userTable.addTableModelListener((TableModelEvent e) -> {
            if (TableModelEvent.UPDATE == e.getType()) {
                btnSave.setEnabled(true);
                table.repaint();
            }
        });
    }

    private void addTableHeaderListeners() {
        table.getTableHeader().addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    int columnIndex = table.columnAtPoint(e.getPoint());
                    userTable.sortData(columnIndex);
                }
            }
        });
    }

    private void addRow() {
        userTable.addRow();
        table.revalidate();
        table.repaint();
    }

    private void deleteRow() {
        if (table.getSelectedRow() == -1) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Biztosan törölni szeretné a felhasználót?", "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            disableButtons();
            callback.startProgressBar();
            new SwingWorker<Integer, Void>() {
                @Override
                protected Integer doInBackground() throws Exception {
                    try {
                        userTable.deleteRow(table.getSelectedRow());
                        callback.showNotification("A felhasználó törlése sikeresen megtörtént.");
                    } catch (ApplicationException ex) {
                        callback.showWarning(ex.getMessage());
                    }
                    restoreButtons();
                    return 1;
                }

                @Override
                protected void done() {
                    callback.stopProgressBar();
                }
            }.execute();
        }
    }

    private void inactivateRow() {
        if (table.getSelectedRow() != -1) {
            userTable.inactivateRow(table.getSelectedRow());
            btnActivate.setEnabled(true);
            btnInactivate.setEnabled(false);
            callback.showNotification("A felhasználó inaktiválása sikeresen megtörtént.");
        }
    }

    private void activateRow() {
        if (table.getSelectedRow() != -1) {
            userTable.activateRow(table.getSelectedRow());
            btnActivate.setEnabled(false);
            btnInactivate.setEnabled(true);
            callback.showNotification("A felhasználó aktiválása sikeresen megtörtént.");
        }
    }

    private void saveData() {

        new SwingWorker<Integer, Void>() {

            @Override
            protected Integer doInBackground() throws Exception {
                disableButtons();
                callback.startProgressBar();
                try {
                    userTable.saveRows();
                    restoreButtons();
                    loadData();
                    callback.showNotification("Az adatok mentése sikeresen megtörtént.");
                } catch (ApplicationException ex) {
                    callback.showWarning(ex.getMessage());                    
                }
                return 1;
            }

            @Override
            protected void done() {
                callback.stopProgressBar();
            }
        }.execute();
    }

    private void disableButtons() {
        btnSave.setEnabled(false);
        btnCreate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnActivate.setEnabled(false);
        btnInactivate.setEnabled(false);
    }
    
    private void restoreButtons() {
        btnCreate.setEnabled(true);
        System.out.println(table.getSelectedRow());
        if (table.getSelectedRowCount() == 1) {
            btnDelete.setEnabled(true);
            boolean active = userTable.isActive(table.getSelectedRow());
            btnActivate.setEnabled(!active);
            btnInactivate.setEnabled(active);            
        }
    }

    /**
     * Creates new form UsersPanel
     */
    public UsersPanel() {
        setTableData();
        initComponents();
        addTableListeners();
        addTableHeaderListeners();
    }

    public UsersPanel(ApplicationCallback applicationCallback) {
        setTableData();
        initComponents();
        addTableListeners();
        addTableHeaderListeners();
        this.callback = applicationCallback;
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
        btnInactivate = new javax.swing.JButton();
        btnActivate = new javax.swing.JButton();

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 300));

        table.setModel(userTable);
        JPasswordField pwf = new JPasswordField();
        DefaultCellEditor editor = new DefaultCellEditor(pwf);
        table.getColumnModel().getColumn(2).setCellEditor(editor);
        PasswordCellRenderer pcr = new PasswordCellRenderer();
        table.getColumnModel().getColumn(2).setCellRenderer(pcr);
        jScrollPane1.setViewportView(table);

        btnSave.setText("Mentés");
        btnSave.setEnabled(false);
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
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnInactivate.setText("Inaktiválás");
        btnInactivate.setToolTipText("");
        btnInactivate.setEnabled(false);
        btnInactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInactivateActionPerformed(evt);
            }
        });

        btnActivate.setText("Aktiválás");
        btnActivate.setEnabled(false);
        btnActivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivateActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInactivate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActivate)
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCreate)
                    .addComponent(btnDelete)
                    .addComponent(btnInactivate)
                    .addComponent(btnActivate))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
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

    private void btnInactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInactivateActionPerformed
        inactivateRow();
    }//GEN-LAST:event_btnInactivateActionPerformed

    private void btnActivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivateActionPerformed
        activateRow();
    }//GEN-LAST:event_btnActivateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivate;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInactivate;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

package hu.gdf.oop.fogadoiroda.gui;

import hu.gdf.oop.fogadoiroda.data.entity.BetEvent;
import hu.gdf.oop.fogadoiroda.data.entity.Outcome;
import hu.gdf.oop.fogadoiroda.data.repository.BetEventRepository;
import hu.gdf.oop.fogadoiroda.data.repository.OutcomeRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class BetEventsPanel extends javax.swing.JPanel {

    String[] betEventStatus = {
        "Nem publikus",
        "Nyitott",
        "Lezárt"
    };
    
    BetEventRepository eventRepository = new BetEventRepository();
    OutcomeRepository outcomeRepository = new OutcomeRepository();
    List<BetEvent> events;
    BetEventsTreeModel treeModel;
    OutcomeTableModel outcomeTable;
    private ApplicationCallback callback;
        
    private void setTableData(){
        outcomeTable = new OutcomeTableModel();
    }
    
    private void fillOutcomeTable(BetEvent event){
        outcomeTable.loadData(event);
        table.revalidate();
        table.repaint();
    }
    
    public void loadData(){
        treeModel = new BetEventsTreeModel(new DefaultMutableTreeNode());
        treeModel.loadData(tree);
    }
    
    private void resetFields(){
        txtId.setText("");
        txtUserId.setText("");
        txtTitle.setText("");
        txtCreated.setText("");
        cbStatus.setSelectedIndex(0);
        outcomeTable.emptyTable();
        table.revalidate();
        table.repaint();
        disableButtons();
    }
    
    private void addOutcome(){
        outcomeTable.addRow();
        table.revalidate();
        table.repaint();
    }
    
    private void deleteOutcome(){
        if (table.getSelectedRow() == -1) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Biztosan törölni szeretné a kimenetelt?", "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new SwingWorker<Integer, Void>() {
                @Override
                protected Integer doInBackground() throws Exception {
                    disableButtons();
                    callback.startProgressBar();
                    try {
                        outcomeTable.deleteRow(table.getSelectedRow(),treeModel);
                        callback.showNotification("A kimenetel törlése sikeresen megtörtént.");
                    } catch (ApplicationException ex) {
                        callback.showWarning(ex.getMessage());
                    }
                    restoreButtons();
                    table.revalidate();
                    table.repaint();
                    return 1;
                }

                @Override
                protected void done() {
                    callback.stopProgressBar();
                }
            }.execute();
        }
    }
    
    private void saveEvent(){
        BetEvent event = treeModel.getSelectedBetEvent();
        if(event == null) return;
        if(event.getId() == -1){
            event.setUserId(Integer.parseInt(txtUserId.getText()));
            event.setTitle(txtTitle.getText());
            event.setStatus(cbStatus.getSelectedIndex());
            event.setCreated(LocalDateTime.now());
            eventRepository.create(event);
        }else{
            event.setUserId(Integer.parseInt(txtUserId.getText()));
            event.setTitle(txtTitle.getText());
            event.setStatus(cbStatus.getSelectedIndex());
            eventRepository.update(event);
        }
        
        treeModel.setNodeTitle(event, event.getTitle());
        
        outcomeTable.saveRows(treeModel);
        
        tree.repaint();
    }
    
    private void deleteEvent(){
        int confirm;
        BetEvent event = treeModel.getSelectedBetEvent();
        if(event == null) return;
        if(outcomeRepository.findWithBetEventId(event.getId()).size() > 0){
            confirm = JOptionPane.showConfirmDialog(null, "Biztosan törölni szeretnéd? \r\n Az eseményhez tartozó kimenetelek is törlésre kerülnek.", "Megerősítés",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Biztos törölni szeretnéd?", "Megerősítés",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
        if (confirm == JOptionPane.YES_OPTION) {
            new SwingWorker<Integer, Void>() {
                @Override
                protected Integer doInBackground() throws Exception {
                    disableButtons();
                    callback.startProgressBar();
                    try {
                            while(outcomeTable.getRowCount() > 0){
                                outcomeTable.deleteRow(outcomeTable.getRowCount()-1,treeModel);
                            }
                            treeModel.removeNode(event);
                            eventRepository.delete(event);

                            resetFields();
                        callback.showNotification("Az esemény törlése sikeresen megtörtént.");
                    } catch (ApplicationException ex) {
                        callback.showWarning(ex.getMessage());
                    }
                    restoreButtons();
                    table.revalidate();
                    table.repaint();
                    return 1;
                }

                @Override
                protected void done() {
                    callback.stopProgressBar();
                }
            }.execute();
        }
    }
    
    private void addEvent(){
        BetEvent event = new BetEvent();
        event.setId(-1);
        event.setUserId(ApplicationGUI.loggedInUser.getId());
        event.setTitle("");
        event.setStatus(0);
        event.setCreated(LocalDateTime.now());
        treeModel.createNode(treeModel.getRoot(), event);
    }
    
    private void disableButtons(){
        allowControls(false);        
    }
    
    private void restoreButtons(){
        allowControls(true);
    }
    
    private void allowControls(boolean state){
        btnSaveEvent.setEnabled(state);
        btnDeleteEvent.setEnabled(state);
        btnCreateOutcome.setEnabled(state);
        btnDeleteOutcome.setEnabled(state);
    }
    
    /**
     * Creates new form BetEventsPanel
     */
    public BetEventsPanel() {
        setTableData();
        initComponents();
        allowControls(false);
    }

    public BetEventsPanel(ApplicationCallback applicationCallback) {
        setTableData();
        initComponents();
        allowControls(false);
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

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnCreateOutcome = new javax.swing.JButton();
        btnDeleteOutcome = new javax.swing.JButton();
        btnCreateEvent = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        lblUserId = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtUserId = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        lblCreated = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtCreated = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox<>();
        btnSaveEvent = new javax.swing.JButton();
        btnDeleteEvent = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(tree);

        table.setModel(outcomeTable);
        jScrollPane2.setViewportView(table);

        btnCreateOutcome.setText("Kimenetel hozzáadása");
        btnCreateOutcome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateOutcomeActionPerformed(evt);
            }
        });

        btnDeleteOutcome.setText("Kimenetel törlése");
        btnDeleteOutcome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteOutcomeActionPerformed(evt);
            }
        });

        btnCreateEvent.setText("Hozzáadás");
        btnCreateEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateEventActionPerformed(evt);
            }
        });

        lblId.setText("ID");

        lblUserId.setText("User ID");

        lblTitle.setText("Megnevezés");

        txtId.setEditable(false);

        lblCreated.setText("Készült");

        lblStatus.setText("Státusz");

        txtCreated.setEditable(false);

        cbStatus.setModel(new DefaultComboBoxModel(betEventStatus));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(10, 10, 10)
                        .addComponent(txtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserId)
                            .addComponent(lblId))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(txtId))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(txtUserId)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblCreated))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCreated)
                    .addComponent(cbStatus, 0, 185, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCreated)
                    .addComponent(txtCreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserId)
                    .addComponent(txtUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnSaveEvent.setText("Mentés");
        btnSaveEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEventActionPerformed(evt);
            }
        });

        btnDeleteEvent.setText("Törlés");
        btnDeleteEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEventActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(btnCreateEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnSaveEvent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCreateOutcome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteOutcome)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreateOutcome)
                            .addComponent(btnDeleteOutcome))
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateEvent)
                    .addComponent(btnSaveEvent)
                    .addComponent(btnDeleteEvent))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
        BetEvent event = treeModel.getSelectedBetEvent();
        if(event == null){
            resetFields();
            return;
        } else {
            txtId.setText(event.getId() == -1 ? "" : event.getId().toString());
            txtUserId.setText(event.getUserId().toString());
            txtTitle.setText(event.getTitle());
            txtCreated.setText(event.getCreated().toString());
            cbStatus.setSelectedIndex(event.getStatus());

            fillOutcomeTable(event);                    
            allowControls(true);
        } 
    }//GEN-LAST:event_treeValueChanged

    
    private void btnDeleteEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEventActionPerformed
        deleteEvent();
    }//GEN-LAST:event_btnDeleteEventActionPerformed

    private void btnSaveEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEventActionPerformed
        saveEvent();
    }//GEN-LAST:event_btnSaveEventActionPerformed

    private void btnDeleteOutcomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteOutcomeActionPerformed
        deleteOutcome();
    }//GEN-LAST:event_btnDeleteOutcomeActionPerformed

    private void btnCreateOutcomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateOutcomeActionPerformed
        addOutcome();
    }//GEN-LAST:event_btnCreateOutcomeActionPerformed

    private void btnCreateEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateEventActionPerformed
        addEvent();
    }//GEN-LAST:event_btnCreateEventActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateEvent;
    private javax.swing.JButton btnCreateOutcome;
    private javax.swing.JButton btnDeleteEvent;
    private javax.swing.JButton btnDeleteOutcome;
    private javax.swing.JButton btnSaveEvent;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblCreated;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserId;
    private javax.swing.JTable table;
    private javax.swing.JTree tree;
    private javax.swing.JTextField txtCreated;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtUserId;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.gdf.oop.fogadoiroda.gui;

import hu.gdf.oop.fogadoiroda.data.entity.BetEvent;
import hu.gdf.oop.fogadoiroda.data.entity.Outcome;
import hu.gdf.oop.fogadoiroda.data.repository.BetEventRepository;
import hu.gdf.oop.fogadoiroda.data.repository.OutcomeRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Krisztián
 */
public class BetEventsPanel extends javax.swing.JPanel {

    
    String[] columnNames = {"ID",
            "Bet Event ID",
            "Megnevezés",
            "Nyert",
            "Összes fogadás"
    };
    
    String[] editableColumns = {
            "Megnevezés",
            "Nyert"
    };
    
    String[] betEventStatus = {
        "Nyitott",
        "Lezárt"
    };
    
    BetEventRepository eventRepository = new BetEventRepository();
    OutcomeRepository outcomeRepository = new OutcomeRepository();
    List<BetEvent> events;
    DefaultTreeModel treeModel;
    DataTable outcomeTable;
    
    
    private void getEventsData(){
        events = eventRepository.findAll();
        List<Outcome> outcomes = outcomeRepository.findAll();
        Map<Integer,List<Outcome>> eventOutcomes = new HashMap<Integer,List<Outcome>>();
        for(int i=0;i<outcomes.size();i++){
            Outcome outcome = outcomes.get(i);
            if(eventOutcomes.get(outcome.getBetEventId()) == null){
                List<Outcome> list = new ArrayList<Outcome>();
                list.add(outcome);
                eventOutcomes.put(outcome.getBetEventId(),list);
            }else
                eventOutcomes.get(outcome.getBetEventId()).add(outcome);
        }
        for(int i=0;i<events.size();i++){
            BetEvent event = events.get(i);
            if(eventOutcomes.get(event.getId()) == null){
                event.setOutcomes(new ArrayList<Outcome>());
            }else
                event.setOutcomes(eventOutcomes.get(event.getId()));
        }
    }

    private void createTreeView(){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Események");
        for(int i=0;i<events.size();i++){
            BetEvent event = events.get(i);
            CustomTreeNode<BetEvent> node = new CustomTreeNode(event.getTitle());
            node.containedObject = event;
            for(int k=0;k<event.getOutcomes().size();k++){
                Outcome outcome = event.getOutcomes().get(k);
                CustomTreeNode<Outcome> outcomeNode = new CustomTreeNode(outcome.getTitle());
                outcomeNode.containedObject = outcome;
                node.add(outcomeNode);
            }
            top.add(node);
        }
        treeModel = new DefaultTreeModel(top);
        tree.setModel(treeModel);
    }
    
    private void setTableData(){
        outcomeTable = new DataTable(columnNames, new ArrayList<Object[]>());
        outcomeTable.setEditableColumns(editableColumns);
    }
    
    class CustomTreeNode<E> extends DefaultMutableTreeNode{
        public E containedObject;
        public CustomTreeNode(Object userObject){
            super.setUserObject(userObject);
        }
    }
    
    public void loadData(){
        getEventsData();
        createTreeView();
    }
    
    private BetEvent getSelectedBetEvent(){
        if(tree.getLastSelectedPathComponent() instanceof CustomTreeNode) {
            CustomTreeNode node = (CustomTreeNode) tree.getLastSelectedPathComponent();
            BetEvent event;
            if (node.containedObject instanceof BetEvent) {
                event = (BetEvent) node.containedObject;
            } else if (node.containedObject instanceof Outcome) {
                CustomTreeNode parent = (CustomTreeNode) node.getParent();
                event = (BetEvent) parent.containedObject;
            }else{
                return null;
            }
            return event;
        }else{
            return null;
        }
    }
    
    private CustomTreeNode getSelectedEventNode(){
        if(tree.getLastSelectedPathComponent() instanceof CustomTreeNode) {
            CustomTreeNode node = (CustomTreeNode) tree.getLastSelectedPathComponent();
            if (node.containedObject instanceof BetEvent) {
                return node;
            } else if (node.containedObject instanceof Outcome) {
                return (CustomTreeNode)node.getParent();
            }
            return null;
        }else{
            return null;
        }
    } 
    
    private void resetFields(){
        txtId.setText("");
        txtUserId.setText("");
        txtTitle.setText("");
        txtCreated.setText("");
        cbStatus.setSelectedIndex(0);
        outcomeTable.loadData(new ArrayList<Object[]>());
        table.revalidate();
        table.repaint();
        allowControls(false);
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
        btnSaveEvent = new javax.swing.JButton();
        btnDeleteEvent = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtUserId = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        lblCreated = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtCreated = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox<>();

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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTitle)
                                .addGap(10, 10, 10)
                                .addComponent(txtTitle))
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
                            .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSaveEvent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveEvent)
                    .addComponent(btnDeleteEvent)))
        );

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreateOutcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteOutcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreateEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCreateOutcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteOutcome))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
        BetEvent event = getSelectedBetEvent();
        if(event == null){
            resetFields();
            return;
        }
        txtId.setText(event.getId().toString());
        txtUserId.setText(event.getUserId().toString());
        txtTitle.setText(event.getTitle());
        txtCreated.setText(event.getCreated().toString());
        cbStatus.setSelectedIndex(event.getStatus());
        
        allowControls(true);
        
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        for(int i = 0; i<event.getOutcomes().size();i++){
            data.add(event.getOutcomes().get(i).toArray());
        }
        outcomeTable.loadData(data);
        table.revalidate();
        table.repaint();
    }//GEN-LAST:event_treeValueChanged

    private void btnDeleteEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEventActionPerformed
        BetEvent event = getSelectedBetEvent();
        if(event == null) return;
        if(event.getOutcomes().size() > 0){
            for(int i=0;i<event.getOutcomes().size();i++){
                outcomeRepository.delete(event.getOutcomes().get(i));
            }
        }
        eventRepository.delete(event);
        treeModel.removeNodeFromParent(getSelectedEventNode());
        
        resetFields();
    }//GEN-LAST:event_btnDeleteEventActionPerformed

    private void btnSaveEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEventActionPerformed
        BetEvent event = getSelectedBetEvent();
        if(event == null) return;
        event.setUserId(Integer.parseInt(txtUserId.getText()));
        event.setTitle(txtTitle.getText());
        event.setStatus(cbStatus.getSelectedIndex());
        eventRepository.update(event);
        
        ArrayList<Object[]> data = outcomeTable.data;
        for(int i=0; i<outcomeTable.rowStatus.size();i++){
            DataTable.RowStatus status = outcomeTable.rowStatus.get(i);
            boolean change = (status == DataTable.RowStatus.CHANGED || status == DataTable.RowStatus.DISABLED);
            if(change){
                if(data.get(i)[0] == null){
                    if(status == DataTable.RowStatus.CHANGED){
                        Outcome outcome = new Outcome();
                        //TODO Create sequence id
                        outcome.setBetEventId(event.getId());
                        outcome.setTitle((String) data.get(i)[2]);
                        outcome.setWon((boolean)data.get(i)[3]);
                        outcome.setSumBetAmount((int)data.get(i)[4]);
                        outcomeRepository.create(outcome);
                    }
                }else{
                    int outcomeId = (int)(data.get(i)[0]);
                    Outcome outcome = outcomeRepository.findOne(outcomeId);
                    if(status == DataTable.RowStatus.DISABLED){
                        outcomeRepository.delete(outcome);
                    }else{
                        outcome.setBetEventId(event.getId());
                        outcome.setTitle((String) data.get(i)[2]);
                        outcome.setWon((boolean)data.get(i)[3]);
                        outcome.setSumBetAmount((int)data.get(i)[4]);
                        outcomeRepository.update(outcome);
                    }
                }
            }
        }
        outcomeTable.resetRowStatus();
    }//GEN-LAST:event_btnSaveEventActionPerformed

    private void btnDeleteOutcomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteOutcomeActionPerformed
        outcomeTable.deleteRow(table.getSelectedRow());
        table.revalidate();
        table.repaint();
    }//GEN-LAST:event_btnDeleteOutcomeActionPerformed

    private void btnCreateOutcomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateOutcomeActionPerformed
        outcomeTable.addRow(Outcome.getEmptyModel());
        table.revalidate();
        table.repaint();
    }//GEN-LAST:event_btnCreateOutcomeActionPerformed

    private void btnCreateEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateEventActionPerformed
        // TODO add your handling code here:
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

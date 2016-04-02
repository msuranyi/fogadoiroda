package hu.gdf.oop.fogadoiroda.gui;


import hu.gdf.oop.fogadoiroda.data.entity.BetEvent;
import hu.gdf.oop.fogadoiroda.data.repository.BetEventRepository;
import hu.gdf.oop.fogadoiroda.data.repository.OutcomeRepository;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BetEventsPanel extends JPanel implements ActionListener,TreeSelectionListener{

    BetEventRepository eventRepository = new BetEventRepository();
    OutcomeRepository outcomeRepository = new OutcomeRepository();

    JTree tree;
    JScrollPane treeView;

    public BetEventsPanel(){
        CreateTreeView();

        this.setLayout(new BorderLayout());
        this.add(treeView, BorderLayout.WEST);
    }

    private void CreateTreeView(){
        List<BetEvent> events = eventRepository.findAll();

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Események");

        for(int i=0;i<events.size();i++){
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(events.get(i).getTitle());
            node.setUserObject(events.get(i));
            top.add(node);
        }
        tree = new JTree(top);
        tree.addTreeSelectionListener(this);
        treeView = new JScrollPane(tree);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();
        BetEvent event = (BetEvent)node.getUserObject();
        System.out.println(event.getTitle());
    }
}
package hu.gdf.oop.fogadoiroda.gui;


import hu.gdf.oop.fogadoiroda.data.entity.BetEvent;
import hu.gdf.oop.fogadoiroda.data.repository.BetEventRepository;
import hu.gdf.oop.fogadoiroda.data.repository.OutcomeRepository;
import hu.gdf.oop.fogadoiroda.data.entity.Outcome;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BetEventsPanel extends JPanel implements ActionListener,TreeSelectionListener{

    BetEventRepository eventRepository = new BetEventRepository();
    OutcomeRepository outcomeRepository = new OutcomeRepository();
    List<BetEvent> events;

    String[] eventColumnNames = {"ID",
            "User ID",
            "Megnevezés",
            "Készült",
            "Státusz"
    };
    String[] outcomeColumnNames = {"ID",
            "Bet Event ID",
            "Megnevezés",
            "Nyert"
    };

    JTree tree;
    JScrollPane treeView;
    JTable table;
    DataTable tableData;

    public BetEventsPanel(){
        getEventsData();
        CreateTreeView();
        table = new JTable(tableData);
        this.setLayout(new BorderLayout());
        this.add(treeView, BorderLayout.WEST);
        this.add(table,BorderLayout.CENTER);
    }

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

    private void CreateTreeView(){
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
        tree = new JTree(top);
        tree.addTreeSelectionListener(this);
        treeView = new JScrollPane(tree);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        CustomTreeNode node = (CustomTreeNode)tree.getLastSelectedPathComponent();
        if(node.containedObject instanceof BetEvent){
            BetEvent event = (BetEvent)node.containedObject;
            System.out.println(event.getOutcomes().size());
            System.out.println(event.getTitle());
        }else
        if(node.containedObject instanceof Outcome){
            Outcome outcome = (Outcome)node.containedObject;
            System.out.println(outcome.getTitle());
        }
    }

    class CustomTreeNode<E> extends DefaultMutableTreeNode{
        public E containedObject;
        public CustomTreeNode(Object userObject){
            super.setUserObject(userObject);
        }
    }


}
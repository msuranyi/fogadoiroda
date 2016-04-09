package hu.gdf.oop.fogadoiroda.gui;

import hu.gdf.oop.fogadoiroda.data.entity.BetEvent;
import hu.gdf.oop.fogadoiroda.data.entity.Outcome;
import hu.gdf.oop.fogadoiroda.data.repository.BetEventRepository;
import hu.gdf.oop.fogadoiroda.data.repository.OutcomeRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class BetEventsTreeModel extends DefaultTreeModel{
    
    BetEventRepository eventRepository = new BetEventRepository();
    OutcomeRepository outcomeRepository = new OutcomeRepository();
    List<BetEvent> events;
    List<CustomTreeNode> nodes = new ArrayList<CustomTreeNode>();
    JTree tree;

    public BetEventsTreeModel(TreeNode root) {
        super(root);
    }
    
    class CustomTreeNode<E> extends DefaultMutableTreeNode {

        public E containedObject;

        public CustomTreeNode(Object userObject){
            super.setUserObject(userObject);
        }

    }
    
    public void loadData(JTree tree){
        this.tree = tree;
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
        
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Események");
        for(int i=0;i<events.size();i++){
            BetEvent event = events.get(i);
            CustomTreeNode<BetEvent> node = new CustomTreeNode(event.getTitle());
            node.containedObject = event;
            List<Outcome> list = eventOutcomes.get(event.getId());
            if(list != null)
            for(int k=0;k<list.size();k++){
                Outcome outcome = list.get(k);
                CustomTreeNode<Outcome> outcomeNode = new CustomTreeNode(outcome.getTitle());
                outcomeNode.containedObject = outcome;
                node.add(outcomeNode);
                nodes.add(outcomeNode);
            }
            nodes.add(node);
            top.add(node);
        }
        this.setRoot(top);
        tree.setModel(this);
    }
    
    public BetEvent getSelectedBetEvent(){
        CustomTreeNode node = getSelectedEventNode(tree.getLastSelectedPathComponent());
        if(node == null) return null;
        return (BetEvent)node.containedObject;
    }
    
    public CustomTreeNode getSelectedEventNode(Object path){
        if(path instanceof CustomTreeNode) {
            CustomTreeNode node = (CustomTreeNode) path;
            if (node.containedObject instanceof BetEvent) {
                return node;
            } else {
                return getSelectedEventNode(node.getParent());
            }
        }else{
            return null;
        }
    }
    
    public void createNode(Object parent,Object child){
        DefaultMutableTreeNode parentNode;
        if(parent == this.getRoot()){
            parentNode = (DefaultMutableTreeNode)this.getRoot();
        }else{
            parentNode = findNodeContaining(parent);
        }
        CustomTreeNode childNode = new CustomTreeNode("");
        
        childNode.containedObject = child;
        
        if(child instanceof BetEvent){
            childNode.setUserObject("Új esemény");
        }else if(child instanceof Outcome){
            childNode.setUserObject(((Outcome) child).getTitle());
        }
        
        nodes.add(childNode);
        
        this.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
    }
    
    public void setNodeTitle(Object search,String title){
        CustomTreeNode node = findNodeContaining(search);
        if (node != null) {
            node.setUserObject(title);
        }
    }
    
    public void removeNode(Object remove){
        CustomTreeNode node = findNodeContaining(remove);
        if (node != null) {
            this.removeNodeFromParent(node);
            nodes.remove(node);
        }
    }
    
    private CustomTreeNode findNodeContaining(Object search) {
        CustomTreeNode result = null;
        if (nodes != null) {
            for (int i = 0; i < nodes.size(); i++) {
                CustomTreeNode node = nodes.get(i);
                Object o = node.containedObject;
                if (o.equals(search)) {
                    result = node;
                    break;
                }
            }
        }
        return result;
    }

}

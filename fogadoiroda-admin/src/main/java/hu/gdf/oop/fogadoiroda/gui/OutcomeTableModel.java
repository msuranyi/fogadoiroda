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

public class OutcomeTableModel extends AbstractTableModel{

    
    BetEvent parent;
    private List<Outcome> list;
    private OutcomeRepository repository = new OutcomeRepository();
    
    public void loadData(BetEvent event){
        list = repository.findWithBetEventId(event.getId());
        parent = event;
    }
    
    public void emptyTable(){
        list = new ArrayList<Outcome>();
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
        Outcome selected = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return selected.getId();
            case 1:
                return selected.getTitle();
            case 2:
                return selected.isWon();
            case 3:
                return selected.getSumBetAmount();
            case 4:
                return selected.isDirty();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "ID";
            case 1:
                return "Megnevezés";
            case 2:
                return "Nyert";
            case 3:
                return "Összes fogadás";
            case 4:
                return "Változott";
        }
        return null;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 3:
                return Integer.class;
            case 2:
            case 4:
                return Boolean.class;
            default:
                return String.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        switch (columnIndex) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Outcome selected = list.get(rowIndex);
        switch (columnIndex) {
            case 1:
                selected.setTitle((String) value);
                break;
            case 2:
                selected.setWon((boolean)value);
                break;
        }
        selected.setDirty(true);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void addRow(){
        Outcome outcome = new Outcome();
        list.add(outcome);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }
    
    public void deleteRow(int rowIndex,BetEventsTreeModel treeModel) {
        Outcome selected = list.get(rowIndex);
        if (selected.getId() != null) {
            if (parent.getStatus() == 1 || parent.getStatus() == 2) {
                throw new ApplicationException("Az esemény nyilvános, ezért nem módosítható!");                
            }
            Outcome outcome = repository.findOne(selected.getId());
            if (outcome != null) {
                repository.delete(outcome);
                treeModel.removeNode(outcome);
            }
        }
        list.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void saveRows(BetEventsTreeModel treeModel) {
        if (list != null && !list.isEmpty()) {
            list.stream().filter(o -> o.isDirty()).forEach(o -> {
                if (o.getId() == null) {
                    if (o.getBetEventId() == null) o.setBetEventId(parent.getId());
                    if (o.getTitle() == null) o.setTitle("Kimenetel");
                    if (o.getSumBetAmount() == null) o.setSumBetAmount(0);
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

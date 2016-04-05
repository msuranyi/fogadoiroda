package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataTable extends AbstractTableModel{

    public enum RowStatus{
        NOT_CHANGED,CHANGED,DISABLED
    }

    private String[] columnNames;
    private List<String> editable;

    public ArrayList<Object[]> data = new ArrayList<Object[]>();

    public ArrayList<RowStatus> rowStatus;

    public DataTable(String[] columnNames,ArrayList<Object[]> data){
        this.columnNames = columnNames;
        this.data = data;
        resetRowStatus();
    }
    public void resetRowStatus(){
        rowStatus = new ArrayList<RowStatus>();
        for(int i=0;i<data.size();i++){
            rowStatus.add(RowStatus.NOT_CHANGED);
        }
    }

    public void setEditableColumns(String[] editable){
        this.editable = Arrays.asList(editable);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data.get(0)[columnIndex].getClass();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int col)
    {
        if(editable.contains(getColumnName(col)))
        return true;
        else
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data.get(row)[col] = value;
        rowStatus.set(row,RowStatus.CHANGED);
        fireTableCellUpdated(row, col);
    }

    public void addRow(Object[] model){
        data.add(model);
        rowStatus.add(RowStatus.NOT_CHANGED);
    }

    public void deleteRow(int row){
        rowStatus.set(row,RowStatus.DISABLED);
    }
}

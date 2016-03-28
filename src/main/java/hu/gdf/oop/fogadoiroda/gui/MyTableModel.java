package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

    String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};

    Object[][] data = {
            {"Kathy", "Smith",
                    "Snowboarding", 5, false},
            {"John", "Doe",
                    "Rowing", 3, true},
            {"Sue", "Black",
                    "Knitting", 2, false},
            {"Jane", "White",
                    "Speed reading", 20, true},
            {"Joe", "Brown",
                    "Pool", 10, false}
    };

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
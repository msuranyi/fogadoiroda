package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class Teszt extends JFrame {

    class MyTableModel extends AbstractTableModel {

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

    }

    private JTable table = new JTable(new MyTableModel());

    public Teszt() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(this);
//    JPanel pn = new JPanel();
//    pn.add(table.getTableHeader());
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(new JScrollPane(table));
        setVisible(true);
    }

    public static void main(String[] args) {
        new Teszt();
    }
}
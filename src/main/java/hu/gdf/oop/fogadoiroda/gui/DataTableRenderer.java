package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DataTableRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(table.isCellEditable(row,column)){
            cellComponent.setBackground(Color.WHITE);
        } else {
            cellComponent.setBackground(Color.LIGHT_GRAY);
        }
        return cellComponent;
    }
}

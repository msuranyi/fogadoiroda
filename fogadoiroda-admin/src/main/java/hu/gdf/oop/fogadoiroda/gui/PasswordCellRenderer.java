package hu.gdf.oop.fogadoiroda.gui;

import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PasswordCellRenderer extends JPasswordField implements TableCellRenderer {

    public PasswordCellRenderer() {
        setText("filler123");
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
    
}
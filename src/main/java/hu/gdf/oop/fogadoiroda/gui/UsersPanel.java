package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanel extends JPanel implements ActionListener {

    private JTable table = new JTable(new MyTableModel());

    public UsersPanel() {
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(new JScrollPane(table));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanel extends JPanel implements ActionListener{

    public UsersPanel(){
        this.add(new JLabel("Felhasználók"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

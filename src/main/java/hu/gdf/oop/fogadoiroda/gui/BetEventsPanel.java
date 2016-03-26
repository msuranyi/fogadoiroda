package hu.gdf.oop.fogadoiroda.gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BetEventsPanel extends JPanel implements ActionListener{

    public BetEventsPanel(){
        this.add(new JLabel("Fogadások"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
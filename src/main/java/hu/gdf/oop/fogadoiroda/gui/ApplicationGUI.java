package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationGUI extends JFrame implements ActionListener {

    JPanel cp;
    UsersPanel usersPanel = new UsersPanel();
    BetEventsPanel betEventsPanel = new BetEventsPanel();
    JMenuBar mBar = new JMenuBar();
    JMenu mFile = new JMenu("F�jl");
    JMenuItem mExit = new JMenuItem("Bez�r�s");

    JMenu mTables = new JMenu("T�bl�k");
    JMenuItem mUsers = new JMenuItem("Felhaszn�l�k");
    JMenuItem mBetEvents = new JMenuItem("Fogad�sok");

    public ApplicationGUI(){
        initComponents();
        initFrame();
    }

    private void initComponents(){
        cp = (JPanel)this.getContentPane();

        mFile.add(mExit);
        mBar.add(mFile);
        mBar.add(mTables);
        mTables.add(mUsers);
        mTables.add(mBetEvents);
        
        mUsers.addActionListener(this);
        mBetEvents.addActionListener(this);
        mExit.addActionListener(this);

    }

    private void initFrame(){
        this.setJMenuBar(mBar);
        this.setTitle("Fogad�iroda Back Office");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }

    public void setActivePanel(JPanel panel){
        cp.removeAll();
        cp.add(panel);
        cp.revalidate();
        cp.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src.equals(mUsers)){
            setActivePanel(usersPanel);
        }else
        if(src.equals(mBetEvents)){
            setActivePanel(betEventsPanel);
        }else
        if(src.equals(mExit)){
            System.exit(0);
        }
    }
}

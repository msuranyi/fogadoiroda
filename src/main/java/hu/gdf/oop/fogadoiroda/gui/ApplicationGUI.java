package hu.gdf.oop.fogadoiroda.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationGUI extends JFrame implements ActionListener {

    JPanel cp;
    UsersPanel usersPanel;
    BetEventsPanel betEventsPanel;
    JMenuBar mBar = new JMenuBar();
    JMenu mFile = new JMenu("Fájl");
    JMenuItem mExit = new JMenuItem("Bezárás");

    JMenu mTables = new JMenu("Táblák");
    JMenuItem mUsers = new JMenuItem("Felhasználók");
    JMenuItem mBetEvents = new JMenuItem("Fogadások");

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
        this.setTitle("Fogadóiroda Back Office");
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
            if(usersPanel == null) usersPanel = new UsersPanel();
            setActivePanel(usersPanel);
        }else
        if(src.equals(mBetEvents)){
            if(betEventsPanel == null) betEventsPanel = new BetEventsPanel();
            setActivePanel(betEventsPanel);
        }else
        if(src.equals(mExit)){
            System.exit(0);
        }
    }
}

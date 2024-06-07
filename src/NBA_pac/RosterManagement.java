package NBA_pac;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

// RosterManagement class which extends JFrame and implements ActionListener for GUI interactions
public class RosterManagement extends JFrame implements ActionListener {

    // Declaring JButton variables for different functionalities
    JButton CEQ;
    JButton IRM;
    JButton PPR;
    JButton Database;

    // Constructor to initialize and set up the GUI components
    RosterManagement() {
        // Setting up the Contract button
        CEQ = new JButton();
        CEQ.setBounds(260, 40, 200, 60);
        CEQ.addActionListener(this);
        CEQ.setText("Contract");
        CEQ.setFocusable(false);
        CEQ.setVisible(true);
        CEQ.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        CEQ.setOpaque(true);
        CEQ.setForeground(Color.white);
        CEQ.setBackground(Color.DARK_GRAY);
        CEQ.setBorder(BorderFactory.createEtchedBorder());

        // Setting up the Injury Management button
        IRM = new JButton();
        IRM.setBounds(260, 140, 200, 60);
        IRM.addActionListener(this);
        IRM.setText("Injury Management");
        IRM.setFocusable(false);
        IRM.setVisible(true);
        IRM.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        IRM.setOpaque(true);
        IRM.setForeground(Color.white);
        IRM.setBackground(Color.DARK_GRAY);
        IRM.setBorder(BorderFactory.createEtchedBorder());

        // Setting up the Ranking button
        PPR = new JButton();
        PPR.setBounds(260, 240, 200, 60);
        PPR.addActionListener(this);
        PPR.setText("Ranking");
        PPR.setFocusable(false);
        PPR.setVisible(true);
        PPR.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        PPR.setOpaque(true);
        PPR.setForeground(Color.white);
        PPR.setBackground(Color.DARK_GRAY);
        PPR.setBorder(BorderFactory.createEtchedBorder());

        // Setting up the Database Work button
        Database = new JButton();
        Database.setBounds(260, 340, 200, 60);
        Database.addActionListener(this);
        Database.setText("DataBase Work");
        Database.setFocusable(false);
        Database.setVisible(true);
        Database.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        Database.setOpaque(true);
        Database.setForeground(Color.white);
        Database.setBackground(Color.DARK_GRAY);
        Database.setBorder(BorderFactory.createEtchedBorder());

        // Setting up the frame
        this.setSize(720, 450);
        this.setTitle("N B A -- Management");
        this.setResizable(false);

        // Adding background image
        ImageIcon background = new ImageIcon("managebg.jpg");
        Image image = background.getImage();
        Image newimg = image.getScaledInstance(720, 450, java.awt.Image.SCALE_SMOOTH);
        background = new ImageIcon(newimg);
        this.setIconImage(background.getImage());
        JLabel bg = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.white);
        bg.setIcon(background);
        bg.setBounds(0, 0, 720, 450);
        bg.setBorder(border);

        // Adding buttons and background to the frame
        this.add(CEQ);
        this.add(IRM);
        this.add(PPR);
        this.add(Database);
        this.add(bg);
        this.setVisible(true);
    }

    // Action event handler to perform actions based on button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle Contract button click
        if (e.getSource().equals(CEQ)) {
            new newCEQ();
        }
        // Handle Injury Management button click
        if (e.getSource().equals(IRM)) {
            new newIRM();
        }
        // Handle Ranking button click
        if (e.getSource().equals(PPR)) {
            dispose();
            new newPPR();
        }
        // Handle Database Work button click
        if (e.getSource().equals(Database)) {
            DataBaseControl.dropTableIfExists(CreateRoster.rostername);
            DataBaseControl.createTableIfNotExists(CreateRoster.rostername);
            for (Player player : Roster.rbRoster(CreateRoster.rostername).getRoster()) {
                DataBaseControl.insertOrUpdatePlayer(CreateRoster.rostername, player);
            }
            JOptionPane.showMessageDialog(null, "DataBase Done!");
        }
    }
}

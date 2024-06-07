/*
 * This class represents the user interface for managing an existing roster.
 * It allows users to add, remove, check, and display the roster, as well as perform dynamic searching.
 */
package NBA_pac;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ExistRoster implements ActionListener {
    JFrame frame = new JFrame();
    JTextField addField;
    JTextField removeField;
    JButton add;
    JButton remove;
    JButton check;
    JButton showRoster;
    JButton dynamicSearching;
    
    /*
     * Constructor for the ExistRoster class.
     * Initializes and configures the user interface components.
     */
    ExistRoster(String rname) {
        frame.setSize(750, 570);
        frame.setTitle("Roster");
        frame.setResizable(false);
        ImageIcon background = new ImageIcon("otherbg.jpg");
        JLabel bg = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.white);
        bg.setIcon(background);
        bg.setBounds(0, 0, 750, 570);
        bg.setBorder(border);
        
        addField = new JTextField();
        addField.setForeground(Color.yellow);
        addField.setBackground(Color.gray);
        addField.setBounds(385, 100, 140, 70);
        addField.setText("Input Player's ID");
        addField.setFont(new Font("Wawati SC", Font.BOLD, 15));
        addField.setBorder(BorderFactory.createEtchedBorder());
        
        add = new JButton();
        add.setText("Add Player");
        add.setBounds(225, 100, 140, 70);
        add.setFont(new Font("Wawati SC", Font.BOLD, 20));
        add.setBackground(Color.gray);
        add.setForeground(Color.yellow);
        add.setBorder(BorderFactory.createEtchedBorder());
        add.addActionListener(this);
        
        removeField = new JTextField();
        removeField.setForeground(Color.yellow);
        removeField.setBackground(Color.gray);
        removeField.setBounds(385, 185, 140, 70);
        removeField.setText("Input Player's ID");
        removeField.setFont(new Font("Wawati SC", Font.BOLD, 15));
        removeField.setBorder(BorderFactory.createEtchedBorder());
        
        remove = new JButton();
        remove.setText("Remove Player");
        remove.setBounds(225, 185, 140, 70);
        remove.setFont(new Font("Wawati SC", Font.BOLD, 20));
        remove.setBackground(Color.gray);
        remove.setForeground(Color.yellow);
        remove.setBorder(BorderFactory.createEtchedBorder());
        remove.addActionListener(this);

        check = new JButton();
        check.setText("Check");
        check.setBounds(225, 270, 300, 70);
        check.setFont(new Font("Wawati SC", Font.BOLD, 20));
        check.setBackground(Color.gray);
        check.setForeground(Color.yellow);
        check.setBorder(BorderFactory.createEtchedBorder());
        check.addActionListener(this);

        showRoster = new JButton();
        showRoster.setText("ShowRoster");
        showRoster.setBounds(225, 355, 300, 70);
        showRoster.setFont(new Font("Wawati SC", Font.BOLD, 20));
        showRoster.setBackground(Color.gray);
        showRoster.setForeground(Color.yellow);
        showRoster.setBorder(BorderFactory.createEtchedBorder());
        showRoster.addActionListener(this);

        dynamicSearching = new JButton();
        dynamicSearching.setText("Dynamic Searching");
        dynamicSearching.setBounds(225, 440, 300, 70);
        dynamicSearching.setFont(new Font("Wawati SC", Font.BOLD, 20));
        dynamicSearching.setBackground(Color.gray);
        dynamicSearching.setForeground(Color.yellow);
        dynamicSearching.setBorder(BorderFactory.createEtchedBorder());
        dynamicSearching.addActionListener(this);
        
        JLabel title = new JLabel();
        title.setText(CreateRoster.rostername + "'s Team");
        title.setForeground(new Color(255, 215, 0));
        title.setBounds(300, 0, 200, 60);
        title.setFont(new Font("Hanzipen SC", Font.BOLD, 25));
        title.setOpaque(false);
        title.setBorder(null);
        
        frame.add(title);
        frame.add(addField);
        frame.add(add);
        frame.add(check);
        frame.add(dynamicSearching);
        frame.add(showRoster);
        frame.add(removeField);
        frame.add(remove);
        frame.add(bg);
        frame.setVisible(true);
    }

    /*
     * ActionListener implementation for handling button clicks.
     * Performs actions based on the button clicked.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(add)) {
            String tempid = addField.getText();
            try {
                int id = Integer.parseInt(tempid);
                if (Roster.rbRoster(CreateRoster.rostername).idCheck.contains(id)) {
                    JOptionPane.showMessageDialog(null, "Player Existed", null, JOptionPane.ERROR_MESSAGE);
                } else if(id <= 0 || id > 540) {
                    JOptionPane.showMessageDialog(null, "Invalid ID, should be 1 to 540", null, JOptionPane.ERROR_MESSAGE);
                } else if((Roster.rbRoster(CreateRoster.rostername).remainingSalaryCap - new Player(id).getSalary()) < 0) {
                    JOptionPane.showMessageDialog(null, "Failed :( Salary Cap Not Valid!", null, JOptionPane.ERROR_MESSAGE);
                } else if(Roster.rbRoster(CreateRoster.rostername).getPlayerNum() == 15) {
                    JOptionPane.showMessageDialog(null, "Player number exceeds maximum", null, JOptionPane.ERROR_MESSAGE);
                } else {
                    Roster.rbRoster(CreateRoster.rostername).addPlayer(new Player(id));
                    JOptionPane.showMessageDialog(null, "Player Added! Remain Salary: " + Roster.rbRoster(CreateRoster.rostername).remainingSalaryCap);
                    Roster.rbRoster(CreateRoster.rostername).idCheck.add(id);
                    Roster.rbRoster(CreateRoster.rostername).idqCheck.add(id);
                    Roster.rbRoster(CreateRoster.rostername).idsCheck.add(id);
                }
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Invalid Type! Integer please...", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource().equals(remove)) {
            try {
                System.out.println(1);
                String tempid = removeField.getText();
                int id = Integer.parseInt(tempid);
                
                if (Roster.rbRoster(CreateRoster.rostername).idCheck.contains(id)) {
                    Roster.rbRoster(CreateRoster.rostername).removePlayer(Roster.rbRoster(CreateRoster.rostername).getPlayer(id));
                    JOptionPane.showMessageDialog(null, "Player Removed! Remain Salary: " + Roster.rbRoster(CreateRoster.rostername).remainingSalaryCap);
                    Roster.rbRoster(CreateRoster.rostername).idCheck.remove(Integer.valueOf(id));
                    Roster.rbRoster(CreateRoster.rostername).idqCheck.remove(Integer.valueOf(id));
                    Roster.rbRoster(CreateRoster.rostername).idsCheck.remove(Integer.valueOf(id));
                } else {
                    JOptionPane.showMessageDialog(null, "Player not Existed", null, JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Invalid Type! Integer please...", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource().equals(showRoster)) {
            new ShowCurrentRoster();
        }
        if(e.getSource().equals(check)) {
            if(Roster.rbRoster(CreateRoster.rostername).checkRoster()) {
                JOptionPane.showMessageDialog(null, "Valid Roster");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Roster, each G,F,C at least 2 and total min 10", null, JOptionPane.ERROR_MESSAGE);
            };
        }
        if (e.getSource().equals(dynamicSearching)) {
            new newDynamicSearching();
        }
    }
}


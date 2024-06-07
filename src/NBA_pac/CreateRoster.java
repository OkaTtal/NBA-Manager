package NBA_pac;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * This class handles the creation and management of a roster for an NBA team.
 */
public class CreateRoster implements ActionListener {
    static String rostername; // Static variable to store the roster name

    JFrame frame = new JFrame(); // Frame for the GUI
    Roster currentRoster; // The current roster object

    JTextField addField; // Text field to input the player ID to add
    JTextField removeField; // Text field to input the player ID to remove
    JButton add; // Button to add a player
    JButton remove; // Button to remove a player
    JButton check; // Button to check if the roster is valid
    JButton showRoster; // Button to show the current roster
    JButton dynamicSearching; // Button for dynamic searching
    boolean work = true; // Flag to indicate if the roster creation process should proceed
    String specialCharacters = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~"; // String of special characters to check against

    /**
     * Constructor to initialize the CreateRoster GUI and functionality.
     */
    CreateRoster() {
        // Prompt user for roster name
        rostername = JOptionPane.showInputDialog("Your roster name: ");
        if (rostername != null) {
            for (char checking : rostername.toCharArray()) {
                if (specialCharacters.contains(String.valueOf(checking))) {
                    JOptionPane.showMessageDialog(null, "Invalid character", null, JOptionPane.ERROR_MESSAGE);
                    work = false;
                }
            }
        }

        // Check if the roster name is valid and does not contain special characters
        if (rostername != null && !rostername.trim().isEmpty() && work) {
            // Check if the roster name already exists
            while (Roster.existName.contains(rostername)) {
                JOptionPane.showMessageDialog(null, "Roster Name Existed", null, JOptionPane.WARNING_MESSAGE);
                rostername = JOptionPane.showInputDialog("Retype your roster name: ");
            }

            // Initialize the current roster and add it to the list of existing rosters
            currentRoster = new Roster(rostername);
            Roster.existRoster.add(currentRoster);
            Roster.existName.add(rostername);

            // Set up the frame and its components
            frame.setSize(750, 570);
            frame.setTitle("Roster Create");
            frame.setResizable(false);

            // Set up background image
            ImageIcon background = new ImageIcon("otherbg.jpg");
            JLabel bg = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.white);
            bg.setIcon(background);
            bg.setBounds(0, 0, 750, 570);
            bg.setBorder(border);

            // Set up the add field
            addField = new JTextField();
            addField.setForeground(Color.yellow);
            addField.setBackground(Color.gray);
            addField.setBounds(385, 100, 140, 70);
            addField.setText("Input Player's ID");
            addField.setFont(new Font("Wawati SC", Font.BOLD, 15));
            addField.setBorder(BorderFactory.createEtchedBorder());

            // Set up the add button
            add = new JButton();
            add.setText("Add Player");
            add.setBounds(225, 100, 140, 70);
            add.setFont(new Font("Wawati SC", Font.BOLD, 20));
            add.setBackground(Color.gray);
            add.setForeground(Color.yellow);
            add.setBorder(BorderFactory.createEtchedBorder());
            add.addActionListener(this);

            // Set up the remove field
            removeField = new JTextField();
            removeField.setForeground(Color.yellow);
            removeField.setBackground(Color.gray);
            removeField.setBounds(385, 185, 140, 70);
            removeField.setText("Input Player's ID");
            removeField.setFont(new Font("Wawati SC", Font.BOLD, 15));
            removeField.setBorder(BorderFactory.createEtchedBorder());

            // Set up the remove button
            remove = new JButton();
            remove.setText("Remove Player");
            remove.setBounds(225, 185, 140, 70);
            remove.setFont(new Font("Wawati SC", Font.BOLD, 20));
            remove.setBackground(Color.gray);
            remove.setForeground(Color.yellow);
            remove.setBorder(BorderFactory.createEtchedBorder());
            remove.addActionListener(this);

            // Set up the check button
            check = new JButton();
            check.setText("Check");
            check.setBounds(225, 270, 300, 70);
            check.setFont(new Font("Wawati SC", Font.BOLD, 20));
            check.setBackground(Color.gray);
            check.setForeground(Color.yellow);
            check.setBorder(BorderFactory.createEtchedBorder());
            check.addActionListener(this);

            // Set up the show roster button
            showRoster = new JButton();
            showRoster.setText("ShowRoster");
            showRoster.setBounds(225, 355, 300, 70);
            showRoster.setFont(new Font("Wawati SC", Font.BOLD, 20));
            showRoster.setBackground(Color.gray);
            showRoster.setForeground(Color.yellow);
            showRoster.setBorder(BorderFactory.createEtchedBorder());
            showRoster.addActionListener(this);

            // Set up the dynamic searching button
            dynamicSearching = new JButton();
            dynamicSearching.setText("Dynamic Searching");
            dynamicSearching.setBounds(225, 440, 300, 70);
            dynamicSearching.setFont(new Font("Wawati SC", Font.BOLD, 20));
            dynamicSearching.setBackground(Color.gray);
            dynamicSearching.setForeground(Color.yellow);
            dynamicSearching.setBorder(BorderFactory.createEtchedBorder());
            dynamicSearching.addActionListener(this);

            // Set up the title label
            JLabel title = new JLabel();
            title.setText(rostername + "'s Team");
            title.setForeground(new Color(255, 215, 0));
            title.setBounds(300, 0, 200, 60);
            title.setFont(new Font("Hanzipen SC", Font.BOLD, 25));
            title.setOpaque(false);
            title.setBorder(null);

            // Add components to the frame
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

        } else {
            JOptionPane.showMessageDialog(null, "Operation cancel");
        }
    }

    /**
     * Handles button click events.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(add)) {
            String tempid = addField.getText();
            try {
                int id = Integer.parseInt(tempid);
                if (Roster.rbRoster(rostername).idCheck.contains(id)) {
                    JOptionPane.showMessageDialog(null, "Player Existed", null, JOptionPane.ERROR_MESSAGE);
                } else if ((Roster.rbRoster(rostername).remainingSalaryCap - new Player(id).getSalary()) < 0) {
                    JOptionPane.showMessageDialog(null, "Faild :( Salary Cap Not Valid!", null, JOptionPane.ERROR_MESSAGE);
                } else if (id <= 0 || id > 540) {
                    JOptionPane.showMessageDialog(null, "Invalid ID, should be 1 to 540", null, JOptionPane.ERROR_MESSAGE);
                } else if (Roster.rbRoster(CreateRoster.rostername).getPlayerNum() == 15) {
                    JOptionPane.showMessageDialog(null, "Player number exceed maximum", null, JOptionPane.ERROR_MESSAGE);
                } else {
                    Roster.rbRoster(rostername).addPlayer(new Player(id));
                    JOptionPane.showMessageDialog(null, "Player Added! Remain Salary: " + Roster.rbRoster(rostername).remainingSalaryCap);
                    Roster.rbRoster(rostername).idCheck.add(id);
                    Roster.rbRoster(rostername).idqCheck.add(id);
                    Roster.rbRoster(rostername).idsCheck.add(id);
                    System.out.println(Roster.rbRoster(rostername).remainingSalaryCap - new Player(id).getSalary());
                }
            } catch (Exception e3) {
                JOptionPane.showMessageDialog(null, "Invalid Type! Integer please...", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource().equals(remove)) {
            System.out.println(1);
            String tempid = removeField.getText();
            try {
                int id = Integer.parseInt(tempid);
                if (Roster.rbRoster(rostername).idCheck.contains(id)) {
                    Roster.rbRoster(rostername).removePlayer(Roster.rbRoster(rostername).getPlayer(id));
                    JOptionPane.showMessageDialog(null, "Player Removed! Remain Salary: " + Roster.rbRoster(rostername).remainingSalaryCap);
                    Roster.rbRoster(rostername).idCheck.remove(Integer.valueOf(id));
                    Roster.rbRoster(rostername).idqCheck.remove(Integer.valueOf(id));
                    Roster.rbRoster(rostername).idsCheck.remove(Integer.valueOf(id));
                } else {
                    JOptionPane.showMessageDialog(null, "Player not Existed", null, JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e2) {
            	e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid Type! Integer please...", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource().equals(showRoster)) {
            new ShowCurrentRoster();
        }
        if (e.getSource().equals(check)) {
            if (Roster.rbRoster(rostername).checkRoster()) {
                JOptionPane.showMessageDialog(null, "Valid Roster");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Roster, each G,F,C at least 2 and total min 10", null,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource().equals(dynamicSearching)) {
            new newDynamicSearching();
        }
    }
}

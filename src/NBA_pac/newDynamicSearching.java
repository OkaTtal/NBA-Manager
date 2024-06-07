package NBA_pac;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// This class represents the GUI for dynamic searching based on height, weight, and position.
public class newDynamicSearching extends JFrame implements ActionListener {
    // GUI components for user inputs
    JTextField hle; // Height less than or equal to
    JTextField hhe; // Height greater than or equal to
    JTextField wle; // Weight less than or equal to
    JTextField whe; // Weight greater than or equal to
    JTextField position; // Position (Guard/Forward/Center)
    JButton search; // Button to initiate search
    JButton bhle; // Button to set hle
    JButton bhhe; // Button to set hhe
    JButton bwle; // Button to set wle
    JButton bwhe; // Button to set whe
    JFrame frame = new JFrame(); // Main frame
    int hf; // Flag for height filter (1 for >=, 2 for <=)
    double h; // Height value
    int wf; // Flag for weight filter (1 for >=, 2 for <=)
    double w; // Weight value
    
    // Constructor to set up the GUI components and layout
    newDynamicSearching() {
        this.setSize(750, 570);
        this.setTitle("Dynamic Searching");
        this.setResizable(false);
        
        // Initialize and configure the height <= input field
        hle = new JTextField();
        hle.setForeground(Color.yellow);
        hle.setBackground(Color.gray);
        hle.setBounds(385, 50, 300, 70);
        hle.setText("Height <=: (m)");
        hle.setFont(new Font("Wawati SC", Font.BOLD, 15));
        hle.setBorder(BorderFactory.createEtchedBorder());
        
        // Initialize and configure the add button for hle
        bhle = new JButton();
        bhle.setText("Add");
        bhle.setBounds(225, 50, 140, 70);
        bhle.setFont(new Font("Wawati SC", Font.BOLD, 20));
        bhle.setBackground(Color.gray);
        bhle.setForeground(Color.yellow);
        bhle.setBorder(BorderFactory.createEtchedBorder());
        bhle.addActionListener(this);
        
        // Initialize and configure the height >= input field
        hhe = new JTextField();
        hhe.setForeground(Color.yellow);
        hhe.setBackground(Color.gray);
        hhe.setBounds(385, 135, 300, 70);
        hhe.setText("Height >=: (m)");
        hhe.setFont(new Font("Wawati SC", Font.BOLD, 15));
        hhe.setBorder(BorderFactory.createEtchedBorder());
        
        // Initialize and configure the add button for hhe
        bhhe = new JButton();
        bhhe.setText("Add");
        bhhe.setBounds(225, 135, 140, 70);
        bhhe.setFont(new Font("Wawati SC", Font.BOLD, 20));
        bhhe.setBackground(Color.gray);
        bhhe.setForeground(Color.yellow);
        bhhe.setBorder(BorderFactory.createEtchedBorder());
        bhhe.addActionListener(this);
        
        // Initialize and configure the weight <= input field
        wle = new JTextField();
        wle.setForeground(Color.yellow);
        wle.setBackground(Color.gray);
        wle.setBounds(385, 210, 300, 70);
        wle.setText("Weight <=: (kg)");
        wle.setFont(new Font("Wawati SC", Font.BOLD, 15));
        wle.setBorder(BorderFactory.createEtchedBorder());
        
        // Initialize and configure the add button for wle
        bwle = new JButton();
        bwle.setText("Add");
        bwle.setBounds(225, 210, 140, 70);
        bwle.setFont(new Font("Wawati SC", Font.BOLD, 20));
        bwle.setBackground(Color.gray);
        bwle.setForeground(Color.yellow);
        bwle.setBorder(BorderFactory.createEtchedBorder());
        bwle.addActionListener(this);
        
        // Initialize and configure the weight >= input field
        whe = new JTextField();
        whe.setForeground(Color.yellow);
        whe.setBackground(Color.gray);
        whe.setBounds(385, 305, 300, 70);
        whe.setText("Weight >=: (kg)");
        whe.setFont(new Font("Wawati SC", Font.BOLD, 15));
        whe.setBorder(BorderFactory.createEtchedBorder());
        
        // Initialize and configure the add button for whe
        bwhe = new JButton();
        bwhe.setText("Add");
        bwhe.setBounds(225, 305, 140, 70);
        bwhe.setFont(new Font("Wawati SC", Font.BOLD, 20));
        bwhe.setBackground(Color.gray);
        bwhe.setForeground(Color.yellow);
        bwhe.setBorder(BorderFactory.createEtchedBorder());
        bwhe.addActionListener(this);
        
        // Initialize and configure the position input field
        position = new JTextField();
        position.setForeground(Color.yellow);
        position.setBackground(Color.gray);
        position.setBounds(385, 390, 300, 70);
        position.setText("Position(G/F/C): ");
        position.setFont(new Font("Wawati SC", Font.BOLD, 15));
        position.setBorder(BorderFactory.createEtchedBorder());
        
        // Initialize and configure the search button
        search = new JButton();
        search.setText("Search");
        search.setBounds(385, 475, 300, 70);
        search.setFont(new Font("Wawati SC", Font.BOLD, 20));
        search.setBackground(Color.gray);
        search.setForeground(Color.yellow);
        search.setBorder(BorderFactory.createEtchedBorder());
        search.addActionListener(this);
        
        // Add background image
        JLabel bg = new JLabel();
        ImageIcon background = new ImageIcon("otherbg.jpg");
        bg.setIcon(background);
        bg.setBounds(0, 0, 750, 570);
        
        // Add components to the frame
        this.add(hhe);
        this.add(hle);
        this.add(whe);
        this.add(wle);
        this.add(search);
        this.add(position);
        this.add(bhhe);
        this.add(bhle);
        this.add(bwhe);
        this.add(bwle);
        this.add(bg);
        this.setVisible(true);
    }

    // Handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle height >= button click
        if (e.getSource().equals(bhhe)) {
            try {
                h = Double.parseDouble(hhe.getText());
                bhle.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Added!");
                hf = 1;
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Invalid type!", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Handle height <= button click
        if (e.getSource().equals(bhle)) {
            try {
                h = Double.parseDouble(hle.getText());
                JOptionPane.showMessageDialog(null, "Added!");
                bhhe.setEnabled(false);
                hf = 2;
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Invalid type!", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Handle weight >= button click
        if (e.getSource().equals(bwhe)) {
            try {
                w = Double.parseDouble(whe.getText());
                wf = 1;
                bwle.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Added!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Invalid type!", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Handle weight <= button click
        if (e.getSource().equals(bwle)) {
            try {
                w = Double.parseDouble(wle.getText());
                wf = 2;
                bwhe.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Added!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Invalid type!", null, JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Handle search button click
        if (e.getSource().equals(search)) {
            dispose();
            new DynamicRoster(Searching.dynamicSearching(hf, h, wf, w, position.getText()));
        }
    }
}

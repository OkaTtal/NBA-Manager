package NBA_pac;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class newCEQ extends JFrame implements ActionListener {
    JTextField enq;
    JButton check;
    JButton benq;
    JButton bdeq;

    newCEQ() {
        // Initialize text field for player ID input
        enq = new JTextField();
        enq.setForeground(Color.yellow);
        enq.setBackground(Color.gray);
        enq.setBounds(188, 50, 100, 70);
        enq.setText("Player's ID");
        enq.setFont(new Font("Wawati SC", Font.BOLD, 15));
        enq.setBorder(BorderFactory.createEtchedBorder());

        // Initialize buttons for enqueueing, dequeuing, and checking contract
        benq = new JButton();
        benq.setText("En");
        benq.setBounds(288, 50, 100, 70);
        benq.setFont(new Font("Wawati SC", Font.BOLD, 20));
        benq.setBackground(Color.gray);
        benq.setForeground(Color.yellow);
        benq.setBorder(BorderFactory.createEtchedBorder());
        benq.addActionListener(this);

        bdeq = new JButton();
        bdeq.setText("De");
        bdeq.setBounds(188, 135, 200, 70);
        bdeq.setFont(new Font("Wawati SC", Font.BOLD, 20));
        bdeq.setBackground(Color.gray);
        bdeq.setForeground(Color.yellow);
        bdeq.setBorder(BorderFactory.createEtchedBorder());
        bdeq.addActionListener(this);

        check = new JButton();
        check.setText("Check Contract");
        check.setBounds(188, 220, 200, 70);
        check.setFont(new Font("Wawati SC", Font.BOLD, 20));
        check.setBackground(Color.gray);
        check.setForeground(Color.yellow);
        check.setBorder(BorderFactory.createEtchedBorder());
        check.addActionListener(this);

        // Set background image
        ImageIcon background = new ImageIcon("managebg.jpg");
        Image image = background.getImage().getScaledInstance(576, 360, Image.SCALE_SMOOTH);
        background = new ImageIcon(image);
        JLabel bg = new JLabel();
        bg.setIcon(background);
        bg.setBounds(0, 0, 576, 360);
        bg.setBorder(BorderFactory.createLineBorder(Color.white));

        // Set JFrame properties
        this.setIconImage(background.getImage());
        this.setTitle("Contract");
        this.setResizable(false);
        this.setSize(576, 360);
        this.add(bdeq);
        this.add(benq);
        this.add(check);
        this.add(enq);
        this.add(bg);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(benq)) {
            // Enqueue a player
            String tempid = enq.getText();
            try {
                int id = Integer.parseInt(tempid);
                Object ID = id;
                if (Roster.rbRoster(CreateRoster.rostername).idqCheck.contains(id)) {
                    Roster.rbRoster(CreateRoster.rostername).idqCheck.remove(ID);
                    Roster.rbRoster(CreateRoster.rostername).CEQ.enqueue(new Player(id));
                } else {
                    JOptionPane.showMessageDialog(null, "Player does not exist in your roster: " + id, null, JOptionPane.ERROR_MESSAGE);
                    System.out.println(Roster.rbRoster(CreateRoster.rostername).idqCheck.size());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid type: " + tempid, null, JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource().equals(bdeq)) {
            // Dequeue a player
            Roster.rbRoster(CreateRoster.rostername).CEQ.dequeue();
        }
        if (e.getSource().equals(check)) {
            // Show the list of players in CEQ
            new listCEQ();
        }
    }
}

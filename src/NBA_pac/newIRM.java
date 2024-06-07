package NBA_pac;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class newIRM extends JFrame implements ActionListener {
    JTextField push;
    JButton check;
    JButton bpush;
    JButton bpop;

    newIRM() {
        // Initialize text field for entering player's ID
        push = new JTextField();
        push.setForeground(Color.yellow);
        push.setBackground(Color.gray);
        push.setBounds(188, 50, 100, 70);
        push.setText("Player's ID");
        push.setFont(new Font("Wawati SC", Font.BOLD, 15));
        push.setBorder(BorderFactory.createEtchedBorder());

        // Initialize button for pushing player to injury reserve
        bpush = new JButton();
        bpush.setText("Push");
        bpush.setBounds(288, 50, 100, 70);
        bpush.setFont(new Font("Wawati SC", Font.BOLD, 20));
        bpush.setBackground(Color.gray);
        bpush.setForeground(Color.yellow);
        bpush.setBorder(BorderFactory.createEtchedBorder());
        bpush.addActionListener(this);

        // Initialize button for popping player from injury reserve
        bpop = new JButton();
        bpop.setText("Pop");
        bpop.setBounds(188, 135, 200, 70);
        bpop.setFont(new Font("Wawati SC", Font.BOLD, 20));
        bpop.setBackground(Color.gray);
        bpop.setForeground(Color.yellow);
        bpop.setBorder(BorderFactory.createEtchedBorder());
        bpop.addActionListener(this);

        // Initialize button for checking injury stack
        check = new JButton();
        check.setText("Check Injury Stack");
        check.setBounds(188, 220, 200, 70);
        check.setFont(new Font("Wawati SC", Font.BOLD, 20));
        check.setBackground(Color.gray);
        check.setForeground(Color.yellow);
        check.setBorder(BorderFactory.createEtchedBorder());
        check.addActionListener(this);

        // Set background image
        ImageIcon background = new ImageIcon("managebg.jpg");
        Image image = background.getImage(); // transform it
        Image newimg = image.getScaledInstance(576, 360, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        background = new ImageIcon(newimg); // transform it back
        this.setIconImage(background.getImage());
        JLabel bg = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.white);

        bg.setIcon(background);
        bg.setBounds(0, 0, 576, 360);
        bg.setBorder(border);
        this.setTitle("Contract");

        this.setResizable(false);
        this.setSize(576, 360);
        this.add(bpush);
        this.add(bpop);
        this.add(check);
        this.add(push);
        this.add(bg);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle action for "Push" button
        if (e.getSource().equals(bpush)) {
            String tempid = push.getText();

            try {
                int id = Integer.parseInt(tempid);
                Object ID = id;

                if (Roster.rbRoster(CreateRoster.rostername).idsCheck.contains(id)) {
                    System.out.println("Remove? : " + Roster.rbRoster(CreateRoster.rostername).idsCheck.remove(ID));
                    String injury = JOptionPane.showInputDialog("Current Player's Injury: ");
                    Roster.rbRoster(CreateRoster.rostername).IRM.addToInjuryReserve(new Player(id), injury);
                } else {
                    JOptionPane.showMessageDialog(null, "Not exist player in your roster! " + id, null,
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid type! " + tempid, null, JOptionPane.ERROR_MESSAGE);
            }

        }
        // Handle action for "Pop" button
        if (e.getSource().equals(bpop)) {
            Roster.rbRoster(CreateRoster.rostername).IRM.removeFromInjuryReserve();
        }
        // Handle action for "Check Injury Stack" button
        if (e.getSource().equals(check)) {
            new listIRM();
        }
    }
}

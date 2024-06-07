package NBA_pac;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class SwitchRoster extends JFrame {
    JButton nRoster;

    SwitchRoster() {
        // Set the layout to null for absolute positioning
        this.setLayout(null);

        int x = 50;
        // Iterate through existing rosters
        for (Roster roster : Roster.existRoster) {
            nRoster = new JButton();
            nRoster.setBounds(0, x, 200, 60);
            x += 70;

            final String teamName = roster.getTeamName();

            nRoster.setText(teamName);
            nRoster.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Set the current roster name and open the existing roster window
                    CreateRoster.rostername = teamName;
                    dispose(); // Close the current window
                    new ExistRoster(teamName); // Open the existing roster window
                }
            });

            nRoster.setFocusable(false);
            nRoster.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
            nRoster.setOpaque(true);
            nRoster.setForeground(new Color(255, 215, 0));
            nRoster.setBackground(new Color(85, 0, 0));
            nRoster.setBorder(BorderFactory.createEtchedBorder(Color.white, Color.black));

            this.add(nRoster);
        }

        JLabel bg = new JLabel();
        ImageIcon bgimg = new ImageIcon("swbg.jpg");
        bg.setIcon(bgimg);

        bg.setBounds(0, 0, 200, 600); // Set bounds for background image

        this.add(bg);
        this.setSize(200, 600);
        this.setVisible(true);
    }
}

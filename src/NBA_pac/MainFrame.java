package NBA_pac;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MainFrame implements ActionListener {
    JButton NewRoster;
    JButton SwitchRoster;
    JButton RosterManagement;
    JButton CraftingTheJourney;
    JButton FullPlayerList;
    ArrayList<Player> roster = new ArrayList<Player>();
    JFrame frame = new JFrame();

    MainFrame() {
        // Initialize and customize buttons
        NewRoster = new JButton();
        NewRoster.setBounds(275, 120, 200, 60);
        NewRoster.addActionListener(this);
        NewRoster.setText("New Roster");
        NewRoster.setFocusable(false);
        NewRoster.setVisible(true);
        NewRoster.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        NewRoster.setOpaque(true);
        NewRoster.setForeground(Color.white);
        NewRoster.setBackground(Color.DARK_GRAY);
        NewRoster.setBorder(BorderFactory.createEtchedBorder());

        SwitchRoster = new JButton();
        SwitchRoster.setBounds(275, 200, 200, 60);
        SwitchRoster.addActionListener(this);
        SwitchRoster.setText("Switch Roster");
        SwitchRoster.setFocusable(false);
        SwitchRoster.setVisible(true);
        SwitchRoster.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        SwitchRoster.setOpaque(true);
        SwitchRoster.setForeground(Color.white);
        SwitchRoster.setBackground(Color.DARK_GRAY);
        SwitchRoster.setBorder(BorderFactory.createEtchedBorder());

        RosterManagement = new JButton();
        RosterManagement.setBounds(275, 280, 200, 60);
        RosterManagement.addActionListener(this);
        RosterManagement.setText("Roster Management");
        RosterManagement.setFocusable(false);
        RosterManagement.setVisible(true);
        RosterManagement.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        RosterManagement.setOpaque(true);
        RosterManagement.setForeground(Color.white);
        RosterManagement.setBackground(Color.DARK_GRAY);
        RosterManagement.setBorder(BorderFactory.createEtchedBorder());

        CraftingTheJourney = new JButton();
        CraftingTheJourney.setBounds(275, 360, 200, 60);
        CraftingTheJourney.addActionListener(this);
        CraftingTheJourney.setText("Crafting The Journey");
        CraftingTheJourney.setFocusable(false);
        CraftingTheJourney.setVisible(true);
        CraftingTheJourney.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        CraftingTheJourney.setOpaque(true);
        CraftingTheJourney.setForeground(Color.white);
        CraftingTheJourney.setBackground(Color.DARK_GRAY);
        CraftingTheJourney.setBorder(BorderFactory.createEtchedBorder());

        FullPlayerList = new JButton();
        FullPlayerList.setBounds(275, 440, 200, 60);
        FullPlayerList.addActionListener(this);
        FullPlayerList.setText("Full Player List");
        FullPlayerList.setFocusable(false);
        FullPlayerList.setVisible(true);
        FullPlayerList.setFont(new Font("Hanzipen SC", Font.ITALIC, 25));
        FullPlayerList.setOpaque(true);
        FullPlayerList.setForeground(Color.white);
        FullPlayerList.setBackground(Color.DARK_GRAY);
        FullPlayerList.setBorder(BorderFactory.createEtchedBorder());

        // Frame setup
        frame.setSize(750, 570);
        frame.setTitle("N B A --- Manager");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background image
        ImageIcon background = new ImageIcon("mainbg.jpg");
        Image image = background.getImage();
        Image newimg = image.getScaledInstance(750, 570, java.awt.Image.SCALE_SMOOTH);
        background = new ImageIcon(newimg);
        frame.setIconImage(background.getImage());
        JLabel bg = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.white);
        bg.setIcon(background);
        bg.setBounds(0, 0, 750, 570);
        bg.setBorder(border);

        // Set title label
        JLabel title = new JLabel();
        title.setText("Welcome To NBA...");
        title.setForeground(new Color(255, 215, 0));
        title.setBounds(275, 0, 200, 60);
        title.setFont(new Font("Hanzipen SC", Font.BOLD, 25));
        title.setOpaque(false);
        title.setBorder(null);

        // Add components to frame
        frame.add(NewRoster);
        frame.add(SwitchRoster);
        frame.add(FullPlayerList);
        frame.add(CraftingTheJourney);
        frame.add(RosterManagement);
        frame.add(title);
        frame.add(bg);

        // Disable buttons if roster is empty
        if (roster.size() == 0) {
            SwitchRoster.setEnabled(false);
            RosterManagement.setEnabled(false);
            CraftingTheJourney.setEnabled(false);
        }

        // Make frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Button actions
        if (e.getSource().equals(NewRoster)) {
            new CreateRoster();
            if (Roster.existRoster.size() != 0) {
                SwitchRoster.setEnabled(true);
                RosterManagement.setEnabled(true);
                CraftingTheJourney.setEnabled(true);
            }
        }
        if (e.getSource().equals(FullPlayerList)) {
            new PlayerList();
        }
        if (e.getSource().equals(CraftingTheJourney)) {
            WeightedGraph nba = new WeightedGraph();
            nba = nba.createNBAGraph();
            nba.printEdges();
            new FrameGraph(nba);
        }
        if (e.getSource().equals(SwitchRoster)) {
            new SwitchRoster();
        }
        if (e.getSource().equals(RosterManagement)) {
            new RosterManagement();
        }
    }
}

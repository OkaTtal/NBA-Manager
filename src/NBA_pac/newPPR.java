package NBA_pac;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class newPPR extends JFrame {
    newPPR() {
        // Retrieve player ranking
        ArrayList<Player> ranking = new PlayerPerformanceRanking().getRanking(
                Roster.rbRoster(CreateRoster.rostername).getRoster());

        // Initialize data array for table
        Object[][] data = new Object[ranking.size()][3];
        String[] columnNames = {"Rank", "Name", "Score"};
        for (int i = 0; i < ranking.size(); i++) {
            data[i][0] = i + 1;
            data[i][1] = ranking.get(i).getName();
            data[i][2] = new PlayerPerformanceRanking().calculatePlayerScore(ranking.get(i));
        }

        // Create JTable with data and column names
        JTable tlist = new JTable(data, columnNames);

        // Create JScrollPane to display table
        JScrollPane sp = new JScrollPane(tlist);
        
        // Customize table appearance
        tlist.setBackground(Color.black);
        tlist.setForeground(Color.yellow);
        tlist.getTableHeader().setBackground(Color.black);
        tlist.getTableHeader().setForeground(Color.yellow);
        Font currentFont = tlist.getFont();
        tlist.setFont(new Font("Wawati SC", currentFont.getStyle(), currentFont.getSize()));
        
        // Set window title and size
        this.setTitle("Player Ranking");
        this.setResizable(false);
        this.add(sp);
        this.setSize(800, 400);
        this.setVisible(true);
    }
}

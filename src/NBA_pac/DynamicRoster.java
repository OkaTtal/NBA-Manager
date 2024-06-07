package NBA_pac;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class DynamicRoster extends JFrame {
	
    // Constructor
    DynamicRoster(ArrayList<Player> sproster) {
        // Initialize the list of players
        ArrayList<Player> list = sproster;
        
        // Create a 2D array to hold player information
        Object[][] data = new Object[list.size()][12];
        
        // Define column names for the table
        String[] columnNames = { "ID", "Name", "Position", "Height", "Weight", "Age", 
                                 "REB", "AST", "STL", "BLK", "PTS", "Salary" };

        // Populate the data array with player information
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getID();
            data[i][1] = list.get(i).getName();
            data[i][2] = list.get(i).getPosition();
            data[i][3] = list.get(i).getHeight();
            data[i][4] = list.get(i).getWeight();
            data[i][5] = list.get(i).getAge();
            data[i][6] = list.get(i).getReboundsPerGame();
            data[i][7] = list.get(i).getAssistsPerGame();
            data[i][8] = list.get(i).getStealsPerGame();
            data[i][9] = list.get(i).getBlocksPerGame();
            data[i][10] = list.get(i).getPointsPerGame();
            data[i][11] = list.get(i).getSalary();
        }

        // Create JTable with data and column names
        JTable tlist = new JTable(data, columnNames);
        
        // Wrap the table inside a scroll pane
        JScrollPane sp = new JScrollPane(tlist);
        
        // Customize table appearance
        tlist.setBackground(Color.black);
        tlist.setForeground(Color.yellow);
        tlist.getTableHeader().setBackground(Color.black);
        tlist.getTableHeader().setForeground(Color.yellow);
        Font currentFont = tlist.getFont();
        tlist.setFont(new Font("Wawati SC", currentFont.getStyle(), currentFont.getSize()));

        // Set window title and size
        this.setTitle("Dynamic-Player-List");
        this.setSize(800, 400);
        
        // Set window to non-resizable
        this.setResizable(false);
        
        // Add scroll pane to the window
        this.add(sp);
        
        // Make the window visible
        this.setVisible(true);
    }
}

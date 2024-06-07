package NBA_pac;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PlayerList extends JFrame {
    // Constructor
    PlayerList() {
        // Getting the full list of players
        ArrayList<Player> list = new ArrayList<Player>(Player.fullPlayerList());
        
        // Creating a 2D array to store player data
        Object[][] data = new Object[540][12];
        // Column names for the table
        String[] columnNames = { "ID", "Name", "Position", "Height", "Weight", "Age", "REB", "AST", "STL", "BLK", "PTS", "Salary" };
        
        // Populating the data array with player information
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
        
        // Creating a JTable with the data and column names
        JTable tlist = new JTable(data, columnNames);
        
        // Adding a JScrollPane to the table
        JScrollPane sp = new JScrollPane(tlist);
        // Setting the title of the frame
        this.setTitle("Player-List");
        // Setting frame properties
        this.setResizable(false);
        this.add(sp);
        this.setSize(800, 400);
        this.setVisible(true);
    }
}

package NBA_pac;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ShowCurrentRoster extends JFrame {
    ShowCurrentRoster() {
        // Get the current roster from the Roster class
        ArrayList<Player> list = Roster.rbRoster(CreateRoster.rostername).getRoster();
        
        // Create a 2D array to hold the data for the JTable
        Object[][] data = new Object[list.size()][12];
        
        // Define column names for the JTable
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

        // Create a JTable with the data and column names
        JTable tlist = new JTable(data, columnNames);
        
        // Create a JScrollPane to add scrolling functionality to the JTable
        JScrollPane sp = new JScrollPane(tlist);
        
        // Customize the appearance of the JTable
        tlist.setBackground(Color.black);
        tlist.setForeground(Color.yellow);
        tlist.getTableHeader().setBackground(Color.black);
        tlist.getTableHeader().setForeground(Color.yellow);
        Font currentFont = tlist.getFont();
        tlist.setFont(new Font("Wawati SC", currentFont.getStyle(), currentFont.getSize()));

        // Set the title of the JFrame
        this.setTitle("Current-Player-List");

        // Set JFrame properties
        this.setResizable(false);
        this.add(sp);
        this.setSize(800, 400);
        this.setVisible(true);
    }
}

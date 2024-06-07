package NBA_pac;

import java.awt.Color;
import java.awt.Font;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class listCEQ extends JFrame {
    
    listCEQ() {
        // Retrieve the queue of players from the current roster's contract exempt queue (CEQ)
        Queue<Player> queue = Roster.rbRoster(CreateRoster.rostername).CEQ.queue;

        // Initialize data array for table rows and column names array
        Object[][] data = new Object[queue.size()][2];
        String[] columnNames = {"Count", "Name"};
        
        // Fill the data array with player information from the CEQ queue
        int i = 0;
        for (Player player : queue) {
            data[i][0] = i + 1;
            data[i][1] = player.getName();
            i++;
        }

        // Create a JTable with the data and column names
        JTable tlist = new JTable(data, columnNames);
        
        // Create a scroll pane to contain the table
        JScrollPane sp = new JScrollPane(tlist);

        // Set table and table header colors and font
        tlist.setBackground(Color.black);
        tlist.setForeground(Color.yellow);
        tlist.getTableHeader().setBackground(Color.black);
        tlist.getTableHeader().setForeground(Color.yellow);
        Font currentFont = tlist.getFont();
        tlist.setFont(new Font("Wawati SC", currentFont.getStyle(), currentFont.getSize()));

        // Set JFrame properties
        this.setTitle("Player Contract");
        this.setResizable(false);
        this.add(sp);
        this.setSize(800, 400);
        this.setVisible(true);
    }
}

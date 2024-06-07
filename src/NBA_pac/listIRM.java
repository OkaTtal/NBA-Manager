package NBA_pac;

import java.awt.Color;
import java.awt.Font;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class listIRM extends JFrame {
    
    listIRM() {
        // Retrieve the stack of players from the current roster's injury reserve management (IRM)
        Stack<Player> stack = Roster.rbRoster(CreateRoster.rostername).IRM.stack;

        // Initialize data array for table rows and column names array
        Object[][] data = new Object[stack.size()][2];
        String[] columnNames = {"Name", "Injury"};
        
        // Fill the data array with player information from the IRM stack
        int i = 0;
        for (Player player : stack) {
            data[i][0] = player.getName();
            data[i][1] = player.getCondition();
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
        this.setTitle("Injury Stack");
        this.setResizable(false);
        this.add(sp);
        this.setSize(800, 400);
        this.setVisible(true);
    }
}

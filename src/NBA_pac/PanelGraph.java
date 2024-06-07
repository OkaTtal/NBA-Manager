package NBA_pac;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class PanelGraph extends JPanel implements ActionListener {
    // Constants for panel dimensions
    final int PANEL_WIDTH = 900;
    final int PANEL_HEIGHT = 600;
    
    // Images and buttons
    Image wgraph, aeroplane;
    JButton dfsButton;
    JButton bfsButton;
    
    // Labels for displaying information
    JLabel label;
    JLabel label2;
    
    // Timer for animation
    Timer timer;
    
    // Variables for tracking movement
    int xVelocity = 1;
    int yVelocity = 1;
    int xTarget = 0;
    int yTarget = 0;
    int x = 390;
    int y = 500;
    
    // Array of target points
    private Point[] targets;
    
    // Index of the current target
    int currentTargetIndex;
    
    // Total distance traveled and travel description
    int distanceTravel = 0;
    String travel = "";
    
    // Weighted graph
    WeightedGraph<String, Integer> nba;

    // Constructor
    PanelGraph(WeightedGraph<String, Integer> w) {
        nba = w;
        // Initializing the timer
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveTowardsTarget();
                repaint();
            }
        });
        // Setting up the panel
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        // Loading images
        wgraph = new ImageIcon("wgraph.png").getImage();
        aeroplane = new ImageIcon("aeroplane.png").getImage();
        // Creating buttons
        dfsButton = new JButton("DFS");
        bfsButton = new JButton("BFS");
        // Setting button positions and listeners
        dfsButton.setBounds(300, 500, 100, 50);
        dfsButton.addActionListener(this);
        dfsButton.setFocusable(false);
        bfsButton.setBounds(500, 500, 100, 50);
        bfsButton.addActionListener(this);
        bfsButton.setFocusable(false);
        // Adding buttons to the panel
        this.add(dfsButton);
        this.add(bfsButton);
        // Creating labels
        label = new JLabel();
        label.setFont(new Font("MV Boli", Font.BOLD, 15));
        label.setBounds(80, 530, 1000, 100);
        label.setVisible(false);
        this.add(label);
        label2 = new JLabel();
        label2.setFont(new Font("MV Boli", Font.BOLD, 30));
        label2.setForeground(Color.cyan);
        label2.setBounds(350, 530, 1000, 100);
        label2.setText("Travelling . . .");
        label2.setVisible(false);
        this.add(label2);
    }

    // Method to paint components
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        // Drawing the weighted graph image
        g2D.drawImage(wgraph, 0, 0, null);
        // Drawing the aeroplane image
        g2D.drawImage(aeroplane, x, y, null);
    }

    // Action listener for buttons
    public void actionPerformed(ActionEvent e) {
        label.setVisible(false);
        label2.setVisible(true);
        // Handling DFS and BFS button clicks
        if (e.getSource() == dfsButton) {
            DFS<String, Integer> dfs = new DFS(nba);
            ArrayList<String> list = dfs.dfs("Spurs");
            travel = list.toString();
            distanceTravel = dfs.calcDFSTotal(list);
            label.setText(travel + "   :   " + distanceTravel + "KM");
            targets = new Point[list.size()];
            for (int i = 0; i < list.size(); i++)
                targets[i] = new Point(nba.getVertex(list.get(i)).getX(), nba.getVertex(list.get(i)).getY());
            currentTargetIndex = 0;
            setTarget(targets[currentTargetIndex]);
            timer.start();
        } else if (e.getSource() == bfsButton) {
            BFS<String, Integer> bfs = new BFS(nba);
            ArrayList<String> path = bfs.BFSLogicalPath(bfs.bfs("Spurs"));
            ArrayList<String> list = bfs.bfs("Spurs");
            travel = list.toString();
            distanceTravel = bfs.calcBFSTotal(list);
            label.setText(travel + "   :   " + distanceTravel + "KM");
            targets = new Point[path.size()];
            for (int i = 0; i < path.size(); i++)
                targets[i] = new Point(nba.getVertex(path.get(i)).getX(), nba.getVertex(path.get(i)).getY());
            currentTargetIndex = 0;
            setTarget(targets[currentTargetIndex]);
            timer.start();
        }
    }

    // Method to set the target point
    public void setTarget(Point target) {
        xTarget = target.x;
        yTarget = target.y;
    }

    // Method to move the aeroplane towards the target point
    public void moveTowardsTarget() {
        if (x < xTarget) {
            x += xVelocity;
        } else if (x > xTarget) {
            x -= xVelocity;
        }
        if (y < yTarget) {
            y += yVelocity;
        } else if (y > yTarget) {
            y -= yVelocity;
        }
        // Checking if the aeroplane reached the target point
        if (x == xTarget && y == yTarget) {
            currentTargetIndex++;
            if (currentTargetIndex < targets.length) {
                setTarget(targets[currentTargetIndex]);
            } else {
                // Stopping the timer and displaying the final label
                label2.setVisible(false);
                label.setVisible(true);
                timer.stop();
            }
        }
    }
}

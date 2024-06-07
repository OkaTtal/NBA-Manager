package NBA_pac;

import java.awt.*;
import javax.swing.*;

public class FrameGraph extends JFrame {
    PanelGraph panel;

    // Constructor
    FrameGraph(WeightedGraph<String,Integer> w) {
        // Set title
        this.setTitle("NBA Graph");

        // Create PanelGraph instance with the given weighted graph
        panel = new PanelGraph(w);

        // Add the panel to the frame
        this.add(panel);

        // Pack the frame to fit the preferred size of subcomponents
        this.pack();

        // Center the frame on the screen
        this.setLocationRelativeTo(null);

        // Set the frame visible
        this.setVisible(true);
    }
}

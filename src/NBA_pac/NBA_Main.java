package NBA_pac;

import java.awt.*;
import javax.swing.*;

/**
 * Main class to launch the NBA application.
 */
public class NBA_Main {
    // Static variable to keep track of the roster count
    public static int rosterCount = 0;

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create and display the main frame of the application
        new MainFrame();
    }
}

/*
 * This class manages the injury reserve, allowing players to be added, removed, and displayed.
 */
package NBA_pac;

import java.util.Stack;
import javax.swing.JOptionPane;

public class InjuryReserveManagement {
    public Stack<Player> stack;
    
    /*
     * Constructor for InjuryReserveManagement class.
     * Initializes the stack to store injured players.
     */
    public InjuryReserveManagement() {
        this.stack = new Stack<>();
    }

    /*
     * Method to add a player to the injury reserve.
     * Sets the player's injury condition and notifies the user.
     */
    public void addToInjuryReserve(Player player, String injury) {
        if(injury != null && !injury.trim().isEmpty()) {
            stack.push(player);
            player.setCondition(injury);
            System.out.println("-- Adding Player to Injury Reserve --");
            System.out.println("Player: " + player.getName());
            System.out.println("Injury: " + player.getCondition());
            System.out.println("Status: Added to Injury Reserve");
            JOptionPane.showMessageDialog(null, "Status: Added to Injury Reserve: " + player.getName());
            System.out.println();
            // Update player's injury condition in the roster
            for(Player player1: Roster.rbRoster(CreateRoster.rostername).getRoster()) {
                if(player1.getID() == player.getID()) {
                    player1.setCondition(injury);
                }
            }
        } else {
            // Display error message if injury information is missing
            injury = "What're you doing?";
            stack.push(player);
            player.setCondition(injury);
            JOptionPane.showMessageDialog(null, "Status: Added why not set injury? : " + player.getName());
            // Update player's injury condition in the roster
            for(Player player1: Roster.rbRoster(CreateRoster.rostername).getRoster()) {
                if(player1.getID() == player.getID()) {
                    player1.setCondition(injury);
                }
            }
        }
    }

    /*
     * Method to remove a player from the injury reserve.
     * Returns the player and updates their status to "Healthy".
     */
    public Player removeFromInjuryReserve() {
        try {
            Player player = stack.pop();
            // Update player's status in the roster
            Roster.rbRoster(CreateRoster.rostername).idsCheck.add(player.getID());
            System.out.println("-- Removing Player from Injury Reserve --");
            System.out.println("Player: " + player.getName());
            System.out.println("Status: Cleared to Play");
            System.out.println();
            JOptionPane.showMessageDialog(null, "Status: Cleared to Injury Reserve: " + player.getName());
            // Update player's injury condition in the roster
            for(Player player1: Roster.rbRoster(CreateRoster.rostername).getRoster()) {
                if(player1.getID() == player.getID()) {
                    player1.setCondition("Healthy");
                }
            }
            return player;
        } catch (Exception e) {
            // Handle empty injury reserve
            System.out.println("Injury reserve is empty.");
            JOptionPane.showMessageDialog(null, "Injury reserve is empty!");
        }
        return null;
    }

    /*
     * Method to display the list of injured players in the injury reserve.
     */
    public void displayInjuredPlayers() {
        if (!stack.isEmpty()) {
            System.out.println("List of injured players:");
            for (Player player : stack) {
                System.out.println(player.getName() + " | " + player.getCondition());
            }
            System.out.println();
        } else {
            // Display message when injury reserve is empty
            System.out.println("Injury reserve is empty.");
            System.out.println();
        }
    }
}

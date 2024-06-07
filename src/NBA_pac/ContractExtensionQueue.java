package NBA_pac;

import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 * This class manages a queue of players who are in negotiation for contract extensions.
 */
public class ContractExtensionQueue {
    
    public Queue<Player> queue; // Queue to store players in negotiation
    public Queue<String> nameq = new LinkedList<>(); // Queue to store player names to check for duplicates

    /**
     * Constructor to initialize the queues.
     */
    public ContractExtensionQueue() {
        this.queue = new LinkedList<>();
    }
    
    /**
     * Adds a player to the contract extension queue.
     *
     * @param player The player to be added to the queue.
     */
    public void enqueue(Player player) {
        if (!nameq.contains(player.getName())) { // Check if the player is not already in the queue
            queue.offer(player); // Add player to the queue
            nameq.offer(player.getName()); // Add player name to the name queue
            player.setContract("in negotiation"); // Set player's contract status to "in negotiation"
            // Update the player's contract status in the roster
            for (Player player1 : Roster.rbRoster(CreateRoster.rostername).getRoster()) {
                if (player1.getID() == player.getID()) {
                    player1.setContract("in negotiation");
                }
            }
            // Notify that the player has been added to the contract extension queue
            JOptionPane.showMessageDialog(null, "Status: Added to Contract Extension Queue: " + player.getName());
        } else {
            // Notify that the player is already in the contract extension queue
            JOptionPane.showMessageDialog(null, "Existed player in Contract Extension Queue", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Removes a player from the contract extension queue and renews their contract.
     *
     * @return The player whose contract has been renewed.
     */
    public Player dequeue() {
        Player nextPlayer = queue.poll(); // Remove the next player from the queue
        try {
            // Update the player's contract status to "Yes" in the roster
            for (Player player1 : Roster.rbRoster(CreateRoster.rostername).getRoster()) {
                if (player1.getID() == nextPlayer.getID()) {
                    player1.setContract("Yes");
                }
            }
            nextPlayer.setContract("Yes"); // Set player's contract status to "Yes"
            Roster.rbRoster(CreateRoster.rostername).idqCheck.add(nextPlayer.getID()); // Add player's ID to the ID check queue
            nameq.poll(); // Remove player's name from the name queue

            if (nextPlayer != null) {
                // Notify that the player's contract has been renewed
                JOptionPane.showMessageDialog(null, "Status: Contract Renewed, remove: " + nextPlayer.getName());
            }
        } catch (Exception e) {
            // Notify that there are no players in the contract extension queue
            JOptionPane.showMessageDialog(null, "No player in Contract Extension Queue", null, JOptionPane.ERROR_MESSAGE);
        }
        return nextPlayer;
    }
}

package NBA_pac;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Searching {
    static ArrayList<Player> plist = Roster.rbRoster(CreateRoster.rostername).getRoster();

    public static ArrayList<Player> dynamicSearching(int hf, double h, int wf, double w, String position) {
        ArrayList<Player> result = new ArrayList<>();
        int heightfil = hf;
        double height = h;
        int weightfil = wf;
        double weight = w;
        int i = 0;
        // Iterate through the player list and filter based on criteria
        while (i < plist.size()) {
            Player fil = plist.get(i);
            if (heightfil == 1 && weightfil == 1) {
                if (fil.getHeight() >= height && fil.getWeight() >= weight && fil.getPosition().equals(position)) {
                    result.add(fil);
                }
            } else if (heightfil == 1 && weightfil == 2) {
                if (fil.getHeight() >= height && fil.getWeight() <= weight && fil.getPosition().equals(position)) {
                    result.add(fil);
                }
            } else if (heightfil == 2 && weightfil == 1) {
                if (fil.getHeight() <= height && fil.getWeight() >= weight && fil.getPosition().equals(position)) {
                    result.add(fil);
                }
            } else if (heightfil == 2 && weightfil == 2) {
                if (fil.getHeight() <= height && fil.getWeight() <= weight && fil.getPosition().equals(position)) {
                    result.add(fil);
                }
            } else {
                // Show error message for invalid criteria
                JOptionPane.showMessageDialog(null, "Invalid type!", null, JOptionPane.ERROR_MESSAGE);
                return result;
            }
            i++;
        }
        // Print the specific roster matching the criteria
        printSpecificRoster(result);
        // Show the number of results as a message dialog
        JOptionPane.showMessageDialog(null, "\nResult number: " + result.size());
        return result;
    }

    static public void printSpecificRoster(ArrayList<Player> specific) {
        // Print the header for the specific roster
        System.out.println("Player List :");
        System.out.println();
        System.out.println(
                "Name                     | Age | Height | Weight | Position | Salary | Points | Rebounds | Assists | Steals | Blocks");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------");
        // Print each player's information in the specific roster
        for (Player player : specific) {
            String playerName = String.format("%-25s", player.getName());
            String playerAge = String.format("%4s", player.getAge());
            String playerHeight = String.format("%6s", player.getHeight());
            String playerWeight = String.format("%6s", player.getWeight());
            String playerPosition = String.format("%8s", player.getPosition());
            String playerSalary = String.format("%5s", player.getSalary());
            String playerPPG = String.format("%6s", player.getPointsPerGame());
            String playerRPG = String.format("%8s", player.getReboundsPerGame());
            String playerAPG = String.format("%7s", player.getAssistsPerGame());
            String playerSPG = String.format("%6s", player.getStealsPerGame());
            String playerBPG = String.format("%6s", player.getBlocksPerGame());

            String playerInfo = playerName + "|" + playerAge + " | " + playerHeight + " | " + playerWeight + " | "
                    + playerPosition + " | " + playerSalary + " | " + playerPPG + " | " + playerRPG + " | " + playerAPG
                    + " | " + playerSPG + " | " + playerBPG;

            System.out.println(playerInfo);
        }
    }
}

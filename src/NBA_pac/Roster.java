package NBA_pac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Roster {
    // Roster attributes
    private int numOfPlayer = 0;
    private String teamName;
    public ArrayList<Player> roster;
    private int forwardPlayer;
    private int guardPlayer;
    private int centrePlayer;
    private int minPlayers = 10;
    private int maxPlayers = 15;
    private int minGuards = 2;
    private int minForwards = 2;
    private int minCenters = 2;
    ArrayList<Integer> idCheck = new ArrayList<Integer>();
    Queue<Integer> idqCheck = new LinkedList<Integer>();
    ArrayList<Integer> idsCheck = new ArrayList<Integer>();

    // Salary cap details
    private double totalSalaryCap = 20000;
    double remainingSalaryCap = totalSalaryCap;
    public static ArrayList<Roster> existRoster = new ArrayList<Roster>();
    public static ArrayList<String> existName = new ArrayList<String>();
    ContractExtensionQueue CEQ = new ContractExtensionQueue();
    InjuryReserveManagement IRM = new InjuryReserveManagement();
    PlayerPerformanceRanking PPR = new PlayerPerformanceRanking();
    
    // Constructor to create a new roster with the given team name
    public Roster(String teamName) {
        this.teamName = teamName;
        this.roster = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Main method for testing
    }

    // Method to add a player to the roster
    public void addPlayer(Player player) {
        if (roster.size() < maxPlayers && remainingSalaryCap >= player.getSalary()) {
            roster.add(player);
            numOfPlayer++;
            switch (player.getPosition().toUpperCase()) {
                case "F":
                    forwardPlayer++;
                    break;
                case "G":
                    guardPlayer++;
                    break;
                case "C":
                    centrePlayer++;
                    break;
            }
            remainingSalaryCap -= player.getSalary();
            System.out.println(player.getName() + " added to " + teamName + ".");
            System.out.println("");
        } else {
            System.out.println("Cannot add " + player.getName() + ". Roster is full or salary cap exceeded.");
            System.out.println("");
        }
    }

    // Method to remove a player from the roster
    public void removePlayer(Player player) {
        if (roster.size() > 0) {
            roster.remove(player);
            numOfPlayer--;
            switch (player.getPosition().toUpperCase()) {
                case "F":
                    forwardPlayer--;
                    break;
                case "G":
                    guardPlayer--;
                    break;
                case "C":
                    centrePlayer--;
                    break;
            }
            remainingSalaryCap += player.getSalary();
            System.out.println(player.getName() + " removed from " + teamName + ".");
            System.out.println("");
        }
    }

    // Method to check if the roster meets the minimum requirements
    public boolean checkRoster() {
        return (forwardPlayer >= minForwards && centrePlayer >= minCenters && guardPlayer >= minGuards
                && remainingSalaryCap >= 0 && Roster.rbRoster(CreateRoster.rostername).numOfPlayer >= 10
                && Roster.rbRoster(CreateRoster.rostername).numOfPlayer <= 15);
    }

    // Method to print the roster
    public void printRoster() {
        System.out.println("Player List :");
        System.out.println();
        System.out.println(
                "Name                     | Age | Height | Weight | Position | Salary | Points | Rebounds | Assists | Steals | Blocks");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------");
        for (Player player : roster) {
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

    // Overloaded method to print a given list of players
    public void printRoster(ArrayList<Player> result) {
        System.out.println("Player List :");
        System.out.println();
        System.out.println(
                "Name                     | Age | Height | Weight | Position | Salary | Points | Rebounds | Assists | Steals | Blocks");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------");
        for (Player player : result) {
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

    // Method to get a roster by team name
    public static Roster rbRoster(String tn) {
        for (int i = 0; i < existRoster.size(); i++) {
            if (existRoster.get(i).teamName.equals(tn)) {
                return existRoster.get(i);
            }
        }
        return null;
    }

    // Getter methods for roster attributes
    public String getTeamName() {
        return teamName;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public int getForwardPlayer() {
        return forwardPlayer;
    }

    public int getGuardPlayer() {
        return guardPlayer;
    }

    public int getCentrePlayer() {
        return centrePlayer;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinGuards() {
        return minGuards;
    }

    public int getMinForwards() {
        return minForwards;
    }

    public int getMinCenters() {
        return minCenters;
    }

    public double getTotalSalaryCap() {
        return totalSalaryCap;
    }

    public double getRemainingSalaryCap() {
        return remainingSalaryCap;
    }

    public int getPlayerNum() {
        return this.numOfPlayer;
    }

    // Method to get a player by ID
    public Player getPlayer(int id) {
        for (int i = 0; i < roster.size(); i++) {
            Player temp = roster.get(i);
            int checkid = temp.getID();
            if (checkid == id) {
                return temp;
            }
        }
        return null;
    }
}

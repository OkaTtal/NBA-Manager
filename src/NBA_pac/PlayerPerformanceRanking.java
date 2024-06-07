package NBA_pac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlayerPerformanceRanking {
    // Weight coefficients for different player attributes
    private static double pointsWeight = 20.0;
    private double reboundsWeight;
    private double assistsWeight;
    private double stealsWeight;
    private double blocksWeight;

    // Method to calculate the score of a player based on their performance
    double calculatePlayerScore(Player player) {
        // Assigning weights based on player position
        if (player.getPosition().equals("Center")) {
            reboundsWeight = 30.0;
            blocksWeight = 30.0;
            stealsWeight = 10.0;
            assistsWeight = 10.0;
        } else if (player.getPosition().equals("Guard")) {
            assistsWeight = 30.0;
            stealsWeight = 30.0;
            blocksWeight = 10.0;
            reboundsWeight = 10.0;
        } else {
            assistsWeight = 20.0;
            reboundsWeight = 20.0;
            blocksWeight = 20.0;
            stealsWeight = 20.0;
        }

        // Calculating player score
        double playerScore = (player.getPointsPerGame() * pointsWeight / 100.0) +
                (player.getReboundsPerGame() * reboundsWeight / 100.0) +
                (player.getAssistsPerGame() * assistsWeight / 100.0) +
                (player.getStealsPerGame() * stealsWeight / 100.0) +
                (player.getBlocksPerGame() * blocksWeight / 100.0);

        // Scaling player score
        playerScore *= 10;
        if (playerScore >= 100) {
            playerScore = 100.0;
        }
        double playerScore1 = Math.round(playerScore * 10) / 10.0;

        return playerScore1;
    }

    // Method to print the player performance ranking
    public void printRanking(ArrayList<Player> players) {
        System.out.println("-- Player Performance Ranking --");

        // Sorting players based on their scores
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Double.compare(calculatePlayerScore(p2), calculatePlayerScore(p1));
            }
        });

        // Printing player ranking
        int rank = 1;
        for (Player player : players) {
            System.out.println("Player: " + player.getName());
            System.out.println("Composite score: " + calculatePlayerScore(player));
            System.out.println("Rank: " + rank);
            rank++;
        }
    }

    // Method to get the ranking of players
    public ArrayList<Player> getRanking(ArrayList<Player> players) {
        System.out.println("-- Player Performance Ranking --");

        // Sorting players based on their scores
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Double.compare(calculatePlayerScore(p2), calculatePlayerScore(p1));
            }
        });

        return players;

    }
}

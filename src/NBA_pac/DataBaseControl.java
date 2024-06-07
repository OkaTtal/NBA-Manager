package NBA_pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class provides methods to manage a MySQL database for an NBA Manager application.
 */
public class DataBaseControl {
    // Database connection constants
    private static final String URL = "jdbc:mysql://localhost:3306/NBA";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    /**
     * Establishes and returns a connection to the database.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Drops a table if it exists.
     * 
     * @param rosterName the name of the roster table to drop.
     */
    public static void dropTableIfExists(String rosterName) {
        String sql = "DROP TABLE IF EXISTS " + rosterName;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a table if it does not already exist.
     * 
     * @param rosterName the name of the roster table to create.
     */
    public static void createTableIfNotExists(String rosterName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + rosterName + " (" +
                     "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                     "Name TEXT NOT NULL, " +
                     "Position TEXT NOT NULL, " +
                     "Height DOUBLE NOT NULL, " +
                     "Weight DOUBLE NOT NULL, " +
                     "Age INT NOT NULL, " +
                     "REB DOUBLE NOT NULL, " +
                     "AST DOUBLE NOT NULL, " +
                     "STL DOUBLE NOT NULL, " +
                     "BLK DOUBLE NOT NULL, " +
                     "PTS DOUBLE NOT NULL, " +
                     "Salary INT NOT NULL, "+
                     "Contract TEXT NOT NULL, " +
                     "Injury TEXT NOT NULL)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts or updates a player in the specified roster table.
     * 
     * @param rosterName the name of the roster table.
     * @param player the player to insert or update.
     */
    public static void insertOrUpdatePlayer(String rosterName, Player player) {
        String insertOrUpdateSql = "INSERT INTO " + rosterName + " (Name, Position, Height, Weight, Age, REB, AST, STL, BLK, PTS, Salary, Contract, Injury) " +
                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                                   "ON DUPLICATE KEY UPDATE " +
                                   "Position = VALUES(Position), " +
                                   "Height = VALUES(Height), " +
                                   "Weight = VALUES(Weight), " +
                                   "Age = VALUES(Age), " +
                                   "REB = VALUES(REB), " +
                                   "AST = VALUES(AST), " +
                                   "STL = VALUES(STL), " +
                                   "BLK = VALUES(BLK), " +
                                   "PTS = VALUES(PTS), " +
                                   "Salary = VALUES(Salary), "+
                                   "Contract = VALUES(Contract), " +
                                   "Injury = VALUES(Injury)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertOrUpdateSql)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getPosition());
            pstmt.setDouble(3, player.getHeight());
            pstmt.setDouble(4, player.getWeight());
            pstmt.setInt(5, player.getAge());
            pstmt.setDouble(6, player.getReboundsPerGame());
            pstmt.setDouble(7, player.getAssistsPerGame());
            pstmt.setDouble(8, player.getStealsPerGame());
            pstmt.setDouble(9, player.getBlocksPerGame());
            pstmt.setDouble(10, player.getPointsPerGame());
            pstmt.setDouble(11, player.getSalary());
            pstmt.setString(12, player.getContract());
            pstmt.setString(13, player.getCondition());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

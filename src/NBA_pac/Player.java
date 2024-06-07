package NBA_pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Player {
    // Player attributes
    private String name;
    private int age;
    private int id;

    // Static variable to keep track of player count
    public static int count = 0;
    private String position;
    private double height; // in meters
    private double weight; // in kilograms
    private double salary;
    private String condition = "Healthy";

    // Player statistics
    private double pointsPerGame;
    private double reboundsPerGame;
    private double assistsPerGame;
    private double stealsPerGame;
    private double blocksPerGame;
    private String contract = "Yes";
    
    // Database connection details
    static String url = "jdbc:mysql://localhost:3306/NBA";
    static String user = "root";
    static String password = "password";

    // Default constructor
    public Player() {}

    // Constructor to create a player object by fetching data from the database
    public Player(int id) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            // SQL query to fetch player data by ID
            String query = "SELECT * FROM nba_player where ID = " + id;
            ResultSet re = statement.executeQuery(query);
            // If a matching player is found, initialize the player object with the fetched data
            if (re.next()) {
                this.id = id;
                this.name = re.getString("Name");
                this.height = re.getDouble("Height") / 100;
                this.weight = re.getDouble("Weight");
                this.age = re.getInt("age");
                this.salary = re.getInt("Salary");
                this.position = re.getString("Position");
                this.reboundsPerGame = round(re.getDouble("REB") / re.getDouble("GP"));
                this.assistsPerGame = round(re.getDouble("AST") / re.getDouble("GP"));
                this.stealsPerGame = round(re.getDouble("STL") / re.getDouble("GP"));
                this.blocksPerGame = round(re.getDouble("BLK") / re.getDouble("GP"));
                this.pointsPerGame = round(re.getDouble("PTS") / re.getDouble("GP"));
                System.out.println("New player created!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to round a double value to one decimal place
    static double round(double value) {
        return Math.round(value * 10) / 10.0;
    }

    // Method to get the contract status
    public String getContract() {
        return contract;
    }

    // Method to set the contract status
    public void setContract(String contract) {
        this.contract = contract;
    }

    // Method to get a list of all players by fetching data from the database
    public static ArrayList<Player> fullPlayerList() {
        int id = 1;
        ArrayList<Player> list = new ArrayList<Player>();
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            // Loop through player IDs and fetch their data
            while (id <= 540) {
                String query = "SELECT * FROM nba_player where id = " + id;
                ResultSet re = statement.executeQuery(query);
                if (re.next()) {
                    Player player = new Player();
                    player.id = id;
                    player.name = re.getString("Name");
                    player.height = re.getDouble("Height") / 100;
                    player.weight = re.getDouble("Weight");
                    player.age = re.getInt("age");
                    player.salary = re.getInt("Salary");
                    player.position = re.getString("Position");
                    player.reboundsPerGame = round(re.getDouble("REB") / re.getDouble("GP"));
                    player.assistsPerGame = round(re.getDouble("AST") / re.getDouble("GP"));
                    player.stealsPerGame = round(re.getDouble("STL") / re.getDouble("GP"));
                    player.blocksPerGame = round(re.getDouble("BLK") / re.getDouble("GP"));
                    player.pointsPerGame = round(re.getDouble("PTS") / re.getDouble("GP"));
                    id++;
                    list.add(player);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to print player information
    void getInfo() {
        System.out.println("Name: " + this.name + " Age: " + this.age + "\nHeight: " + this.height + "m; Weight: "
                + this.weight + "kg\nPointsPerGame: " + this.pointsPerGame + "; assistsPerGame: " + this.reboundsPerGame
                + "\nStealsPerGame: " + this.stealsPerGame + "; BlocksPerGame: " + this.blocksPerGame
                + "\nReboundsPerGame: " + this.reboundsPerGame);
    }

    // Getter and setter methods for player attributes
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(double pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public double getReboundsPerGame() {
        return reboundsPerGame;
    }

    public void setReboundsPerGame(double reboundsPerGame) {
        this.reboundsPerGame = reboundsPerGame;
    }

    public double getAssistsPerGame() {
        return assistsPerGame;
    }

    public void setAssistsPerGame(double assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public double getStealsPerGame() {
        return stealsPerGame;
    }

    public void setStealsPerGame(double stealsPerGame) {
        this.stealsPerGame = stealsPerGame;
    }

    public double getBlocksPerGame() {
        return blocksPerGame;
    }

    public void setBlocksPerGame(double blocksPerGame) {
        this.blocksPerGame = blocksPerGame;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getID() {
        return this.id;
    }
}

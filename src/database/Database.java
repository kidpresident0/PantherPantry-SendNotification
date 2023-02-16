package database;
import java.sql.*;

/**
 * This is the Database class that will retrieve information needed to log in a user as well as add a user to the database.
 * @author Sevin Webb
 * @version 2023.07.02
 */


// connecting to the database
public class Database {
    private static final String url = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
    private static final String username = "234a_Null";
    private static final String password = "456$%^234a_Null";

    // Queries
    private static final String CREATE_USER_ACCOUNT = "INSERT INTO USERS " +
            "(username, firstName, lastName, userEmail, userPassword, userRole) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_EMAIL = "SELECT userEmail FROM USERS WHERE userEmail = ?";
    private static final String GET_USERNAME = "SELECT username FROM USERS WHERE username = ?";
    private static final String GET_PASSWORD_USERNAME = "SELECT userPassword FROM USERS WHERE username = ?";
    private static final String GET_PASSWORD_EMAIL = "SELECT userPassword FROM USERS WHERE userEmail = ?";

    // The one and only connection object
    private static Connection conn = null;

    public static void connect() {
        if (conn != null)
            return;
        try {
            // Create a database connection with the given username and password.
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Error! Couldn't connect to the database!");
            e.printStackTrace();
        }
    }

    // Adds a new subscriber to the Users table.
    public static void addSubscriber(String username, String firstName, String lastName, String email, String password) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(CREATE_USER_ACCOUNT);
            stmt.setString(1, username);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setString(6, "subscriber");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves the email that the user entered to verify that it matches an entry in the Users table
    public static boolean checkEmail(String name) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_EMAIL);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return emailDB;
        return false;
    }

    // Retrieves the username that the user entered to verify that it matches an entry in the Users table
    public static boolean checkUsername(String name) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_USERNAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return usernameDB;
        return false;
    }

    // Retrieves the password that belongs to the email address the user entered
    public static String checkPasswordEmail(String name) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_PASSWORD_EMAIL);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString(1);
                return password;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    // Retrieves the password that belongs to the username the user entered
    public static String checkPasswordUsername(String name) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_PASSWORD_USERNAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString(1);
                return password;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }
}
package database;

import java.sql.*;

import logic.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Flow;

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
    private static String GET_SUBSCRIBER_EMAIL = "SELECT userEmail FROM USERS WHERE userRole = 'subscriber'";

    private static String GET_ALL_SUBSCRIBER_INFO = "SELECT userID , username , firstName , lastName , userEmail , userPassword"
            + ", salt , userRole FROM 234a_Null.dbo.USERS";
    private static String WRITE_NOTIFICATION_INFO = "INSERT INTO NOTIFICATIONS (subject, messageBody, sentBy, sentDateTime,"
            + "subscriberCount) VALUES (?,?,?,?,?)";

    private static final String CREATE_USER_ACCOUNT = "INSERT INTO USERS " +
            "(username, firstName, lastName, userEmail, userPassword, userRole) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_EMAIL = "SELECT userEmail FROM USERS WHERE userEmail = ?";
    private static final String GET_USERNAME = "SELECT username FROM USERS WHERE username = ?";
    private static final String GET_PASSWORD_USERNAME = "SELECT userPassword FROM USERS WHERE username = ?";
    private static final String GET_PASSWORD_EMAIL = "SELECT userPassword FROM USERS WHERE userEmail = ?";

    private static ArrayList<User> subscribers = null;
    private static Integer currentSubscriberCount = 0;

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

    /**
     * Returns a list of subscriber email addresses from the USERS database. If an error occurs, a stack
     * trace is printed to standard error and an empty list is returned.
     *
     * @return a list of subscriber email addresses.
     */
    private static ArrayList<User> readSubscribersEmail() {
        ArrayList<User> subscribers = new ArrayList<>();

        connect();
        try (
             PreparedStatement stmt = conn.prepareStatement(GET_SUBSCRIBER_EMAIL);
             ResultSet rs = stmt.executeQuery()) {
            // Iterate through subscribers in the database and add their email addresses to a list.
            while (rs.next()) {
                User user = new User(
                        rs.getString("userEmail"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        0
                );
                subscribers.add(user);
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return subscribers;

    }

    /**
     * Makes the subscriber email addresses publicly available for other classes.
     *
     * @return the list of subscriber email addresses
     */
    public static ArrayList<User> getGetSubscriberEmail() {
        subscribers = readSubscribersEmail();
        currentSubscriberCount = subscribers.size();
        return subscribers;
    }

    /**
     * Counts the number of subscribers that will receive a notification
     *
     * @return a count for subscribers.
     */
    public static int subCount() {
        getGetSubscriberEmail();
        return currentSubscriberCount;
    }

    /**
     * Writes the details of each notification to the notification log database.
     *
     * @param subject         the subject of the notification
     * @param messageBody     the body of the notification
     * @param sentBy          pantry staff that sent the notification
     * @param subscriberCount the number of subscribers that received the notification
     */
    private static void writeNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount) {
        try {
            connect();
            PreparedStatement stmt = conn.prepareStatement(WRITE_NOTIFICATION_INFO);
            // Set the values for the INSERT statement
            stmt.setString(1, subject);
            stmt.setString(2, messageBody);
            stmt.setString(3, sentBy);
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(5, subscriberCount);
            // Execute the INSERT statement
            stmt.executeUpdate();

            System.out.println("Record inserted successfully");
        } catch (SQLException e) {
            // Handle errors for JDBC
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Public method to be called by the SendNotification so that notification details
     * can be recorded in the database.
     *
     * @param subject         the subject of the notification
     * @param messageBody     the body of the notification
     * @param sentBy          pantry staff that sent the notification
     * @param subscriberCount the number of subscribers that received the notification
     */
    public static void recordNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount) {
        writeNotificationInfo(subject, messageBody, sentBy, subscriberCount);
    }
}
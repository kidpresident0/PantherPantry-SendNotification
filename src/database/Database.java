package database;

import logic.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Database class. It will be used to write to and read information from the database.
 * For this story that mean collecting email addresses of subscribers and recording notification details.
 *
 * @author John Christian
 * @version 2022.01.26
 */
public class Database {
    static String url = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
    static String username = "234a_Null";
    static String password = "456$%^234a_Null";
    static String GET_SUBSCRIBER_EMAIL = "SELECT userEmail FROM USERS WHERE userRole = 'subscriber'";

    static String GET_ALL_SUBSCRIBER_INFO = "SELECT userID , username , firstName , lastName , userEmail , userPassword"
        + ", salt , userRole FROM 234a_Null.dbo.USERS";
    static String WRITE_NOTIFICATION_INFO = "INSERT INTO NOTIFICATIONS (subject, messageBody, sentBy, sentDateTime,"
        + "subscriberCount) VALUES (?,?,?,?,?)";


    private final ArrayList<User> subscribers;

    /**
     * Create a Database object
     * Read  the subscriber email addresses from the database and creates a list of them.
     */
    public Database() {
        subscribers = readSubscribersEmail();

    }

    /**
     * Creates a connection to the database.
     *
     * @return connection to the database.
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    /**
     * Returns a list of subscriber email addresses from the USERS database. If an error occurs, a stack
     * trace is printed to standard error and an empty list is returned.
     *
     * @return a list of subscriber email addresses.
     */
    private ArrayList<User> readSubscribersEmail() {
        ArrayList<User> subscribers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_SUBSCRIBER_EMAIL);
             ResultSet rs = stmt.executeQuery()) {
            // Iterate through subscribers in the database and add their email addresses to a list.
            while (rs.next()) {
                User user = new User(rs.getString("userEmail"));
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
    public ArrayList<User> getGetSubscriberEmail() {
        return subscribers;
    }

    /**
     * Counts the number of subscribers that will receive a notification
     *
     * @return a count for subscribers.
     */
    public int subCount() {
        return this.subscribers.size();
    }

    /**
     * Writes the details of each notification to the notification log database.
     *
     * @param subject         the subject of the notification
     * @param messageBody     the body of the notification
     * @param sentBy          pantry staff that sent the notification
     * @param subscriberCount the number of subscribers that received the notification
     */
    private void writeNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(WRITE_NOTIFICATION_INFO)) {
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
    public void recordNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount) {
        writeNotificationInfo(subject, messageBody, sentBy, subscriberCount);
    }
}




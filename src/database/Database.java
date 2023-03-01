package database;

/**
 * This Database.java file connects to a SQL database, creates table templates
 * with given columns in the database, and adds the new template to the table
 * in the database with one created query (so far).
 * @author Kate White, Sevin Webb, John, Christian
 * @version 2023.18.02
 */

import logic.Log;
import logic.Template;
import logic.TemplateName;
import logic.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

// connecting to the database
public class Database {
    private static final String url = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
    private static final String username = "234a_Null";
    private static final String password = "456$%^234a_Null";

    // creation of queries
    private static final String CREATE_TEMPLATE_SQL = "INSERT INTO TEMPLATES " +
            "(UserID, TemplateName, Subject, TemplateText) " +
            "VALUES (?, ?, ?, ?)";

    private static final String LIST_TEMPLATES_SQL = "SELECT ID, TemplateName FROM TEMPLATES";

    private static final String getTemplateSql = "SELECT UserID, TemplateName, Subject, TemplateText FROM TEMPLATES WHERE ID = ?";

    private static String GET_SUBSCRIBER_EMAIL = "SELECT userEmail FROM USERS WHERE userRole = 'subscriber'";

    private static String GET_SUBSCRIBER_PHONE = "SELECT userPhone FROM USERS WHERE userRole = 'subscriber'";

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
    private static final String FIND_REVIEW_NOTIFICATION_QUERY =
            "SELECT sentBy,  sentDateTime, subject, messageBody, subscriberCount " + "FROM NOTIFICATIONS " +
                    "WHERE  sentDateTime BETWEEN ? AND ?;";

    private static ArrayList<User> subscribersEmails = null;
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

    // Adds a new template to the table in database.
    public static void createTemplate(Template template) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(CREATE_TEMPLATE_SQL);
            stmt.setInt(1, template.getUserID());
            stmt.setString(2, template.getTemplateName());
            stmt.setString(3, template.getSubject());
            stmt.setString(4, template.getTemplateText());
            stmt.executeUpdate();
        } catch (SQLException e) {
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

    // creates of the array of Template Names objects
    // retrieves the stored value from the database
    public static List<TemplateName> listTemplates(int id) {
        connect();

        List<TemplateName> list = new ArrayList<TemplateName>();

        try {
            PreparedStatement stmt = conn.prepareStatement(LIST_TEMPLATES_SQL);
            //stmt.setInt(1, id);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                list.add(new TemplateName(results.getInt(1), results.getString(2)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return emailDB;
        return null;
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
        return null;
    }

    // sets the ID to the 1
    // retrieves the value from the database
    public static Template getTemplate(int id) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(getTemplateSql);
            stmt.setInt(1, id);

            ResultSet results = stmt.executeQuery();
            results.next();    // starts reading from the first assigned column in the database

            Template template = new Template(
                    results.getInt(1),
                    results.getString(2),
                    results.getString(3),
                    results.getString(4)
            );

            return template;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // if the object doesn't exist in the database
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
        return null;
    }

    /**
     * Returns a list of subscriber email addresses from the USERS database. If an error occurs, a stack
     * trace is printed to standard error and an empty list is returned.
     *
     * @return a list of subscriber email addresses.
     */
    private static ArrayList<User> readSubscribersEmail() {
        ArrayList<User> subscriberEmails = new ArrayList<>();

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
                        null,
                        0
                );
                subscriberEmails.add(user);
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return subscriberEmails;

    }

    /**
     * Makes the subscriber email addresses publicly available for other classes.
     *
     * @return the list of subscriber email addresses
     */
    public static ArrayList<User> getGetSubscriberEmail() { return readSubscribersEmail(); }

    /**
     * Counts the number of subscribers that will receive an email notification
     *
     * @return a count for subscribers.
     */
    public static int emailSubCount() { return getGetSubscriberEmail().size();}

    /**
     * Returns a list of subscriber phone numbers from the USERS database. If an error occurs, a stack
     * trace is printed to standard error and an empty list is returned.
     *
     * @return a list of subscriber phone numbers.
     */
    private static ArrayList<User> readSubscriberPhone() {
        ArrayList<User> subscriberPhones = new ArrayList<>();

        connect();
        try (
                PreparedStatement stmt = conn.prepareStatement(GET_SUBSCRIBER_PHONE);
                ResultSet rs = stmt.executeQuery()) {
            // Iterate through subscribers in the database and add their phone numbers to a list.
            while (rs.next()) {
                User user = new User(
                        rs.getString("userPhone"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        0
                );
                subscriberPhones.add(user);
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return subscriberPhones;

    }

    /**
     * Makes the subscriber phone numbers publicly available for other classes.
     *
     * @return the list of subscriber phone numbers.
     */
    public static ArrayList<User> getGetSubscriberPhone() { return readSubscriberPhone(); }

    /**
     * Counts the number of subscribers that will receive an SMS notification
     *
     * @return a count for subscribers.
     */
    public static int smsSubCount() { return getGetSubscriberPhone().size();}

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
     * Public method to be called by the SendEmailNotification so that notification details
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


    /**
     * Fetches the information for the ReviewNotificationLog
     *
     * @param startDate  The start date to search for
     * @param endDate The end date to search for
     * @return The requested notification log query
     */
    public static ArrayList<Log> findLogs(String startDate, String endDate) {
        ResultSet rs;
        ArrayList<Log> logs = new ArrayList<>();
        PreparedStatement stmt;

        try {
            //Creates connection if not already created
            connect();

            //Prepare sql statement
            stmt = conn.prepareStatement(FIND_REVIEW_NOTIFICATION_QUERY);
            stmt.setString(1, startDate + " 00:00:00");
            stmt.setString(2, endDate + " 23:59:59");
            //Execute the query
            rs = stmt.executeQuery();

            //For each row in the result set, create a new Notification object with the provided values
            // and add it to the list of notifications
            while (rs.next()) {
                logs.add(new Log(
                        rs.getString("sentBy"),
                        rs.getString("sentDateTime"),
                        rs.getString("subject"),
                        rs.getString("messageBody"),
                        rs.getInt("subscriberCount")
                ));
            }
        } catch (Exception e) {
            System.err.println("ERROR: Your query is not working.");
            e.printStackTrace();
            return null;
        }
        //Returns list of results
        return logs;
    }
}
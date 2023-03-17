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

    private static final String GET_TEMPLATE_SQL = "SELECT UserID, TemplateName, Subject, TemplateText FROM TEMPLATES WHERE ID = ?";

    private static final String UPDATE_TEMPLATE_SQL = "UPDATE TEMPLATES SET TemplateText = ?, Subject = ? WHERE ID = ?";

    private static final String DELETE_TEMPLATE_SQL = "DELETE FROM TEMPLATES WHERE ID = ?";


    private static final String GET_SUBSCRIBER_EMAIL = "SELECT userEmail FROM USERS WHERE userRole = 'subscriber' AND"
    + " userEmail IS NOT NULL AND receiveNotifications = 'Yes' AND notificationType = 'Email'";
    private static final String GET_SUBSCRIBER_PHONE = "SELECT phoneNumber FROM USERS WHERE userRole = 'subscriber' AND"
    + " phoneNumber IS NOT NULL AND receiveNotifications = 'Yes' AND notificationType = 'SMS'";
    private static final String GET_SUBSCRIBER_BOTH = "SELECT username FROM USERS WHERE userRole = " +
            " 'subscriber' AND userEmail IS NOT NULL AND phoneNumber IS NOT NULL AND receiveNotifications = 'Yes' AND"
        + " notificationType = 'Both'";

    private static final String GET_ALL_SUBSCRIBER_INFO = "SELECT userID , username , firstName , lastName , userEmail , userPassword"
            + ", salt , userRole FROM 234a_Null.dbo.USERS";
    private static final String WRITE_NOTIFICATION_INFO = "INSERT INTO NOTIFICATIONS (subject, messageBody, sentBy, sentDateTime,"
            + "subscriberCount, type) VALUES (?,?,?,?,?,?)";

    private static final String CREATE_USER_ACCOUNT = "INSERT INTO USERS " +
            "(username, firstName, lastName, userEmail, userPassword, userRole, receiveNotifications, notificationType, verified, verifyCode) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_ACCOUNT = "UPDATE USERS SET username = ?, firstName = ?, lastName = ?, userEmail = ?, userPassword = ?, phoneNumber = ?, receiveNotifications = ?, notificationType = ? WHERE userID = ?";

    private static final String DELETE_USER_ACCOUNT = "DELETE FROM USERS WHERE userID = ?";


    private static final String GET_EMAIL = "SELECT userEmail FROM USERS WHERE userEmail = ?";
    
    private static final String GET_USERID_EMAIL = "SELECT userID FROM USERS WHERE userEmail = ?";
    
    private static final String GET_USERID_USERNAME = "SELECT userID FROM USERS WHERE username = ?";
    
    private static final String GET_USERNAME = "SELECT username FROM USERS WHERE username = ?";
    
    private static final String GET_PASSWORD_USERNAME = "SELECT userPassword FROM USERS WHERE username = ?";
    
    private static final String GET_PASSWORD_EMAIL = "SELECT userPassword FROM USERS WHERE userEmail = ?";

    private static final String GET_USERNAME_FROM_ID = "SELECT username FROM USERS WHERE userID = ?";
    
    private static final String GET_FIRST_NAME_FROM_ID = "SELECT firstName FROM USERS WHERE userID = ?";
    
    private static final String GET_LAST_NAME_FROM_ID = "SELECT lastName FROM USERS WHERE userID = ?";
    
    private static final String GET_EMAIL_FROM_ID = "SELECT userEmail FROM USERS WHERE userID = ?";
    
    private static final String GET_PASSWORD_FROM_ID = "SELECT userPassword FROM USERS WHERE userID = ?";
    
    private static final String GET_PHONE_NUMBER_FROM_ID = "SELECT phoneNumber FROM USERS WHERE userID = ?";
    
    private static final String GET_RECEIVE_NOTIFICATIONS_FROM_ID = "SELECT receiveNotifications FROM USERS WHERE userID = ?";
    
    private static final String GET_NOTIFICATION_TYPE_FROM_ID = "SELECT notificationType FROM USERS WHERE userID = ?";
    
    private static final String GET_ROLE_FROM_ID = "SELECT userRole FROM USERS WHERE userID = ?";

    private static final String FIND_REVIEW_NOTIFICATION_QUERY =
            "SELECT sentBy,  sentDateTime, subject, messageBody, subscriberCount " + "FROM NOTIFICATIONS " +

    private static final String GET_DATE_SEARCH_QUERY =
            "SELECT sentBy,  sentDateTime, subject, messageBody, subscriberCount, type " + "FROM NOTIFICATIONS " +
                    "WHERE  sentDateTime BETWEEN ? AND ?;";
                    
    private static final String GET_USER_SEARCH_QUERY =
            "SELECT sentBy,  sentDateTime, subject, messageBody, subscriberCount, type " + "FROM NOTIFICATIONS " +
                    "WHERE  sentBy LIKE CONCAT( '%',?,'%');";
                    
    private static final String GET_SUBJECT_SEARCH_QUERY =
            "SELECT sentBy,  sentDateTime, subject, messageBody, subscriberCount, type " + "FROM NOTIFICATIONS " +
                    "WHERE  subject LIKE CONCAT( '%',?,'%');";
                    
    private static final String GET_MESSAGE_SEARCH_QUERY =
            "SELECT sentBy,  sentDateTime, subject, messageBody, subscriberCount, type " + "FROM NOTIFICATIONS " +
                    "WHERE  messageBody LIKE CONCAT( '%',?,'%');";

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
    public static void addSubscriber(String username, String firstName, String lastName, String email, String password, String verifyCode) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(CREATE_USER_ACCOUNT);
            stmt.setString(1, username);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setString(6, "subscriber");
            stmt.setString(7, "Yes");
            stmt.setString(8, "Email");
            stmt.setString(9, "false");
            stmt.setString(10, verifyCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates a row in the Users table with new information
    public static void updateSubscriber(String username, String firstName, String lastName, String email, String password, String phoneNumber, String receiveNotifications, String notificationType, Integer userID ) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_ACCOUNT);
            stmt.setString(1, username);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setString(6, phoneNumber);
            stmt.setString(7, receiveNotifications);
            stmt.setString(8, notificationType);
            stmt.setInt(9, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates a row in the Users table with new information
    public static void deleteSubscriber(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(DELETE_USER_ACCOUNT);
            stmt.setInt(1, userID);
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

    // Retrieves the userID that belongs to the email address the user entered
    public static Integer getUserIDEmail(String name) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_USERID_EMAIL);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userID = rs.getString(1);
                return Integer.valueOf(userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieves the userID that belongs to the username the user entered
    public static Integer getUserIDUsername(String name) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_USERID_USERNAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userID = rs.getString(1);
                return Integer.valueOf(userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //returns the username from the database based on the userID
    public static String getUsernameFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_USERNAME_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString(1);
                return username;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns the email from the database based on the userID
    public static String getEmailFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_EMAIL_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String email = rs.getString(1);
                return email;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns user's first name  from the database based on the userID
    public static String getFirstNameFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_FIRST_NAME_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString(1);
                return firstName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns user's last name from the database based on the userID
    public static String getLastNameFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_LAST_NAME_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String lastName = rs.getString(1);
                return lastName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns the password from the database based on the userID
    public static String getPasswordFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_PASSWORD_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString(1);
                return password;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns phone number from the database based on the userID
    public static String getPhoneNumberFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_PHONE_NUMBER_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String phoneNumber = rs.getString(1);
                return phoneNumber;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns teh receive notification setting from the database based on the userID
    public static String getReceiveNotificationsFromId(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_RECEIVE_NOTIFICATIONS_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String receiveNotifications = rs.getString(1);
                return receiveNotifications;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns the notification type settings from the database based on the userID
    public static String getNotificationTypeFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_NOTIFICATION_TYPE_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String notificationType = rs.getString(1);
                return notificationType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    //returns the notification type settings from the database based on the userID
    public static String getRoleFromID(Integer userID) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_ROLE_FROM_ID);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString(1);
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // sets the ID to the 1
    // retrieves the value from the database
    public static Template getTemplate(int id) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(GET_TEMPLATE_SQL);
            stmt.setInt(1, id);

            ResultSet results = stmt.executeQuery();

            // starts reading from the first assigned column in the database
            if (!results.next())
                return null;

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

    // takes a TemplateName as input
    // updates the subject and text of the template in the database corresponding to a passed TemplateName
    public static void updateTemplate(int id, String subject, String templateText) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_TEMPLATE_SQL);
            stmt.setString(1, templateText);
            stmt.setString(2, subject);
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // takes a TemplateName as input
    // removes the subject, template text, and selected templateName from the database
    public static void deleteTemplate(int id) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(DELETE_TEMPLATE_SQL);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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
    public static ArrayList<User> getSubscriberEmails() { return readSubscribersEmail(); }

    /**
     * Counts the number of subscribers that will receive an email notification
     *
     * @return a count for subscribers.
     */
    public static int emailSubCount() { return (getSubscriberEmails()).size();}

    /**
     * Returns a list of subscriber phone numbers from the USERS database. If an error occurs, a stack
     * trace is printed to standard error and an empty list is returned.
     *
     * @return a list of subscriber phone numbers.
     */
    private static ArrayList<String> readSubscriberPhone() {
        ArrayList<String> subscriberPhones = new ArrayList<>();

        connect();
        try (
                PreparedStatement stmt = conn.prepareStatement(GET_SUBSCRIBER_PHONE);
                ResultSet rs = stmt.executeQuery()) {
            // Iterate through subscribers in the database and add their phone numbers to a list.
            while (rs.next()) {
                User user = new User(
                        null,
                        rs.getString("phoneNumber"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        0
                );
                subscriberPhones.add(String.valueOf(user));
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
    public static ArrayList<String> getSubscriberPhone() { return readSubscriberPhone(); }

    /**
     * Counts the number of subscribers that will receive an SMS notification
     *
     * @return a count for subscribers.
     */
    public static int smsSubCount() { return getSubscriberPhone().size();}


    /**
     * Returns a list of subscribers who want to receive both types of  notifications from the USERS database.
     * The only purpose of this method is to retrieve a count for subscribers who want both types of notifications.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     *
     * @return a list of users.
     */
    private static ArrayList<String> readSubscriberBoth() {
        ArrayList<String> subscriberBoth = new ArrayList<>();

        connect();
        try (
                PreparedStatement stmt = conn.prepareStatement(GET_SUBSCRIBER_BOTH);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        null,
                        null,
                        rs.getString("username"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        0
                );
                subscriberBoth.add(String.valueOf(user));
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return subscriberBoth;
    }

    /**
     * Counts the number of subscribers that will receive both SMS and Email notifications
     *
     * @return a count for subscribers.
     */
    public static int bothSubCount() { return readSubscriberBoth().size();}

    /**
     * Writes the details of each notification to the notification log database.
     *
     * @param subject         the subject of the notification
     * @param messageBody     the body of the notification
     * @param sentBy          pantry staff that sent the notification
     * @param subscriberCount the number of subscribers that received the notification
     */
    private static void writeNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount,
                                              String notificationType) {
        try {
            connect();
            PreparedStatement stmt = conn.prepareStatement(WRITE_NOTIFICATION_INFO);
            // Set the values for the INSERT statement
            stmt.setString(1, subject);
            stmt.setString(2, messageBody);
            stmt.setString(3, sentBy);
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(5, subscriberCount);
            stmt.setString(6, notificationType);
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
     * Public method to be called by the SendEmailNotification and SendSMSNotification so that notification details
     * can be recorded in the database.
     *
     * @param subject         the subject of the notification
     * @param messageBody     the body of the notification
     * @param sentBy          pantry staff that sent the notification
     * @param subscriberCount the number of subscribers that received the notification
     * @param notificationType the type of notification being recorded to the database(Email, SMS, Both)
     */
    public static void recordNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount,
                                              String notificationType) {
        writeNotificationInfo(subject, messageBody, sentBy, subscriberCount, notificationType);
    }


    /**
     * Fetches the date information for the ReviewNotificationLog
     *
     * @param startDate  The start date to search for

     * @param endDate  The end date to search for

     * @return The requested notification log query
     */
    public static ArrayList<Log> findDate(String startDate, String endDate) {
        ResultSet rs;
        ArrayList<Log> logs = new ArrayList<>();
        PreparedStatement stmt;

        try {
            //Creates connection if not already created
            connect();

            //Prepare sql statement
            stmt = conn.prepareStatement(GET_DATE_SEARCH_QUERY);
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
                        rs.getInt("subscriberCount"),
                        rs.getString("type")
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

    /**
     * Fetches staff the information for the ReviewNotificationLog
     *
     * @param staffText  The staff member to search for
     * @return The requested notification log query
     */
    public static ArrayList<Log> findUser(String staffText) {
        ResultSet rs;
        ArrayList<Log> users = new ArrayList<>();
        PreparedStatement stmt;

        try {
            //Creates connection if not already created
            connect();

            //Prepare sql statement
            stmt = conn.prepareStatement(GET_USER_SEARCH_QUERY);
            stmt.setString(1, staffText);
            //Execute the query
            rs = stmt.executeQuery();

            //For each row in the result set, create a new Notification object with the provided values
            // and add it to the list of notifications
            while (rs.next()) {
                users.add(new Log(
                        rs.getString("sentBy"),
                        rs.getString("sentDateTime"),
                        rs.getString("subject"),
                        rs.getString("messageBody"),
                        rs.getInt("subscriberCount"),
                        rs.getString("type")
                ));
            }
        } catch (Exception e) {
            System.err.println("ERROR: Your query is not working.");
            e.printStackTrace();
            return null;
        }
        //Returns list of results
        return users;
    }

    /**
     * Fetches staff the information for the ReviewNotificationLog
     *
     * @param subjectText  The subject member to search for
     * @return The requested notification log query
     */
    public static ArrayList<Log> findSubject(String subjectText) {
        ResultSet rs;
        ArrayList<Log> subjects = new ArrayList<>();
        PreparedStatement stmt;

        try {
            //Creates connection if not already created
            connect();

            //Prepare sql statement
            stmt = conn.prepareStatement(GET_SUBJECT_SEARCH_QUERY);
            stmt.setString(1, subjectText);
            //Execute the query
            rs = stmt.executeQuery();

            //For each row in the result set, create a new Notification object with the provided values
            // and add it to the list of notifications
            while (rs.next()) {
                subjects.add(new Log(
                        rs.getString("sentBy"),
                        rs.getString("sentDateTime"),
                        rs.getString("subject"),
                        rs.getString("messageBody"),
                        rs.getInt("subscriberCount"),
                        rs.getString("type")
                ));
            }
        } catch (Exception e) {
            System.err.println("ERROR: Your query is not working.");
            e.printStackTrace();
            return null;
        }
        //Returns list of results
        return subjects;
    }

    /**
     * Fetches staff the information for the ReviewNotificationLog
     *
     * @param messageText  The message to search for
     * @return The requested notification log query
     */
    public static ArrayList<Log> findMessage(String messageText) {
        ResultSet rs;
        ArrayList<Log> messages = new ArrayList<>();
        PreparedStatement stmt;

        try {
            //Creates connection if not already created
            connect();

            //Prepare sql statement
            stmt = conn.prepareStatement(GET_MESSAGE_SEARCH_QUERY);
            stmt.setString(1, messageText);
            //Execute the query
            rs = stmt.executeQuery();

            //For each row in the result set, create a new Notification object with the provided values
            // and add it to the list of notifications
            while (rs.next()) {
                messages.add(new Log(
                        rs.getString("sentBy"),
                        rs.getString("sentDateTime"),
                        rs.getString("subject"),
                        rs.getString("messageBody"),
                        rs.getInt("subscriberCount"),
                        rs.getString("type")
                ));
            }
        } catch (Exception e) {
            System.err.println("ERROR: Your query is not working.");
            e.printStackTrace();
            return null;
        }
        //Returns list of results
        return messages;
    }


}
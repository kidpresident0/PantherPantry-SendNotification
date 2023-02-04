package database;

import logic.ReviewNotificationLog;

import java.sql.*;
import java.util.ArrayList;

/**
 * This is the Database class that will retrieve information needed to send and retrieve
 * information from the database.  It will communicate with the logic layer that will then
 * communicate to the presentation layer.
 * @author Brandon King
 * @version 2022.02.04
 */

public class Database {

    private static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
    private static final String USERNAME = "234a_Null";
    private static final String PASSWORD = "456$%^234a_Null";

    //sql queries
    private static final String FIND_REVIEW_NOTIFICATION_QUERY =
            "need to insert a query here";

    //the connection object
    private static Connection m_Connection = null;

    /**
     * Creates the connection to the database
     */
    private static void connect() {
        if (m_Connection != null)
            return;
        try {
            //creates database connection
            m_Connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.err.println("ERROR: You could not connect to the database");
        }
    }

    /**
     * Fetches the information for the ReviewNotificationLog
     *
     * @param
     * @return
     */
    public static ArrayList<ReviewNotificationLog> reviewNotifications() {
        ResultSet rs = null;
        ArrayList<ReviewNotificationLog> notifications = new ArrayList<>();
        PreparedStatement stmt;

        try {
            //Creates connection if not already created
            connect();

            //Prepare sql statement
            stmt = m_Connection.prepareStatement(FIND_REVIEW_NOTIFICATION_QUERY);

            //Execute the query
            rs = stmt.executeQuery();

            //For each row in the result set, create a new Notification object with the provided values
            // and add it to the list of notifications
            while (rs.next()) {
                notifications.add(new ReviewNotificationLog(
                        rs.getString("userID"),
                        rs.getInt("dateTime"),
                        rs.getString("subject"),
                        rs.getString("messageBody"),
                        rs.getInt("subscriberAmount")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error: could not connect to database.");
            e.printStackTrace();
            return null;
        }
        //Returns list of results
        return notifications;
    }
}




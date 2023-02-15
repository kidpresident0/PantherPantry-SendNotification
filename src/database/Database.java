package database;

import logic.Log;

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
            "SELECT userID, dateTime, subject, messageBody, subscriberAmount " + "FROM NOTIFICATIONS " +
                    "WHERE dateTime BETWEEN ? AND ?;";

    //the connection object
    private static Connection mConnection = null;

    /**
     * Creates the connection to the database
     */
    private static void connect() {
        if (mConnection != null)
            return;
        try {
            //creates database connection
            mConnection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.err.println("ERROR: You could not connect to the database");
        }
    }

    /**
     * Fetches the information for the ReviewNotificationLog
     *
     * @param date  The date to search for
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
            stmt = mConnection.prepareStatement(FIND_REVIEW_NOTIFICATION_QUERY);
            stmt.setString(1, startDate + " 00:00:00");
            stmt.setString(2, endDate + " 23:59:59");
            //Execute the query
            rs = stmt.executeQuery();

            //For each row in the result set, create a new Notification object with the provided values
            // and add it to the list of notifications
            while (rs.next()) {
                logs.add(new Log(
                        rs.getString("userID"),
                        rs.getString("dateTime"),
                        rs.getString("subject"),
                        rs.getString("messageBody"),
                        rs.getInt("subscriberAmount")
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




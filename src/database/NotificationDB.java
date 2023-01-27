package database;

/**
 * This is the sendNotification.NotificationDB class that will retrieve information needed to send notifications like
 * subscribers from the dtabase. Eventually it will store some of that information in the database
 * for the log search feature.
 * @author John Christian
 * @version 2022.01.26
 */
import logic.Notification;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotificationDB {
    public static void main(String[] args) {
        String url = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
        String username = "234a_Null";
        String password = "456$%^234a_Null";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("connected to db");

            // Create the subscribers table
            String createTableSql = "CREATE TABLE subscribers (name VARCHAR(255), email VARCHAR(255))";
            try (PreparedStatement createTableStmt = conn.prepareStatement(createTableSql)) {
                createTableStmt.executeUpdate();
                System.out.println("created table");
            }

            // Add an entry to the subscribers table
            String insertSql = "INSERT INTO subscribers (name, email) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, "Hungry Joe");
                insertStmt.setString(2, "ImhungryJoe@whensdinner.com");
                insertStmt.executeUpdate();
                System.out.println("added entry successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private ArrayList<Notification>readSubscribers() {
        ArrayList<Notification> subscribers = new ArrayList<>();

        return subscribers;

    }
}


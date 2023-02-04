package database;

/**
 * This is the Database class. It will be used to write to and read information from the database.
 * @author John Christian
 * @version 2022.01.26
 */
import logic.Users;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    static String url = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
    static String username = "234a_Null";
    static String password = "456$%^234a_Null";
    static String GET_SUBSCRIBER_EMAIL = "SELECT userEmail FROM USERS WHERE userRole = 'subscriber'";

    static String GET_ALL_SUBSCRIBER_INFO = "SELECT [userID] ,[username] ,[firstName] ,[lastName] ,[userEmail] ,[userPassword] ,[salt] ,[userRole]FROM [234a_Null].[dbo].[USERS]";

    private ArrayList<Users> subscribers;
    /**
     * Create a Database object
     * Read  the subscriber email addresses from the database and creates a list of them.
     */
    public Database(){
        subscribers = readSubscribersEmail();
    }
    /**
     * Creates a connection to the database.
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
     * @return a list of subscriber email addresses.
     */
    private ArrayList<Users> readSubscribersEmail(){
        ArrayList<Users> subscribers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(GET_SUBSCRIBER_EMAIL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Users user = new Users(rs.getString("userEmail"));

                subscribers.add(user);
            }
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return subscribers;

    }

    /**
     * Returns the list of subscriber email addresses
     *
     * @return
     */
    public ArrayList<Users> getGetSubscriberEmail() {
        return subscribers;
    }
}


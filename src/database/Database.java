package database;

/**
 * This Database.java file connects to a SQL database, creates table templates
 * with given columns in the database, and adds the new template to the table
 * in the database with one created query (so far).
 * @author Kate White
 * @version 2023.01.30
 */

import logic.Template;
import java.sql.*;

// connecting to the database
public class Database {
    private static final String url = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
    private static final String username = "234a_Null";
    private static final String password = "456$%^234a_Null";

    // creates a query
    private static final String createTemplateSql = "INSERT INTO TEMPLATES " +
            "(UserID, TemplateName, Subject, TemplateText) " +
            "VALUES (?, ?, ?, ?)";

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
            PreparedStatement stmt = conn.prepareStatement(createTemplateSql);
            stmt.setInt(1, template.getUserID());
            stmt.setString(2, template.getTemplateName());
            stmt.setString(3, template.getSubject());
            stmt.setString(4, template.getTemplateText());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


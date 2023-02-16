package database;

/**
 * This Database.java file connects to a SQL database, creates table templates
 * with given columns in the database, and adds the new template to the table
 * in the database with one created query (so far).
 * @author Kate White
 * @version 2023.15.02
 */

import logic.Template;
import logic.TemplateName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// connecting to the database
public class Database {
    private static final String url = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_Null";
    private static final String username = "234a_Null";
    private static final String password = "456$%^234a_Null";

    // creation of queries
    private static final String createTemplateSql = "INSERT INTO TEMPLATES " +
            "(UserID, TemplateName, Subject, TemplateText) " +
            "VALUES (?, ?, ?, ?)";

    private static final String listTemplatesSql = "SELECT ID, TemplateName FROM TEMPLATES";

    private static final String getTemplateSql = "SELECT UserID, TemplateName, Subject, TemplateText FROM TEMPLATES WHERE ID = ?";

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

    // creates of the array of Template Names objects
    // retrieves the stored value from the database
    public static List<TemplateName> listTemplates(int id) {
        connect();

        List<TemplateName> list = new ArrayList<TemplateName>();

        try {
            PreparedStatement stmt = conn.prepareStatement(listTemplatesSql);
            //stmt.setInt(1, id);

            ResultSet results = stmt.executeQuery();

            while(results.next()) {
                list.add(new TemplateName(results.getInt(1), results.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
}


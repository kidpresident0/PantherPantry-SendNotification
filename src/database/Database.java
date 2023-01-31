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
            "(subject, templateText, campus, foodItems, startTime, endTime, term, staffName) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE TEMPLATES " +
                    "(id INTEGER NOT NULL IDENTITY PRIMARY KEY," +
                    " subject VARCHAR(255), " +
                    " templateText TEXT, " +
                    " campus VARCHAR(255), " +
                    " foodItems VARCHAR(255)," +
                    " startTime VARCHAR(255)," +
                    " endTime VARCHAR(255)," +
                    " term VARCHAR(255)," +
                    " staffName VARCHAR(255)" +
                    ")";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    // adding text to the table in database
    // addTemplate("Healthy Food Items", "Our mission to develop healthy eating habits in you!", "PCC, Sylvania Campus", "Veggies and fruits",
    //            "Jan 20, 2023 12:00:00", "Jan 20, 2023 2:00:00", "Spring Term", "Jenny P");

    // Adds a new template to the table in database.
    public static void createTemplate(Template template) {
        connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(createTemplateSql);
            stmt.setString(1, template.getSubject());
            stmt.setString(2, template.getTemplateText());
            stmt.setString(3, template.getCampus());
            stmt.setString(4, template.getFoodItems());
            stmt.setString(5, template.getStartTime());
            stmt.setString(6, template.getEndTime());
            stmt.setString(7, template.getTerm());
            stmt.setString(8, template.getStaffName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


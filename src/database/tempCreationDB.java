package database;

/**
 *
 * To do:
 * search how to create database table
 * be able to insert row
 *
 * This is the templateCreation.tempCreationDB class that will retrieve information needed from the user
 * to filiing the gaps created in this template. Eventually it will store some of that information in the database
 * for the log search feature.
 * @author John Christian, Kate White
 * @version 2022.01.30
 */
import logic.templateCreation;

import java.util.ArrayList;

public class tempCreationDB {

    private static final String url = "jdbc:mysql://cisdbss.pcc.edu:3306/234a_Null";
    private static final String username = "234a_Null";
    private static final String password = "456_$%^234a_Null";
    private static final String dbname = "234a_Null";

    private static final String GET_SUBSCRIBERS_SQL = "SELECT * FROM subscribers where ? = ";


    private ArrayList<templateCreation>readSubscribers() {
        ArrayList<templateCreation> subscribers = new ArrayList<>();

        return subscribers;

    }
}


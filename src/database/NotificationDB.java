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

public class NotificationDB {

    private static final String url = "jdbc:mysql://cisdbss.pcc.edu:3306/234a_Null";
    private static final String username = "234a_Null";
    private static final String password = "456_$%^234a_Null";
    private static final String dbname = "234a_Null";

    private static final String GET_SUBSCRIBERS_SQL = "SELECT * FROM subscribers where ? = ";




    private ArrayList<Notification>readSubscribers() {
        ArrayList<Notification> subscribers = new ArrayList<>();

        return subscribers;

    }
}


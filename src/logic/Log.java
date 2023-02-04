package logic;

import database.Database;

import java.util.ArrayList;

/**
 * This is a class for the notification log that will communicate between the presentation and database package to run
 * the query for the UI.
 * @author Brandon King
 * @version 2023.02.23
 */

public class Log {
    private static String m_userID;
    private static Integer m_dateTime;
    private static String m_subject;
    private static String m_messageBody;
    private static Integer m_subscriberAmount;

    public Log(String userID, Integer dateTime, String subject, String messageBody, Integer subscriberAmount) {
        m_userID = userID;
        m_dateTime = dateTime;
        m_subject = subject;
        m_messageBody = messageBody;
        m_subscriberAmount = subscriberAmount;
    }

    public static ArrayList<Log> findLogs() {
        return Database.findLogs();
    }

    public static String getUserID() {
        return m_userID;
    }
    public static Integer getDateTime() {
        return m_dateTime;
    }

    public static String getSubject() {
        return m_subject;
    }

    public static String getMessageBody() {
        return m_messageBody;
    }

    public static Integer getSubscriberAmount() {
        return m_subscriberAmount;
    }
}



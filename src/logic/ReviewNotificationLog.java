package logic;

import database.Database;

import java.util.ArrayList;

/**
 * This is a class for the notification log that will communicate between the presentation and database package to run
 * the query for the UI.
 * @author Brandon King
 * @version 2023.02.23
 */

public class ReviewNotificationLog {
    private String m_userID;
    private Integer m_dateTime;
    private String m_subject;
    private String m_messageBody;
    private Integer m_subscriberAmount;

    public ReviewNotificationLog (String userID, Integer dateTime, String subject, String messageBody, Integer subscriberAmount) {
        m_userID = userID;
        m_dateTime = dateTime;
        m_subject = subject;
        m_messageBody = messageBody;
        m_subscriberAmount = subscriberAmount;
    }

    public static ArrayList<ReviewNotificationLog> reviewNotifications() {
        return Database.reviewNotifications();
    }

    public String getUserID() {
        return m_userID;
    }
    public Integer getDateTime() {
        return m_dateTime;
    }

    public String getSubject() {
        return m_subject;
    }

    public String getMessageBody() {
        return m_messageBody;
    }

    public Integer getSubscriberAmount() {
        return m_subscriberAmount;
    }
}



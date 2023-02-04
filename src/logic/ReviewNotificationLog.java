package logic;

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

    public ReviewNotificationLog (String userID, Integer date, String subject, String messageBody, Integer subscriberAmount) {
        m_userID = userID;
        m_dateTime = date;
        m_subject = subject;
        m_messageBody = messageBody;
        m_subscriberAmount = subscriberAmount;
    }
}



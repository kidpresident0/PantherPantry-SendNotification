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
    private static String mSentBy;
    private static String mSentDateTime;
    private static String mSubject;
    private static String mMessageBody;
    private static Integer mSubscriberCount;

    public Log(String sentBy, String sentDateTime, String subject, String messageBody, Integer subscriberCount) {
        mSentBy = sentBy;
        mSentDateTime = sentDateTime;
        mSubject = subject;
        mMessageBody = messageBody;
        mSubscriberCount = subscriberCount;
    }

    public static ArrayList<Log> findLogs(String startDate, String endDate) {
        return Database.findLogs(startDate, endDate);
    }

    public static String getSentBy() {
        return mSentBy;
    }

    public static String getSentDateTime() {
        return mSentDateTime;
    }

    public static String getSubject() { return mSubject;}

    public static String getMessageBody() {
        return mMessageBody;
    }

    public static Integer getSubscriberCount() {
        return mSubscriberCount;
    }
}



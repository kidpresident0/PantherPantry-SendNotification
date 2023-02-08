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
    private static String mUserID;
    private static String mDate;
    private static String mSubject;
    private static String mMessageBody;
    private static Integer mSubscriberAmount;

    public Log(String userID, String date, String subject, String messageBody, Integer subscriberAmount) {
        mUserID = userID;
        mDate = date;
        mSubject = subject;
        mMessageBody = messageBody;
        mSubscriberAmount = subscriberAmount;
    }

    public static ArrayList<Log> findLogs() {
        return Database.findLogs();
    }

    public static String getUserID() {
        return mUserID;
    }
    public static String getDate() {
        return mDate;
    }

    public static String getSubject() {
        return mSubject;
    }

    public static String getMessageBody() {
        return mMessageBody;
    }

    public static Integer getSubscriberAmount() {
        return mSubscriberAmount;
    }
}



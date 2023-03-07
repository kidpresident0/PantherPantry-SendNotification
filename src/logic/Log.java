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
    private String mSentBy;
    private String mSentDateTime;
    private String mSubject;
    private String mMessageBody;
    private Integer mSubscriberCount;

    public Log(String sentBy, String sentDateTime, String subject, String messageBody, Integer subscriberCount) {
        mSentBy = sentBy;
        mSentDateTime = sentDateTime;
        mSubject = subject;
        mMessageBody = messageBody;
        mSubscriberCount = subscriberCount;
    }

    public static ArrayList<Log> findLogs(String startDate, String endDate) {
        return Database.findDate(startDate, endDate);
    }

    public static ArrayList<Log> findUser(String staffText) {
        return Database.findUser(staffText);
    }

    public static ArrayList<Log> findSubject(String subjectText) {
        return Database.findSubject(subjectText);
    }

    public static ArrayList<Log> findMessage(String messageText) {
        return Database.findMessage(messageText);
    }

    public String getSentBy() {
        return mSentBy;
    }

    public String getSentDateTime() {
        return mSentDateTime;
    }

    public String getSubject() { return mSubject;}

    public String getMessageBody() {
        return mMessageBody;
    }

    public Integer getSubscriberCount() {
        return mSubscriberCount;
    }
}



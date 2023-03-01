package logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @Test
    void testFindLogs() {
        ArrayList<Log> logs = Log.findLogs("", "");
        assertEquals(0, logs.size());
    }

    @Test
    void testGetSentBy() {
        Log l = new Log("Test User","2022-03-01 11:30:00","Test Subject","This is a test message body.",10);
        assertEquals("Test User", l.getSentBy());
    }

    @Test
    void testGetSentDateTime() {
        Log l = new Log("Test User","2022-03-01 11:30:00","Test Subject","This is a test message body.",10);
        assertEquals("2022-03-01 11:30:00", l.getSentDateTime());
    }

    @Test
    void testGetSubject() {
        Log l = new Log("Test User","2022-03-01 11:30:00","Test Subject","This is a test message body.",10);
        assertEquals("Test Subject", l.getSubject());
    }

    @Test
    void testGetMessageBody() {
        Log l = new Log("Test User","2022-03-01 11:30:00","Test Subject","This is a test message body.",10);
        assertEquals("This is a test message body.", l.getMessageBody());
    }

    @Test
    void testGetSubscriberCount() {
        Log l = new Log("Test User","2022-03-01 11:30:00","Test Subject","This is a test message body.",10);
        assertEquals(Integer.valueOf(10), l.getSubscriberCount());
    }
}
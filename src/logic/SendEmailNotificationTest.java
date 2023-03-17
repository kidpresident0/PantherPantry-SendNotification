package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Test class for SendEmailNotification
 * @authors John Christian
 * @version 2023.02.21
 */
class SendEmailNotificationTest {

    @Test
    void sendNotificationTestValid() {
            String subscribers = "test@example.com";
            String subject = "Test Subject";
            String body = "Test Body";
            Assertions.assertDoesNotThrow(() -> SendEmailNotification.sendEmailNotification(subscribers, subject, body));
    }

    @Test
    void GetSubscriberCountTest() {
        int subscriberCount = SendEmailNotification.getEmailSubscriberCount();
        Assertions.assertTrue(subscriberCount >= 0);
    }

    @Test
    void GetUsernameTest() {
        String expected = "TeamNullTest@gmail.com";
        String actual = new SendEmailNotification().getUsername();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void SubscriberEmailsTest() {
        ArrayList<User> subscribers = new SendEmailNotification().subscriberEmails();
        Assertions.assertNotNull(subscribers);
        Assertions.assertFalse(subscribers.isEmpty());
    }







}
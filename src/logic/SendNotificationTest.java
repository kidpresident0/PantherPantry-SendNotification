package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Test class for SendNotification
 * @authors John Christian
 * @version 2023.02.21
 */
class SendNotificationTest {

    @Test
    void sendNotificationTestValid() {
            String subscribers = "test@example.com";
            String subject = "Test Subject";
            String body = "Test Body";
            Assertions.assertDoesNotThrow(() -> SendNotification.sendNotification(subscribers, subject, body));
    }

    @Test
    void GetSubscriberCountTest() {
        int subscriberCount = SendNotification.getSubscriberCount();
        Assertions.assertTrue(subscriberCount >= 0);
    }

    @Test
    void GetUsernameTest() {
        String expected = "TeamNullTest@gmail.com";
        String actual = new SendNotification().getUsername();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void SubscriberEmailsTest() {
        ArrayList<User> subscribers = new SendNotification().subscriberEmails();
        Assertions.assertNotNull(subscribers);
        Assertions.assertFalse(subscribers.isEmpty());
    }







}
package logic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import database.Database;

import java.util.ArrayList;

/**
 * This is the SMS Notification Class. It uses the Twilio framework to send SMS notifications to
 * Panther Pantry subscribers who wish to receive them.
 *
 * @author Twilio, John Christian
 * @version 2023.03.13
 *
 */

public class SendSMSNotification {
    // Your Twilio account SID and auth token
    private static final String ACCOUNT_SID = "AC02ab8364891650bfb13c9f20c2a63a63";
    private static final String AUTH_TOKEN = "93711ea02317821b7b03855c917bc050";

    // The phone number you're sending the SMS message from (Twilio phone number)
    private static final String FROM_NUMBER = "+15673339882";

    //retrieve phone numbers from the database and proceed them with a + per Twilio requirements.
    public static ArrayList<String> subscriberNumbers() {
        ArrayList<String> rawPhoneNumbers = Database.getSubscriberPhone();
        ArrayList<String> formattedPhoneNumbers = new ArrayList<>();
        for (String phoneNumber : rawPhoneNumbers) {
            formattedPhoneNumbers.add("+" + phoneNumber);
        }
        return formattedPhoneNumbers;
    }

    /**
     * Get the username of the panther pantry staff sending the SMS. For demonstration purposes
     * this value is currently hardcoded.
     * @return sender's email address
     */
    public static String getUsername() {
        String publicUsername = "PantherPantryAdmin";
        return publicUsername;
    }

    /**
     * Pass along the current Panther Pantry SMS subscriber count to the GUI
     * @return subscriber count
     */
    public static int getSMSSubscriberCount() {

        return Database.smsSubCount();
    }


    /**
     * Sends a text message to the specified phone numbers with the specified message body.
     *
     * @param subscriberNumber The phone number to send the message to.
     * @param messageBody       The body of the message to send.
     */
    public static void sendMessage(String subscriberNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(subscriberNumber),
                    new PhoneNumber(FROM_NUMBER),
                    messageBody).create();

            System.out.println("Sent message to " + subscriberNumber + ": " + message.getSid());
        }
    /**
     * Pass the details of a single SMS notification along to the database.
     * @param messageBody message body of the notification
     * @param sentBy panther pantry staff member who sent the notification
     * @param subscriberCount the number of subscribers that received the notification
     */
    public static void setSMSNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount,
                                              String notificationType) {
        Database db = new Database();
        Database.recordNotificationInfo(subject, messageBody, sentBy, subscriberCount, notificationType);
    }


}



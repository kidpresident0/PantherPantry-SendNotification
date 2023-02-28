package logic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * This is the SMS Notification Class. It uses the Twilio framework to send SMS notifications to
 * Panther Pantry subscribers who wish to receive them.
 *
 * @author Twilio, John Christian
 * @version 2023.02.27
 *
 */

public class SendSMSNotification {
    // Your Twilio account SID and auth token
    private static final String ACCOUNT_SID = "AC02ab8364891650bfb13c9f20c2a63a63";
    private static final String AUTH_TOKEN = "93711ea02317821b7b03855c917bc050";

    // The phone number you're sending the SMS message from (Twilio phone number)
    private static final String FROM_PHONE_NUMBER = "your_twilio_phone_number_here";

    public static void send(String toPhoneNumber, String message) {
        // Initialize the Twilio client with your account SID and auth token
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send the SMS message using the Twilio API
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(FROM_PHONE_NUMBER), message).create();
    }
}


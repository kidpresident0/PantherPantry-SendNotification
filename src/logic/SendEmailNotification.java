package logic;

import database.Database;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Properties;

/**
 * This is the class responsible for sending email notifications to subscribers of the Panther Pantry.
 * It uses the Javax.mail library to log in to Gmail and send email notifications to Panther Pantry subscribers.
 *
 * @author Marc Goodman, John Christian
 * @version 2023.02.06
 */
public class SendEmailNotification {

    /**
     * Create a sendNotification object
     * @param subscribers the email addresses of the subscribers
     * @param subject the subject of the notification
     * @param body the message body of the notification
     */
    public static void sendNotification(String subscribers, String subject, String body) {

        final String username = "TeamNullTest@gmail.com";
        final String password = "jpolvslgeuawbsum";

        Properties props = new Properties();

        props.put("mail.smtp.auth" , "true");
        props.put("mail.smtp.starttls.enable" , "true");
        props.put("mail.smtp.host" , "smtp.gmail.com");
        props.put("mail.smtp.port" , "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("TeamNullTest@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("jumbo86smile@gmail.com"));
            message.setSubject(subject);
            message.setContent(body, "text/plain");

            Transport.send(message);

            System.out.println("Done");

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pass along the current Panther Pantry subscriber account to the GUI
     * @return subscriber count
     */
    public static int getSubscriberCount() {

        return new Database().emailSubCount();
    }

    /**
     * Get the email address of the panther pantry staff sending the email. For demonstration purposes
     * this value is currently hardcoded.
     * @return sender's email address
     */
    public String getUsername() {
        String publicUsername = "TeamNullTest@gmail.com";
        return publicUsername;
    }

    /**
     * Make a list of subscriber emails
     *
     * @return a list of subscriber email addresses.
     */
    public ArrayList<User> subscriberEmails() {
        Database subEmails = new Database();
        ArrayList<User> subscribers = subEmails.getGetSubscriberEmail();
        System.out.println(subscribers);
        for (User user : subscribers) {
            System.out.println(user.getEmail());
        }
        return subscribers;
    }

    /**
     * Pass the details of a single notification along to the database.
     * @param subject subject of the notification
     * @param messageBody message body of the notification
     * @param sentBy panther pantry staff member who sent the notification
     * @param subscriberCount the number of subscribers that received the notification
     */
    public static void setNotificationInfo(String subject, String messageBody, String sentBy, int subscriberCount) {
        Database db = new Database();
        db.recordNotificationInfo(subject, messageBody, sentBy, subscriberCount);
    }
}
package logic;

import presentation.SendNotificationGUI;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This is the class responsible for sending email notifications to subscribers of the Panther Pantry.
 *
 * @author Marc Goodman
 * @version 2023.02.06
 *
 */
public class SendEmail {


    public static void sendEmail(String subscribers, String subject, String body ) {

        SendNotificationGUI gui = new SendNotificationGUI();

        final String username = "TeamNull234@gmail.com";
        final String password = "zmjmnszsetntvcxq";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("TeamNull234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("flanwithaplan0@gmail.com"));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);

            System.out.println("Done");

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
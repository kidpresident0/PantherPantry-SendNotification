package logic;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * This is the class responsible for sending email notifications to subscribers of the Panther Pantry.
 * It uses the Javax.mail library to log in to Gmail and send email notifications to Panther Pantry subscribers.
 * Updated to send verification emails to new subscribers
 *
 * @author Marc Goodman, John Christian, Sevin Webb
 * @version 2023.03.14
 */

public class SendVerification {

    public static void sendVerification(String email, String subject, String body) {

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
                    InternetAddress.parse(email));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);

            System.out.println("Done");

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

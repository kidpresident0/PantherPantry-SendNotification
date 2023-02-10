package presentation;

import database.Database;
import logic.SendNotification;
import logic.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the GUI class for the Send Notification story for the PCC Panther Pantry.
 *
 * @author John Christian
 * @version 2023.02.07
 */
public class SendNotificationGUI extends JFrame {


    JPanel rootPanel;
    JTextField subjectField;
    JLabel subjectLabel;
    JTextArea bodyArea;
    JButton sendButton;
    JLabel subscriberLabel;
    JTextField subscriberField;
    JPanel topPanel;
    JPanel bottomPanel;


    public SendNotificationGUI() {
        Database db = new Database();

        subscriberField.setText(String.valueOf(db.subCount()));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonSendActionPerformed(event);
            }

        });

    }

    private void buttonSendActionPerformed(ActionEvent event) {
        SendNotification sendNotification = new SendNotification();
        Database db = new Database();
        if (!validateFields()) {
            return;
        }
        String subscribers = "flanwithaplan0@gmail.com";
        //ArrayList<User> subscribers = subscriberEmails();
        String fromEmail = sendNotification.getUsername();
        String subject = subjectField.getText();
        String body = bodyArea.getText();
        int subscriberCount = db.subCount();
        try {
            SendNotification.sendEmail(subscribers, subject, body);
            subjectField.setText("");
            bodyArea.setText("");
            recordTime();
            db.setNotificationInfo(subject, body, fromEmail, subscriberCount);
            System.exit(0);


            JOptionPane.showMessageDialog(this,
                    "The notification has been sent successfully!");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error sending Email." ,
                    "Please try again" , JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (subjectField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a subject!" ,
                    "Error" , JOptionPane.ERROR_MESSAGE);
            subjectField.requestFocus();
            return false;
        }
        if (bodyArea.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a message!" ,
                    "Error" , JOptionPane.ERROR_MESSAGE);
            bodyArea.requestFocus();
            return false;
        }

        return true;
    }

    public void recordTime() {
        Calendar now = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
        String result = df.format(now.getTime());
        System.out.println(result);

    }

    public ArrayList<User> subscriberEmails() {
        Database subEmails = new Database();
        ArrayList<User> subscribers = subEmails.getGetSubscriberEmail();
        System.out.println(subscribers);
        for (User user : subscribers) {
            System.out.println(user.getEmail());
        }
        return subscribers;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }


}

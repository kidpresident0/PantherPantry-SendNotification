package presentation;

import database.Database;
import logic.SendNotification;
import logic.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonSendActionPerformed();
            }

        });

    }

    // If the subject/body fields have content send the notification and pass its details along to the logic layer.
    private void buttonSendActionPerformed() {
        SendNotification sendNotification = new SendNotification();

        if (!validateFields()) {
            return;
        }
        String subscribers = "flanwithaplan0@gmail.com";
        //ArrayList<User> subscribers = subscriberEmails();
        String fromEmail = sendNotification.getUsername();
        String subject = subjectField.getText();
        String body = bodyArea.getText();
        int subscriberCount = SendNotification.getSubscriberCount();
        try {
            SendNotification.sendNotification(subscribers, subject, body);
            subjectField.setText("");
            bodyArea.setText("");
            recordTime();
            SendNotification.setNotificationInfo(subject, body, fromEmail, subscriberCount);
            JOptionPane.showMessageDialog(this,
                    "The notification has been sent successfully!");
            System.exit(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error sending Email." ,
                    "Please try again" , JOptionPane.ERROR_MESSAGE);
        }
    }
    // Check that the subject and body are not empty
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
    //Display the local time for when the notification is sent to the terminal
    public void recordTime() {
        Calendar now = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
        String result = df.format(now.getTime());
        System.out.println(result);

    }
    //Only on subscriber is present for presentation purposes
    public ArrayList<User> subscriberEmails() {
        ArrayList<User> subEmails = new SendNotification().subscriberEmails();

        return subEmails;
    }
    //root GUI panel that hold all the Swing components
    public JPanel getRootPanel() {
        return rootPanel;
    }

}

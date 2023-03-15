package presentation;

import logic.SendEmailNotification;
import logic.SendSMSNotification;
import logic.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the GUI class for the Send Notification story for the PCC Panther Pantry.
 *
 * @author John Christian
 * @version 2023.03.13
 */
public class SendNotificationGUI extends JFrame {

    private JPanel rootPanel;
    private JTabbedPane notificationChoiceTabbedPane;
    JTextField emailSubjectField;
    private JScrollPane emailBodyScrollPane;
    JTextArea emailBodyArea;
    JLabel emailSubscriberCountLabel;
    private JTextField emailSubCountField;
    JButton emailSendButton;
    JPanel emailTopPanel;
    JPanel emailBottomPanel;
    private JPanel emailTabPanel;
    private JLabel emailSubjectLabel;
    private JPanel smsTabPanel;
    private JPanel smsTopPanel;
    private JLabel smsMessageLabel;
    private JPanel smsMiddlePanel;
    private JTextArea smsMessageArea;
    private JPanel smsBottomPanel;
    private JTextField smsSubCountField;
    private JLabel smsSubCountLabel;
    private JButton smsSendButton;
    private JPanel smsBottomRightPanel;
    private JPanel smsBottomLeftPanel;
    private JPanel smsBottomMiddlePanel;
    private JPanel smsMiddleLeftPanel;
    private JPanel smsMiddleRightPanel;

    private JCheckBox bothSMSAndEmailCheckbox;


    public SendNotificationGUI() {

        emailSubCountField.setText(String.valueOf(SendEmailNotification.getEmailSubscriberCount()));
        smsSubCountField.setText(String.valueOf(SendSMSNotification.getSMSSubscriberCount()));

        emailBottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        emailSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                if (bothSMSAndEmailCheckbox.isSelected()) {
                    bothEmailAndSMSButtonActionPerformed();
                }
                else {
                    emailButtonSendActionPerformed();
                }
            }

        });

        smsSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { demoSMSSendButtonActionPerformed(); }
            //public void actionPerformed(ActionEvent actionEvent) { smsButtonSendActionPerformed(); }
        });

        bothSMSAndEmailCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ( bothSMSAndEmailCheckbox.isSelected()) {
                    emailSubCountField.setText(String.valueOf(SendEmailNotification.getBothSubscriberCount()));
                }
                else { emailSubCountField.setText(String.valueOf(SendEmailNotification.getEmailSubscriberCount())); }
            }
        });
    }

    // If the subject/body fields have content send the notification and pass its details along to the logic layer.
    private void emailButtonSendActionPerformed() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        if (!validateEmailFields()) {
                return;
        }
            String subscribers = "flanwithaplan0@gmail.com";
            //ArrayList<User> subscribers = subscriberEmails();
            String sentBy = sendEmailNotification.getUsername();
            String notificationType = "email";
            String subject = emailSubjectField.getText();
            String body = emailBodyArea.getText();
            int subscriberCount = SendEmailNotification.getEmailSubscriberCount();
            try {
                SendEmailNotification.sendEmailNotification(subscribers, subject, body);
                emailSubjectField.setText("");
                emailBodyArea.setText("");

                SendEmailNotification.setNotificationInfo(subject, body, sentBy, subscriberCount, notificationType);
                JOptionPane.showMessageDialog(this,
                        "The notification has been sent successfully!");
                System.exit(0);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error sending Email.",
                        "Please try again", JOptionPane.ERROR_MESSAGE);
            }


        }


    //This action listener would format an array list of subscriber numbers for Twilio and send them
    //an SMS notification per the requirements of Sprint 2, then log the details.
    private void smsButtonSendActionPerformed() {
        if (validateSMSFields()) {
            ArrayList<String> subscriberNumbers = SendSMSNotification.subscriberNumbers();
            int subscriberCount = SendSMSNotification.getSMSSubscriberCount();
            String messageBody = smsMessageArea.getText();
            String notificationType = "sms";
            String sentBy = SendSMSNotification.getUsername();
            try {
                for (String phoneNumber : subscriberNumbers) {
                    SendSMSNotification.sendMessage(phoneNumber, messageBody);
                }
                SendSMSNotification.setSMSNotificationInfo(null, messageBody, sentBy, subscriberCount, notificationType);
                JOptionPane.showMessageDialog(this,
                        "The SMS message has been sent successfully!");
                //System.exit(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error sending SMS message.",
                        "Please try again", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //This method sends an SMS notification to my phone for the demo and logs the details.
    private void demoSMSSendButtonActionPerformed() {
        if (validateSMSFields()) {
            String phoneNumber = "19717108892";
            String messageBody = smsMessageArea.getText();
            String notificationType = "sms";
            String sentBy = SendSMSNotification.getUsername();
            int subscriberCount = SendSMSNotification.getSMSSubscriberCount();
            try {
                SendSMSNotification.sendMessage(phoneNumber, messageBody);
                smsMessageArea.setText(" ");
                SendSMSNotification.setSMSNotificationInfo(null, messageBody, sentBy, subscriberCount, notificationType);
                JOptionPane.showMessageDialog(this,
                        "The SMS message has been sent successfully!");
                System.exit(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error sending SMS message.",
                        "Please try again", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Method for sending both an email and an SMS message containing the body of the email, then logging the details
    private void bothEmailAndSMSButtonActionPerformed() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();
        if (!validateEmailFields()) {
            return;
        }
        //email details
        String subscribers = "flanwithaplan0@gmail.com";
        //ArrayList<User> subscribers = subscriberEmails();
        String sentBy = sendEmailNotification.getUsername();
        String subject = emailSubjectField.getText();
        String body = emailBodyArea.getText();
        //SMS details
        String phoneNumber = "19717108892";
        String messageBody = emailBodyArea.getText();

        String notificationType = "both";
        int subscriberCount = SendEmailNotification.getBothSubscriberCount();
        try {
            SendEmailNotification.sendEmailNotification(subscribers, subject, body);
            SendSMSNotification.sendMessage(phoneNumber, messageBody);
            emailSubjectField.setText("");
            emailBodyArea.setText("");

            SendEmailNotification.setNotificationInfo(subject, body, sentBy, subscriberCount, notificationType );
            JOptionPane.showMessageDialog(this,
                    "Both Email and SMS notifications have been sent successfully!");
            System.exit(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error sending notifications",
                    "Please try again", JOptionPane.ERROR_MESSAGE);
        }


    }

    // Check that the subject and body are not empty
    private boolean validateEmailFields() {
        if (emailSubjectField.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a subject!" ,
                    "Error" , JOptionPane.ERROR_MESSAGE);
            emailSubjectField.requestFocus();
            return false;
        }
        if (emailBodyArea.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a message!" ,
                    "Error" , JOptionPane.ERROR_MESSAGE);
            emailBodyArea.requestFocus();
            return false;
        }

        return true;
    }

    // Check that SMS message field is not empty
    private boolean validateSMSFields() {
        if (smsMessageArea.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a message!" ,
                    "Error" , JOptionPane.ERROR_MESSAGE);
            smsMessageArea.requestFocus();
            return false;
        }

        return true;
    }

    //Only one subscriber is present for presentation purposes
    public ArrayList<User> subscriberEmails() {
        ArrayList<User> subEmails = new SendEmailNotification().subscriberEmails();

        return subEmails;
    }
    //root GUI panel that holds all the Swing components
    public JPanel getRootPanel() {
        return rootPanel;
    }

}

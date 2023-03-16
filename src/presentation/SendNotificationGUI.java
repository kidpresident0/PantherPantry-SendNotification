package presentation;

import database.Database;
import logic.SendEmailNotification;
import logic.Template;
import logic.TemplateName;
import logic.User;
import main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static main.Main.launchGUI;

/**
 * This is the GUI class for the Send Notification story for the PCC Panther Pantry.
 *
 * @author John Christian
 * @version 2023.02.07
 */
public class SendNotificationGUI extends JFrame {

    private JPanel rootPanel;
    JTextField emailSubjectField;

    JLabel emailSubjectLabel;
    JTextArea emailBodyArea;
    JLabel emailSubscriberCountLabel;
    private JTextField emailSubCountField;
    JButton emailSendButton;
    JPanel emailTopPanel;
    JPanel emailBottomPanel;
    private JTabbedPane notificationChoiceTabbedPane;
    private JPanel emailTabPanel;
    private JLabel subjectLabel;

    private JTextArea bodyArea;

    private JButton sendButton;

    private JLabel subscriberLabel;
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
    private JScrollPane messageScrollPane;
    private JCheckBox emailSMSbothCheckBox;
    private JComboBox templateNameComboBox;
    private JLabel TemplateNameLabel;
    private JButton previewButton;
    private JTextField campusTextField;
    private JTextField foodItemtextField;
    private JTextField dateTextField;
    private JTextField timeTextField;
    private JTextField termTextField;
    private JTextField staffNameTextField;
    private JLabel CampusNameLabel;
    private JLabel FoodItemLabel;
    private JLabel DateLabel;
    private JLabel TimeLabel;
    private JLabel TermLabel;
    private JLabel StaffNameLabel;
    private JButton backButton;

    public SendNotificationGUI() {
        List<TemplateName> templates = TemplateName.list(1);
        for (TemplateName template : templates) {
            templateNameComboBox.addItem(template);
        }
        // shows the comboBox empty where there is no selection of templates
        templateNameComboBox.setSelectedIndex(-1);

        emailSubCountField.setText(String.valueOf(Database.emailSubCount()));

        emailBottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        emailSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonSendActionPerformed();
            }

        });

        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateName name = (TemplateName) templateNameComboBox.getSelectedItem();
                if (name != null) {
                    Template template = Template.getTemplate(name.getId());

                    // Create an empty hashmap by declaring object of string type
                    HashMap<String, String> tags = new HashMap<>();
                    // Adding elements to the tags
                    tags.put("{campus}", campusTextField.getText());
                    tags.put("{food items}", foodItemtextField.getText());
                    tags.put("{date}", dateTextField.getText());
                    tags.put("{time}", timeTextField.getText());
                    tags.put("{term}", termTextField.getText());
                    tags.put("{staff name}", staffNameTextField.getText());

                    emailBodyArea.setText(template.createMessage(tags));
                } else {
                    // shows a warning message when the templateName in comboBox wasn't selected
                    JOptionPane.showMessageDialog(
                            rootPanel,
                            "Please select a template.",
                            "Warning", JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        templateNameComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateName name = (TemplateName) templateNameComboBox.getSelectedItem();
                if (name != null) {
                    Template template = Template.getTemplate(name.getId());
                    String subject = template.getSubject();
                    emailSubjectField.setText(subject);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.launchGUI();
            }
        });
    }

    // If the subject/body fields have content send the notification and pass its details along to the logic layer.
    private void buttonSendActionPerformed() {
        SendEmailNotification sendEmailNotification = new SendEmailNotification();

        if (!validateFields()) {
            return;
        }
        String subscribers = "flanwithaplan0@gmail.com";
        //ArrayList<User> subscribers = subscriberEmails();
        String fromEmail = sendEmailNotification.getUsername();
        String subject = emailSubjectField.getText();
        String body = emailBodyArea.getText();
        int subscriberCount = SendEmailNotification.getSubscriberCount();
        try {
            SendEmailNotification.sendNotification(subscribers, subject, body);
            emailSubjectField.setText("");
            emailBodyArea.setText("");
            recordTime();
            SendEmailNotification.setNotificationInfo(subject, body, fromEmail, subscriberCount);
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
    //Display the local time for when the notification is sent to the terminal
    public void recordTime() {
        Calendar now = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
        String result = df.format(now.getTime());
        System.out.println(result);

    }
    //Only one subscriber is present for presentation purposes
    public ArrayList<User> subscriberEmails() {
        ArrayList<User> subEmails = new SendEmailNotification().subscriberEmails();

        return subEmails;
    }
    //root GUI panel that hold all the Swing components
    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

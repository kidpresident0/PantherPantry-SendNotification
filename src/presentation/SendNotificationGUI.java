package presentation;

import database.Database;
import logic.SendEmail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendNotificationGUI extends JFrame implements ActionListener {


    JPanel rootPanel;
    JLabel senderLabel;
    JTextField senderField;
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
        SendEmail sendEmail = new SendEmail();



        subscriberField.setText(String.valueOf(db.subCount()));



        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonSendActionPerformed(event);
            }

            ;
        });

    }

    private void buttonSendActionPerformed(ActionEvent event) {
        SendEmail sendEmail = new SendEmail();
        if (!validateFields()) {
            return;
        }
        String subject = subjectField.getText();
        String body = bodyArea.getText();
        try {
            sendEmail.sendEmail();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error sending Email.",
                    "Please try again", JOptionPane.ERROR_MESSAGE);
        }
    }


    private boolean validateFields () {
//            if (senderField.getText().equals("")) {
//                JOptionPane.showMessageDialog(this,
//                        "Please enter a Sender" ,
//                        "Error" , JOptionPane.ERROR_MESSAGE);
//                senderField.requestFocus();
//                return false;
//            }

            if (subjectField.getText().equals("")) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a subject!" ,
                        "Error" , JOptionPane.ERROR_MESSAGE);
                subjectField.requestFocus();
                return false;
            }

            if (bodyArea.getText().equals("")) {
                JOptionPane.showMessageDialog(this,
                        "Please enter message!" ,
                        "Error" , JOptionPane.ERROR_MESSAGE);
                bodyArea.requestFocus();
                return false;
            }

            return true;
        }


        @Override
        public void actionPerformed (ActionEvent e){

        }
    public String getSubject () {
        return subjectField.getText();
    }

    public String getBody () {
        return bodyArea.getText();
    }

    public JPanel getRootPanel () {

        return rootPanel;
    }

}

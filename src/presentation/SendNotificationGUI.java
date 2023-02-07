package presentation;

import database.Database;

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
        subscriberField.setText(String.valueOf(db.subCount()));



    }


    public JPanel getRootPanel() {

        return rootPanel;
    }







    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

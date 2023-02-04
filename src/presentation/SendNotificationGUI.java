package presentation;

import javax.swing.*;
import java.awt.*;
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





    }


    public JPanel getRootPanel() {

        return rootPanel;
    }







    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

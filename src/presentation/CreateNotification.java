package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNotification extends JFrame implements ActionListener {

    JPanel newPanel;

    private JLabel staffLabel;
    private JTextField staffField;
    private JLabel dateLabel;
    private JTextField dateField;
    private JLabel subjectLabel;
    private JTextField subjectField;
    private JTextField timeField;
    private JLabel timeLabel;
    private JTextPane messagePane;
    private JLabel subscriberLabel;
    private JTextField subscriberField;


    CreateNotification(){
    JPanel newPanel = new JPanel(new GridLayout());



    newPanel.add(staffLabel);
    newPanel.add(staffField);
    newPanel.add(dateLabel);
    newPanel.add(dateField);
    newPanel.add(subjectLabel);
    newPanel.add(subjectField);
    newPanel.add(timeLabel);
    newPanel.add(timeField);

    newPanel.add(messagePane);

    this.newPanel = newPanel;

    //set border
    add(newPanel, BorderLayout.CENTER);



}
    public static void main(String[] args){
        CreateNotification createNotification = new CreateNotification();
        createNotification.setVisible(true);
        createNotification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createNotification.pack();
        createNotification.setLocationRelativeTo(null);
        createNotification.setTitle("Create Notification");


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

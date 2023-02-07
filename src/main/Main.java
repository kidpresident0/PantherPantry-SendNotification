package main;


import database.Database;
import logic.Users;
import presentation.SendNotificationGUI;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This is the main class for the Panther Pantry Create sendNotification.Notification application
 * @author John Christian
 * @version 2022.01.26
 */
public class Main {

    public static void main(String[] args)  {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
//        Database subEmails = new Database();
//        ArrayList<Users> subscribers = subEmails.getGetSubscriberEmail();
//
//        System.out.println(subscribers);
//        for (Users user : subscribers) {
//            System.out.println(user.getEmail());
//        }
    }

    private static void createGUI() {
        SendNotificationGUI ui = new SendNotificationGUI();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    public ArrayList<Users> subscriberEmails(){
        Database subEmails = new Database();
        ArrayList<Users> subscribers = subEmails.getGetSubscriberEmail();
        System.out.println(subscribers);
        for (Users user : subscribers) {
            System.out.println(user.getEmail());
        }
        return subscribers;
    }


}

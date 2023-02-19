package main;

import presentation.AccountCreation;
import presentation.AccountLogin;
import presentation.SendNotificationGUI;

import javax.swing.*;

/**
 * Main class for the Panther Pantry application
 * @author Sevin Webb, John Christian
 * @version 2023.02.18
 */

public class Main {
    private static JFrame frame = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    public static void createGUI() {
        frame = new JFrame("Panther Pantry Account Creation");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        accountCreate();
    }

    public static void sendNotification() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new SendNotificationGUI().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Panther Pantry Account Creation");
    }

    public static void accountCreate() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new AccountCreation().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Panther Pantry Account Creation");
    }

    public static void accountLogin() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new AccountLogin().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Panther Pantry Login");
    }
}

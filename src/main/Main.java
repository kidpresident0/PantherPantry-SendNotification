package main;

import presentation.*;
import javax.swing.*;


/**
 * Main class for the Panther Pantry application
 * @author Sevin Webb, John Christian, Kate White
 * @version 2023.02.18
 */

public class
Main {
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
        //logUI();
        //sendNotification();
        launchGUI();

    }

    public static void launchGUI() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new launchGUI().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Panther Pantry Notification System");
    }

    public static void logUI() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new LogUI().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Panther Pantry Account Creation");
    }

    public static void sendNotification() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new SendNotificationGUI().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Send Notification");
    }

    public static void createTemplate() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new TabbedPaneFrame().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Panther Pantry Account Creation");
        ImageIcon image = new ImageIcon("Logo.png");
        frame.setIconImage(image.getImage());
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


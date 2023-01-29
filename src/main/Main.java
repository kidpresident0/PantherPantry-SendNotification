package main;

import presentation.ReviewNotificationLogUI;

import javax.swing.*;

/**
 * This is the main class for the Panther Pantry Create sendNotification.Notification application
 * @author John Christian
 * @version 2022.01.26
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI() {
        ReviewNotificationLogUI ui = new ReviewNotificationLogUI();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

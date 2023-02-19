package main;

import presentation.SendNotificationGUI;

import javax.swing.*;

/**
 * This is the main class for the Panther Pantry Send Notification application
 *
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

    // Construct the GUI
    private static void createGUI() {
        SendNotificationGUI ui = new SendNotificationGUI();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();

        frame.setTitle("Send Notification");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(400, 200);

    }

}

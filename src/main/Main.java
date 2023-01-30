package main;

/**
 * This is the main class for the Panther Pantry Create sendNotification.Notification application
 * @author John Christian
 * @version 2022.01.30
 */

import presentation.Template;
import javax.swing.*;

/**
 * Main entry point for the program.
 * <p>
 * Kick off the application by calling the start method of the Controller class.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
                /*
                ArrayList<Show> shows = Show.findShows();
                for (Show show: shows) {
                    System.out.println(show.getID() + ": " + show.getTitle());
                }
                 */
            }
        });
    }

    public static void createGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Create a JFrame to show our form in, and display the UsersTableGUI form.
        JFrame frame = new JFrame("Template Name");

        // Makes the application close when the window goes away.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Template app = new Template();

        JPanel root = app.getRootPanel();

        frame.getContentPane().removeAll();
        frame.getContentPane().add(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


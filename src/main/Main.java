package main;

/**
 * This is the main class for the Panther Pantry creates Template presentation application
 * @author Kate White
 * @version 2023.01.30
 */

import presentation.ShowTemplate;
import javax.swing.*;
import java.awt.*;

/**
 * Main entry point for the program.
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
    public static void createGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Create a JFrame to show our form in, and display the UsersTableGUI form.
        JFrame frame = new JFrame("Template");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Makes the application close when the window goes away.
        ShowTemplate app = new ShowTemplate();

        JPanel root = app.getRootPanel();

        frame.getContentPane().removeAll();
        frame.getContentPane().add(root);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("Logo.png");
        frame.setIconImage(image.getImage());
    }
}


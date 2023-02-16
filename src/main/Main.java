package main;

import presentation.AccountCreation;
import presentation.AccountLogin;

import javax.swing.*;

/**
 * Main class for the Panther Pantry account creation and login application
 * @author Sevin Webb
 * @version 2023.02.13
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
       AccountCreate();
    }

    public static void AccountCreate() {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(new AccountCreation().getRootPanel());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setTitle("Panther Pantry Account Creation");
    }

    public static void AccountLogin() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new AccountLogin().getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Panther Pantry Login");
    }
}

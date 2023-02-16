package presentation;

import main.Main;
import logic.User;
import logic.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * GUI class for displaying the account creation screen as well as functionality needed to move data throughout the application.
 * @author Sevin Webb
 * @version 2023.02.13
 */

public class AccountCreation {
     JPanel rootPanel;
     JTextField usernameField;
     JTextField emailField;
     JTextField passwordField;
     JTextField verificationField;
     JTextField nameField;
     JTextArea topText;
     JTextArea bottomText;
     JButton createSubmitButton;
     JLabel usernameLabel;
     JPanel createEmailLabel;
     JLabel passwordLabel;
     JLabel passwordVerificationLabel;
     JLabel nameLabel;
     JLabel emailLabel;
     JLabel createToLoginButton;

    public AccountCreation() {
        createToLoginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Main.AccountLogin();
            }
        });
        createSubmitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createToLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validateCreation();
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * Takes the given name from account creation and splits it into first and last by a space character
     */
    public static String[] splitNames(String name){
        String[] names = name.split(" ", 2);
        return names;
    }

    /**
     * When the create account button is clicked, this sequence will begin to verify that all fields are correct before writing to the database.
     */
    public void validateCreation() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String passwordVerify = verificationField.getText();
        String name = nameField.getText();
        String firstName;
        String lastName;
        String encryptedPassword;
        boolean isEmail;
        boolean emailCheck;
        boolean usernameCheck;

        //checks that all fields are filled in
        if (username.equals("") || password.equals("") || passwordVerify.equals("") ||  name.equals("") ||  email.equals("") ) {
            JOptionPane.showMessageDialog(null, "Please fill out all fields", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //checks that the email has a proper format
        isEmail = User.isEmail(email);
        if (!isEmail) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //checks that the password and password verification match
        if (!User.compareString(password, passwordVerify)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //first checks that the username isn't taken
        usernameCheck = User.checkUsername(username);
        if (!usernameCheck) {
            //then checks that the email isn't used
            emailCheck = User.checkEmail(email);
            if (!emailCheck) {
                JOptionPane.showMessageDialog(null, "Email confirmation has been sent.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                String[] names = splitNames(name);
                firstName = names[0];
                if(names.length >= 2) {
                    lastName = names[1];
                } else {
                    lastName = "";
                }
                //only writes to the database if both checks pass, password is not encrypted
                encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
                User.addUser(firstName, lastName, encryptedPassword, email, username);
                return;
            }
            //confirmation with no entry in the database for user privacy
            JOptionPane.showMessageDialog(null, "Email confirmation has been sent.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Email confirmation has been sent.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
}

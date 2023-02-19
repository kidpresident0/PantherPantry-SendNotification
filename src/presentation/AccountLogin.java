package presentation;

import logic.User;
import main.Main;
import logic.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * GUI class for displaying the account login screen as well as functionality needed to move data throughout the application.
 * @author Sevin Webb
 * @version 2023.02.13
 */

public class AccountLogin {
     JPanel rootPanel;
     JTextArea loginIntroText;
     JTextField emailOrUsername;
     JTextField loginPasswordField;
     JButton loginButton;
     JTextArea loginBottomText;
     JLabel loginToCreateButton;

    public AccountLogin() {
        loginToCreateButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Main.accountCreate();
            }
        });
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginToCreateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validateLogin();
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * When the login button is clicked, this sequence will begin to verify the input values exist in the database
     */
    public void validateLogin() {
        boolean emailCheck = false;
        boolean isUsername = false;
        String passwordCheck = "";
        String name = emailOrUsername.getText();
        String password = loginPasswordField.getText();

        //checks that fields are filled in
        if (name.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Username and Password must be supplied", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        isUsername = User.checkUsername(name);
        if (!isUsername) {
            emailCheck = User.checkEmail(name);
            if (!emailCheck) {
                JOptionPane.showMessageDialog(null, "Login failed, check information and try again.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            passwordCheck = User.getPasswordEmail(name);
            String passwordErrorCorrect = "$2a" + passwordCheck.substring(3);
            if (BCrypt.checkpw(password, passwordErrorCorrect)) {
                JOptionPane.showMessageDialog(null, "You have successfully logged in.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Login failed, check information and try again.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        passwordCheck = User.getPasswordUsername(name);
        String passwordErrorCorrect = "$2a" + passwordCheck.substring(3);
        if (BCrypt.checkpw(password, passwordErrorCorrect)) {
            JOptionPane.showMessageDialog(null, "You have successfully logged in.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Login failed, check information and try again.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

}

package presentation;

import logic.User;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                Main.AccountCreate();
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
        if (name.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Username and Password must be supplied", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        isUsername = User.checkUsername(name);
        if (!isUsername) {
            emailCheck = User.isEmail(name);
            if (!emailCheck) {
                JOptionPane.showMessageDialog(null, "Email or Username is not valid.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            passwordCheck = User.getPasswordEmail(name);
            if(password.equals(passwordCheck)) {
                JOptionPane.showMessageDialog(null, "You have successfully logged in.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Email and Password do not match.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        passwordCheck = User.getPasswordUsername(name);
        if(password.equals(passwordCheck)) {
            JOptionPane.showMessageDialog(null, "You have successfully logged in.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Username and Password do not match.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
    }

}

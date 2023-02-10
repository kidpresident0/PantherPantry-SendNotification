package presentation;

import main.Main;
import logic.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AccountCreation {
     JPanel rootPanel;
     JTextField username;
     JTextField email;
     JTextField password;
     JTextField verification;
     JTextField name;
     JTextArea CreateIntroText;
     JTextArea CreateBottomText;
     JButton CreateSubmitButton;
     JLabel CreateUsernameLabel;
     JPanel CreateEmailLabel;
     JLabel CreatePasswordLabel;
     JLabel CreatePasswordVerificationLabel;
     JLabel CreateNameLabel;
     JLabel CreateEmailText;
     JLabel CreateToLoginButton;

    public AccountCreation() {
        CreateToLoginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Main.AccountLogin();
            }
        });
        CreateSubmitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CreateToLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CreateSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validateCreation();
                JOptionPane.showMessageDialog(null, "Wow you really clicked that.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * When the create account button is clicked, this sequence will begin to verify that all fields are correct before writing to the database.
     */
    public static void validateCreation() {
        // email = getEmail()
        // username = getUsername()
        // password = getPassword()
        // passwordVerify = getPasswordVerify()
        // name = getName()
        // if (email != null && username != null && password != null && passwordVerify != null && name != null)
        //     if (isEmail(email) == true)
        //         if (compareString(password, passwordVerify) == true)
        //              splitNames(name)
        //              Database.addSubscriber()
        //
        //
        //
    }



}

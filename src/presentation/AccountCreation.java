package presentation;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AccountCreation {
     JPanel rootPanel;
     JTextField CreateUsernameInput;
     JTextField CreateEmailInput;
     JTextField CreatePasswordInput;
     JTextField CreateVerificationInput;
     JTextField CreateNameInput;
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

    public JPanel getRootPanel() {
        return rootPanel;
    }

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
                JOptionPane.showMessageDialog(null, "Wow you really clicked that.", "Thanks", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

}

package presentation;

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
     JTextField loginUserEmailField;
     JTextField loginPasswordField;
     JButton loginButton;
     JTextArea loginBottomText;
     JLabel LoginToCreateButton;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public AccountLogin() {
        LoginToCreateButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Main.AccountCreate();
            }
        });

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LoginToCreateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Wow you really clicked that.", "Thanks", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}

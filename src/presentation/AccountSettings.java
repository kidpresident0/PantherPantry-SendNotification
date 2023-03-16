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
 * GUI class for account settings. Takes a user object with information about the current users and displays fields to adjust current settings.
 * @author Sevin Webb
 * @version 2023.03.14
 */

public class AccountSettings {
    private JPanel rootPanel;
    private JLabel introText;
    private JTextField emailInput;
    private JTextField firstInput;
    private JTextField lastInput;
    private JTextField passwordInput;
    private JTextField confirmInput;
    private JTextField phoneInput;
    private JTextField usernameInput;
    private JComboBox receiveComboBox;
    private JComboBox typeComboBox;
    private JButton backToMenuButton;
    private JButton applyChangesButton;
    private JLabel username;
    private JLabel email;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel password;
    private JLabel confirmPassword;
    private JLabel phone;
    private JLabel receiveText;
    private JLabel typeText;
    private JLabel deleteText;
    private JPanel JPanel;
    private JLabel deleteButton;

    public AccountSettings(User currentUser) {

        usernameInput.setText(currentUser.getActualUsername());
        emailInput.setText(currentUser.getEmail());
        firstInput.setText(currentUser.getFirstName());
        lastInput.setText(currentUser.getLastName());
        phoneInput.setText(currentUser.getPhoneNumber());

        receiveComboBox.addItem("Yes");
        receiveComboBox.addItem("No");
        receiveComboBox.setSelectedItem(currentUser.getReceiveNotifications());

        typeComboBox.addItem("Email");
        typeComboBox.addItem("SMS");
        typeComboBox.addItem("Both");
        typeComboBox.setSelectedItem(currentUser.getNotificationType());

        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                User.deleteUser(currentUser.getUserID());
                JOptionPane.showMessageDialog(null, "Your account has been deleted. Returning to menu.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                Main.accountLogin();
            }
        });

        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.accountLogin();
            }
        });

        applyChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameInput.getText();
                String firstName = firstInput.getText();
                String lastName = lastInput.getText();
                String phoneNumber = phoneInput.getText();
                String email = emailInput.getText();
                String password = currentUser.getPassword();
                String newPassword = passwordInput.getText();
                String verifyPassword = confirmInput.getText();
                String receiveNotifications = receiveComboBox.getSelectedItem().toString();
                String notificationType = typeComboBox.getSelectedItem().toString();

                if (!newPassword.equals("") && !verifyPassword.equals("")) {
                    if (User.compareString(newPassword, verifyPassword)) {
                        password = BCrypt.hashpw(newPassword, BCrypt.gensalt(10));
                    } else if (!User.compareString(newPassword, verifyPassword)) {
                        JOptionPane.showMessageDialog(null, "New passwords do not match.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }

                if(username.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a username.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                //checks that the email has a proper format
                boolean isEmail = User.isEmail(email);
                if (!isEmail) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                // Ensures that the email is unique UNLESS it belongs to the userID that is attempting to use it
               Integer emailCheckID = User.getUserIDEmail(email);
                if (currentUser.getUserID() != emailCheckID && User.checkEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Unable to update information please try again.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Ensures that the username is unique UNLESS it belongs to the userID that is attempting to use it
                Integer usernameCheckID = User.getUserIDUsername(username);
                if (currentUser.getUserID() != usernameCheckID && User.checkUsername(username)) {
                    JOptionPane.showMessageDialog(null, "Username already taken.", "Panther Pantry", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                User.updateUser(username, firstName, lastName, password, email, phoneNumber, receiveNotifications,notificationType, currentUser.getUserID());

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}

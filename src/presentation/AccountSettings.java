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

public class AccountSettings {
    private JPanel rootPanel;
    private JLabel introText;
    private JTextField emailInput;
    private JTextField firstInput;
    private JTextField lastInput;
    private JPasswordField passwordInput;
    private JPasswordField confirmInput;
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
    private JLabel DeleteButton;

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

        applyChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameInput.getText();
                String firstName = firstInput.getText();
                String lastName = lastInput.getText();
                String phoneNumber = phoneInput.getText();
                String email = emailInput.getText();
                String password = passwordInput.getText();
                String receiveNotifications = receiveComboBox.getSelectedItem().toString();
                String notificationType = typeComboBox.getSelectedItem().toString();

                User.updateUser(username, firstName, lastName, password, email, phoneNumber, receiveNotifications,notificationType, currentUser.getUserID());

                //FOR TESTING - REMOVE LATER
                System.out.println(emailInput.getText() + " " + firstInput.getText() +  " " + lastInput.getText() +  " " + passwordInput.getText() + " " +
                        confirmInput.getText() + " " +  phoneInput.getText() + " " +  usernameInput.getText() + " " +  receiveComboBox.getSelectedItem().toString() + " " +
                        typeComboBox.getSelectedItem().toString());

                System.out.println(currentUser.getEmail() + " " + currentUser.getPassword() + " " + currentUser.getFirstName() + " " + currentUser.getLastName() + " " + currentUser.getActualUsername() + " " + currentUser.getPhoneNumber() + " " + currentUser.getReceiveNotifications() + " " + currentUser.getNotificationType());
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}

package presentation;

import main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchGUI {
    private JPanel topSpacer;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel bottomSpacer;
    private JButton templateButton;
    private JButton sendNotificationButton;
    private JButton logButton;
    private JPanel rootPanel;

    public LaunchGUI() {
        templateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.createTemplate();

            }
        });
        sendNotificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.sendNotification();

            }
        });
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.logUIAdvanced();

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}

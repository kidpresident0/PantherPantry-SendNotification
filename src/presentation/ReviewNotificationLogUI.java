package presentation;

import javax.swing.*;

public class ReviewNotificationLogUI {
    private JPanel rootPanel;
    private JTable notificationLogTable;
    private JButton submitButton;
    private JButton closeButton;
    private JTextField enterStartDate;
    private JTextField enterEndDate;
    private JLabel endDate;
    private JLabel startDate;

    public ReviewNotificationLogUI() {
        rootPanel = new JPanel();
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}

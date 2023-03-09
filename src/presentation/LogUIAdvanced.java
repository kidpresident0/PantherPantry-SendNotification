package presentation;

import logic.Log;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * This is the ReviewNotificationLog's Graphic User Interface Class that will launch its advanced search application.
 * @author Brandon King
 * @version 2022.03.06
 */

public class LogUIAdvanced {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;
    private JLabel endLabel;
    private JLabel startLabel;
    private JButton submitButton;
    private JDatePicker startDatePicker;
    private JDatePicker endDatePicker;
    private JButton closeButton;
    private JTable logTable;
    private JTextField staffTextInput;
    private JButton staffSubmitButton;
    private JButton staffCloseButton;
    private JButton subjectSubmitButton;
    private JButton searchCloseButton;
    private JTextField subjectTextInput;
    private JButton messageSubmitButton;
    private JTextField messageTextInput;
    private JButton messageCloseButton;
    private JTable staffTable;
    private JTable subjectTable;
    private JTable messageTable;
    private DefaultTableModel m_LogTableModel;

    public LogUIAdvanced() {
        startDatePicker.getModel().setDate(2023, 1, 1);
        fetchDates();
        fetchUser();
        fetchSubject();
        fetchMessage();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchDates();
            }
        });
        staffSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchUser();
            }
        });
        subjectSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchSubject();
            }
        });
        messageSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchMessage();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        staffCloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        messageCloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
    public JTabbedPane getTabbedPane() { return tabbedPane;}

    private void setupTable () {
        //Create a default table model with 5 columns named User, Date/Time, Subject, Message, Subscribers
        m_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count"}
        ) {
            //stops user from editing the table
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        //applies the model to the table
        logTable.setModel(m_LogTableModel);

        //centers the values in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        logTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        logTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        logTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        logTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        logTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        // center colum
        ((DefaultTableCellRenderer)logTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        logTable.getColumnModel().getColumn(0).setMinWidth(50);
        logTable.getColumnModel().getColumn(1).setMinWidth(50);
        logTable.getColumnModel().getColumn(2).setMinWidth(50);
        logTable.getColumnModel().getColumn(3).setMinWidth(50);
        logTable.getColumnModel().getColumn(4).setMinWidth(50);
    }

    private void setupStaffTable () {
        //Create a default table model with 5 columns named User, Date/Time, Subject, Message, Subscribers
        m_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count"}
        ) {
            //stops user from editing the table
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        //applies the model to the table
        staffTable.setModel(m_LogTableModel);

        //centers the values in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        staffTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        // center colum
        ((DefaultTableCellRenderer)staffTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        staffTable.getColumnModel().getColumn(0).setMinWidth(50);
        staffTable.getColumnModel().getColumn(1).setMinWidth(50);
        staffTable.getColumnModel().getColumn(2).setMinWidth(50);
        staffTable.getColumnModel().getColumn(3).setMinWidth(50);
        staffTable.getColumnModel().getColumn(4).setMinWidth(50);
    }

    private void setupSubjectTable () {
        //Create a default table model with 5 columns named User, Date/Time, Subject, Message, Subscribers
        m_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count"}
        ) {
            //stops user from editing the table
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        //applies the model to the table
        subjectTable.setModel(m_LogTableModel);

        //centers the values in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        subjectTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        // center colum
        ((DefaultTableCellRenderer)subjectTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        subjectTable.getColumnModel().getColumn(0).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(1).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(2).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(3).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(4).setMinWidth(50);
    }

    private void setupMessageTable () {
        //Create a default table model with 5 columns named User, Date/Time, Subject, Message, Subscribers
        m_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count"}
        ) {
            //stops user from editing the table
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        //applies the model to the table
        messageTable.setModel(m_LogTableModel);

        //centers the values in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        messageTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        messageTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        messageTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        messageTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        messageTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        // center colum
        ((DefaultTableCellRenderer)messageTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        messageTable.getColumnModel().getColumn(0).setMinWidth(50);
        messageTable.getColumnModel().getColumn(1).setMinWidth(50);
        messageTable.getColumnModel().getColumn(2).setMinWidth(50);
        messageTable.getColumnModel().getColumn(3).setMinWidth(50);
        messageTable.getColumnModel().getColumn(4).setMinWidth(50);
    }

    private void fetchDates() {
        setupTable();
        DateModel startCal = startDatePicker.getModel();
        String startDateString = (1 + startCal.getMonth()) + "/" + startCal.getDay() + "/" + startCal.getYear();
        DateModel endCal = endDatePicker.getModel();
        String endDateString = (1 + endCal.getMonth()) + "/" + endCal.getDay() + "/" + endCal.getYear();
        ArrayList<Log> logs = Log.findLogs(startDateString, endDateString);
        for (Log log: logs) {
            m_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount()
            });
        }
    }

    private void fetchUser() {
        setupStaffTable();
        String staffText = String.valueOf(staffTextInput);
        ArrayList<Log> users = Log.findUser(staffText);
        for (Log log: users) {
            m_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount()
            });
        }
    }

    private void fetchSubject() {
        setupSubjectTable();
        String subjectText = String.valueOf(subjectTextInput);
        ArrayList<Log> subjects = Log.findSubject(subjectText);
        for (Log log: subjects) {
            m_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount()
            });
        }
    }

    private void fetchMessage() {
        setupMessageTable();
        String messageText = String.valueOf(messageTextInput);
        ArrayList<Log> messages = Log.findMessage(messageText);
        for (Log log: messages) {
            m_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount()
            });
        }
    }
}

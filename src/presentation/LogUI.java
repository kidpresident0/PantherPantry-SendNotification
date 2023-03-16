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

public class LogUI {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;
    private JLabel endLabel;
    private JLabel startLabel;
    private JButton submitButton;
    private JDatePicker startDatePicker;
    private JDatePicker endDatePicker;
    private JButton closeButton;
    private JTable dateTable;
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
    private DefaultTableModel d_LogTableModel;
    private DefaultTableModel u_LogTableModel;
    private DefaultTableModel s_LogTableModel;
    private DefaultTableModel m_LogTableModel;

    public LogUI() {
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

    private void setupDateTable () {
        //Create a default table model with 6 columns named User, Date/Time, Subject, Message, Subscribers, Type
        d_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count", "Type"}
        ) {
            //stops user from editing the table
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        //applies the model to the table
        dateTable.setModel(d_LogTableModel);

        //centers the values in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        dateTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        dateTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        dateTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        dateTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        dateTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        dateTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        // center colum
        ((DefaultTableCellRenderer)dateTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        dateTable.getColumnModel().getColumn(0).setMinWidth(50);
        dateTable.getColumnModel().getColumn(1).setMinWidth(50);
        dateTable.getColumnModel().getColumn(2).setMinWidth(50);
        dateTable.getColumnModel().getColumn(3).setMinWidth(50);
        dateTable.getColumnModel().getColumn(4).setMinWidth(50);
        dateTable.getColumnModel().getColumn(5).setMinWidth(50);
    }

    private void setupStaffTable () {
        //Create a default table model with 6 columns named User, Date/Time, Subject, Message, Subscribers, Type
        u_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count", "Type"}
        ) {
            //stops user from editing the table
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        //applies the model to the table
        staffTable.setModel(u_LogTableModel);

        //centers the values in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        staffTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        // center colum
        ((DefaultTableCellRenderer)staffTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        staffTable.getColumnModel().getColumn(0).setMinWidth(50);
        staffTable.getColumnModel().getColumn(1).setMinWidth(50);
        staffTable.getColumnModel().getColumn(2).setMinWidth(50);
        staffTable.getColumnModel().getColumn(3).setMinWidth(50);
        staffTable.getColumnModel().getColumn(4).setMinWidth(50);
        staffTable.getColumnModel().getColumn(5).setMinWidth(50);
    }

    private void setupSubjectTable () {
        //Create a default table model with 6 columns named User, Date/Time, Subject, Message, Subscribers, Type
        s_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count", "Type"}
        ) {
            //stops user from editing the table
            @Override
            public boolean isCellEditable(int row, int column) {return false;}
        };

        //applies the model to the table
        subjectTable.setModel(s_LogTableModel);

        //centers the values in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        subjectTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        subjectTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);


        // center colum
        ((DefaultTableCellRenderer)subjectTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        subjectTable.getColumnModel().getColumn(0).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(1).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(2).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(3).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(4).setMinWidth(50);
        subjectTable.getColumnModel().getColumn(5).setMinWidth(50);
    }

    private void setupMessageTable () {
        //Create a default table model with 6 columns named User, Date/Time, Subject, Message, Subscribers, Type
        m_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User", "Date/Time", "Subject", "Message", "Subscriber Count", "Type"}
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
        messageTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        // center colum
        ((DefaultTableCellRenderer)messageTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //adjust column widths
        messageTable.getColumnModel().getColumn(0).setMinWidth(50);
        messageTable.getColumnModel().getColumn(1).setMinWidth(50);
        messageTable.getColumnModel().getColumn(2).setMinWidth(50);
        messageTable.getColumnModel().getColumn(3).setMinWidth(50);
        messageTable.getColumnModel().getColumn(4).setMinWidth(50);
        messageTable.getColumnModel().getColumn(5).setMinWidth(50);
    }

    private void fetchDates() {
        setupDateTable();
        DateModel startCal = startDatePicker.getModel();
        String startDateString = (1 + startCal.getMonth()) + "/" + startCal.getDay() + "/" + startCal.getYear();
        DateModel endCal = endDatePicker.getModel();
        String endDateString = (1 + endCal.getMonth()) + "/" + endCal.getDay() + "/" + endCal.getYear();
        ArrayList<Log> logs = Log.findLogs(startDateString, endDateString);
        for (Log log: logs) {
            d_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount(),
                    log.getType()
            });
        }
    }

    private void fetchUser() {
        setupStaffTable();
        String staffText = staffTextInput.getText();
        ArrayList<Log> users = Log.findUser(staffText);
        for (Log log: users) {
            u_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount(),
                    log.getType()
            });
        }
    }

    private void fetchSubject() {
        setupSubjectTable();
        String subjectText = subjectTextInput.getText();
        ArrayList<Log> subjects = Log.findSubject(subjectText);
        for (Log log: subjects) {
            s_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount(),
                    log.getType()
            });
        }
    }

    private void fetchMessage() {
        setupMessageTable();
        String messageText = messageTextInput.getText();
        ArrayList<Log> messages = Log.findMessage(messageText);
        for (Log log: messages) {
            m_LogTableModel.addRow(new Object[]{
                    log.getSentBy(),
                    log.getSentDateTime(),
                    log.getSubject(),
                    log.getMessageBody(),
                    log.getSubscriberCount(),
                    log.getType()
            });
        }
    }
}

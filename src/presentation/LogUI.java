package presentation;

import com.toedter.calendar.JDateChooser;
import logic.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
/**
 * This is the ReviewNotificationLog Graphic User Interface Class that will launch its application
 * @author Brandon King
 * @version 2022.02.04
 */

public class LogUI {
    private JPanel rootPanel;
    private JTable logTable;
    private JButton submitButton;
    private JButton closeButton;
    private JLabel endLabel;
    private JLabel startLabel;
    private DefaultTableModel m_LogTableModel;



    public LogUI() {
        fetchData();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }


    private void setupTable () {
        //Create a default table model with 5 columns named UserID, Date/Time, Subject, Message, Subscribers
        m_LogTableModel = new DefaultTableModel(
                //initial date which is empty
                new Object[][]{},
                //initial columns
                new Object[] {"User ID", "Date", "Subject", "Message", "Subscribers"}
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

    private void fetchData() {
        setupTable();
        ArrayList<Log> logs = Log.findLogs();
        for (Log log: logs) {
            m_LogTableModel.addRow(new Object[]{
                    Log.getUserID(),
                    Log.getDate(),
                    Log.getSubject(),
                    Log.getMessageBody(),
                    Log.getSubscriberAmount()
            });
        }
    }
}


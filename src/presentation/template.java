import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

public class template {
    private JFormattedTextField titleTextField;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel campusLabel;
    private JLabel foodLabel;
    private JLabel startTime;
    private JLabel endTimeLabel;
    private JLabel termLabel;
    private JLabel staffNameLabel;
    private JTextField textField7;
    private JTextArea textArea;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel messagePanel;
    private JPanel blocksTextPanel;
    private JPanel buttonPanel;
    private JButton previewButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Template Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new template().mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public template() {
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager UI = new UIManager();
                UI.put("OptionPane.background", Color.decode("#BBBBBB"));
                UI.put("Panel.background", Color.decode("#BBBBBB"));
                UIManager.put("Button.background", Color.decode("#9B9B9B"));

                Object[] buttons = {"Back", "Create"};
                int option = JOptionPane.showOptionDialog(mainPanel, textArea.getText(),
                        "Template Name", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, buttons, buttons[0]);
            }
        });
    }
}



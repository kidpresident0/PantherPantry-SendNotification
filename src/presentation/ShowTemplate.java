package presentation;

/**
 * This presentation.Template.java file handles the action events of the preview
 * and create buttons in this application. The preview button displays a dialog box
 * with the generated text (a message to a Food Pantry subscribers) in the template, and the
 * create button allows the user to save all completed text in the text fields of the application.
 * @author Kate White
 * @version 2023.01.30
 */

import logic.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

public class ShowTemplate {
    private JFormattedTextField titleTextField;
    private JTextField campus;
    private JTextField foodItems;
    private JTextField startTime;
    private JTextField endTime;
    private JTextField term;
    private JTextField staffName;
    private JLabel campusLabel;
    private JLabel foodItemsLabel;
    private JLabel startTimeLabel;
    private JLabel endTimeLabel;
    private JLabel termLabel;
    private JLabel staffNameLabel;
    private JTextField subject;
    private JTextArea templateText;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel messagePanel;
    private JPanel blocksTextPanel;
    private JPanel buttonPanel;
    private JButton previewButton;
    private JButton createButton;

    public ShowTemplate() {
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager UI = new UIManager();
                UI.put("OptionPane.background", Color.decode("#BBBBBB"));
                UI.put("Panel.background", Color.decode("#BBBBBB"));
                UIManager.put("Button.background", Color.decode("#9B9B9B"));

                Object[] buttons = {"Back", "Create"};
                int option = JOptionPane.showOptionDialog(rootPanel, templateText.getText(),
                        "Template Name", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, buttons, buttons[0]);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Template template = new Template(subject.getText(), templateText.getText(), campus.getText(), foodItems.getText(),
                        startTime.getText(), endTime.getText(), term.getText(), staffName.getText());
                template.create();

                JOptionPane.showMessageDialog(rootPanel, "The template has been created");
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}



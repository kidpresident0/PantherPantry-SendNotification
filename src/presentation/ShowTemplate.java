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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowTemplate {
    private JTextArea templateText;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JButton createButton;
    private JTextField TemplateNameField;
    private JTextField SubjectField;
    private JLabel TemplateNameLabel;
    private JLabel SubjectLabel;
    private JLabel TextLabel;
    private JPanel bottomPanel;

    public ShowTemplate() {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Template template = new Template(1, TemplateNameField.getText(), SubjectField.getText(), templateText.getText());
                template.create();

                JOptionPane.showMessageDialog(rootPanel, "The template has been created");
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}



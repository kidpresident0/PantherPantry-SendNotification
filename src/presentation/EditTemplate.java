package presentation;

import javax.swing.*;

import logic.TemplateName;
import logic.Template;
import main.Main;

import java.awt.event.*;
import java.util.List;

public class EditTemplate {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JLabel labelAnnouncement;
    private JComboBox templateNameComboBox;
    private JTextField subjectTextField;
    private JPanel textPanel;
    private JTextArea textArea;
    private JButton saveButton;
    private JButton deleteButton;
    private JLabel temp;
    private JButton refreshButton;
    private JButton backButton;


    public EditTemplate() {
        List<TemplateName> templates = TemplateName.list(1);
        for (TemplateName template: templates) {
            templateNameComboBox.addItem(template);
        }
        // shows the comboBox empty where there is no selection of templates
        templateNameComboBox.setSelectedIndex(-1);

        // gets the selection of templateNames from the comboBox
        // and sets the subject and template text corresponding to the selected templateName
        templateNameComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateName name = (TemplateName) templateNameComboBox.getSelectedItem();
                if (name != null) {
                    Template template = Template.getTemplate(name.getId());
                    String subject = template.getSubject();
                    String text = template.getTemplateText();

                    // sets the value for specific text fields in the GUI
                    subjectTextField.setText(subject);
                    textArea.setText(text);
                } else {
                    // sets empty text fields in the template on click of delete button
                    subjectTextField.setText("");
                    textArea.setText("");
                }
            }
        });

        // gets the selection of templateNames from the comboBox
        // updates subject and text fields in the template in the database
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateName name = (TemplateName) templateNameComboBox.getSelectedItem();
                if (name != null) {
                    Template template = new Template(0, name.getName(), subjectTextField.getText(), textArea.getText());
                    template.update(name.getId());
                    JOptionPane.showMessageDialog(
                            rootPanel,
                            "Template saved successfully!"
                    );
                } else {
                    // shows a warning message when the templateName in comboBox wasn't selected
                    JOptionPane.showMessageDialog(
                            rootPanel,
                            "Please select a template.",
                            "Warning", JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        // Deletes a row of data from the database selected from the comboBox
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateName name = (TemplateName) templateNameComboBox.getSelectedItem();
                if (name != null) {
                    // shows a warning message if the user is sure to remove the template from the database
                    int result = JOptionPane.showConfirmDialog(
                        rootPanel,
                        "Are you sure you want to delete this template?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );
                    if(result == JOptionPane.YES_OPTION){
                        Template.delete(name.getId());
                        templateNameComboBox.removeItem(name);
                        templateNameComboBox.setSelectedIndex(-1);
                    }
                } else {
                    // shows a warning message when the templateName in comboBox wasn't selected
                    JOptionPane.showMessageDialog(
                            rootPanel,
                            "Please select a template.",
                            "Warning", JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        // refreshes a comboBox after creating a new template
        // first it removes all existing template names
        // a new list of templateNames will be added after the warning message
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        rootPanel,
                        "All changes will be lost. Are you sure you want to proceed?",
                        "Confirm Refresh",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if(result == JOptionPane.YES_OPTION){
                    templateNameComboBox.removeAllItems();
                    List<TemplateName> templates = TemplateName.list(1);
                    for (TemplateName template: templates) {
                        templateNameComboBox.addItem(template);
                    }
                    templateNameComboBox.setSelectedIndex(-1);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.launchGUI();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

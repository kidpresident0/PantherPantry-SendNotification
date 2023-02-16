package presentation;

import javax.swing.*;

import logic.TemplateName;
import logic.Template;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton clearButton;
    private JLabel temp;


    public EditTemplate() {

        List<TemplateName> templates = TemplateName.list(1);
        for (TemplateName template: templates) {
            templateNameComboBox.addItem(template);
        }
        // shows the comboBox empty where there is no selection of templates
        templateNameComboBox.setSelectedIndex(-1);

        // gets the selection of templateNames from the comboBox
        templateNameComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateName name = (TemplateName) templateNameComboBox.getSelectedItem();
                Template template = Template.getTemplate(name.getId());
                String subject = template.getSubject();
                String text = template.getTemplateText();

                // sets the value for specific text fields in the GUI
                subjectTextField.setText(subject);
                textArea.setText(text);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

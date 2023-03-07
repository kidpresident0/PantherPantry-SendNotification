package logic;

import database.Database;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This logic.Template.java file gets the text from the application form as input
 * and creates a new templates, updates and deletes templates from the database.
 * @author Kate White
 * @version 2023.15.02
 */
public class Template {
    private int UserID;
    private String TemplateName;
    private String Subject;
    private String TemplateText;

    public Template(int UserID, String TemplateName, String Subject, String TemplateText) {
        this.UserID = UserID;
        this.TemplateName = TemplateName;
        this.Subject = Subject;
        this.TemplateText = TemplateText;
    }

    // create a new Template in the database
    public void create() {
        Database.createTemplate(this);
    }

    public static Template getTemplate(int id) {
        return Database.getTemplate(id);
    }

    public int getUserID() {
        return UserID;
    }

    public String getTemplateName() {
        return TemplateName;
    }

    public String getSubject() {
        return Subject;
    }

    public String getTemplateText() {
        return TemplateText;
    }

    // update the template in the database
    public void update(int id) {
        Database.updateTemplate(id, Subject, TemplateText);
    }

    // deletes a row from the database
    public static void delete(int id) {
        Database.deleteTemplate(id);
    }

    public String createMessage(HashMap<String, String> tags) {
        String str = TemplateText;

        // iterate over all tags
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            // for each tag, find it in TemplateText and replace it and returns a new string.
            str = str.replace(entry.getKey(), entry.getValue());
        }

        return str;
    }
}




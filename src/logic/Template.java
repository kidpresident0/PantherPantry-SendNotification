package logic;

import database.Database;

import java.awt.*;
import java.util.List;

/**
 * This logic.Template.java file gets the text from the application form and creates a new template in the database.
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

}




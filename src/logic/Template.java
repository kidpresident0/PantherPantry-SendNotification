package logic;

import database.Database;
import java.util.List;

/**
 * This logic.Template.java file gets the text from the application form and creates a new template in the database.
 * @author Kate White
 * @version 2023.01.30
 */
public class Template {
    private String subject;
    private String templateText;
    private String campus;
    private String foodItems;
    private String startTime;
    private String endTime;
    private String term;
    private String staffName;

    public Template(String subject, String templateText, String campus, String foodItems,
                    String startTime, String endTime, String term, String staffName) {
        this.subject = subject;
        this.templateText = templateText;
        this.campus = campus;
        this.foodItems = foodItems;
        this.startTime= startTime;
        this.endTime = endTime;
        this.term = term;
        this.staffName = staffName;
    }

    // create a new Template in the database
    public void create() {
        Database.createTemplate(this);
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplateText() {
        return templateText;
    }

    public String getCampus() {
        return campus;
    }

    public String getFoodItems() {
        return foodItems;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTerm() {
        return term;
    }

    public String getStaffName() {
        return staffName;
    }
}




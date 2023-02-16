package logic;

import database.Database;

import java.util.List;

/**
 * This logic.TemplateName.java file gets the ID from the user input
 * and get a list of stored TemplateNames from the database.
 * @author Kate White
 * @version 2023.15.02
 */
public class TemplateName {
    private int id;
    private String name;


    public TemplateName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // returns stored TemplateNames from the database
    public static List<TemplateName> list(int userID) { return Database.listTemplates(userID);}

    public String toString() {
        return name;
    }

}

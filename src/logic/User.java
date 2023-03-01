package logic;

import database.Database;
import java.util.regex.Pattern;

/**
 * User class for Panther Pantry login and account creation. This class creates User objects that can be used to pass information to and from the database to the presentation layer.
 * @author Sevin Webb
 * @version 2023.02.13
 */

public class User {
    private String email;

    private String phone;
    private String username;
    private String password;
    private String subscriberName;
    private String firstName;
    private String lastName;
    private String role;
    private Integer userID;

    public User(String email, String phone, String username, String password, String subscriberName, String firstName, String lastName, String role, Integer userID) {
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.subscriberName = subscriberName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.userID = userID;
    }

    /**
     * This will check the format of a given string to see if it matches the required email format
     */
    public static boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return false;
        return pat.matcher(email).matches();
    }

    /**
     * Compares the strings given for the password and verification fields, if they are the same, the rest of the program can continue
     */
    public static boolean compareString(String password, String passwordVerify){
        if (password.compareTo(passwordVerify) == 0) {
            return true;

        } else
            return false;
    }

    public static boolean checkUsername(String name) {
         return Database.checkUsername(name);
    }

    public static boolean checkEmail(String name) {
         return Database.checkEmail(name);
    }

    public static String getPasswordEmail(String name) {
         return Database.checkPasswordEmail(name);
    }

    public static String getPasswordUsername(String name) {
        return Database.checkPasswordUsername(name);
    }

    public static void addUser(String firstName, String lastName, String password, String email, String username) {
        Database.addSubscriber(username, firstName, lastName, email, password);
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return subscriberName;
    }

    public void setUsername(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
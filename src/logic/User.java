package logic;
import database.Database;
import java.util.regex.Pattern;

/**
 * User class for Panther Pantry login and account creation. This class creates User objects that can be used to pass information to and from the database to the presentation layer.
 * @author Sevin Webb
 * @version 2023.02.13
 */

public class User {
    String email;
    String username;
    String password;
    String firstName;
    String lastName;

    public User(String email, String username, String password, String firstName, String lastName) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
}

package logic;

/**
 * This is a User class for the Panther Pantry project. It is used to create user objects
 * so that Notifications class can pull a list of subscriber role users from the database. Currently, only
 * the email addresses for the subscribers are required for this story.
 *
 * @author John Christian
 * @version 2023.01.30
 */
public class User {
    private String subscriberName;
    private String email;
    private String role;
    private String password;
    private Integer userID;

    /**
     * Create a User object
     *
     * @param email email address of the user
     */
    public User(String email) {
        this.subscriberName = subscriberName;
        this.email = email;
        this.userID = userID;
        this.role = role;
        this.password = password;
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


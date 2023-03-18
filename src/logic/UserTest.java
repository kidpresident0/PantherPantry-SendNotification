package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void isEmailValid() {
        String email = "test@example.com";
        assertEquals(true, User.isEmail(email));
    }

    @Test
    public void isEmailInvalid() {
        String email = "testexample.com";
        assertEquals(false, User.isEmail(email));
    }

    @Test
    public void compareStringValid() {
        String tester = "ThisIsATest123!";
        String tester2 = "ThisIsATest123!";
        assertEquals(true, User.compareString(tester, tester2));
    }

    @Test
    public void compareStringInvalid() {
        String tester = "ThisIsATest123!";
        String tester2 = "ThisIsATest126!";
        assertEquals(false, User.compareString(tester, tester2));
    }

    @Test
    public void checkUsername() {
        assertEquals(true, User.checkUsername("user2"));
    }

    @Test
    public void checkEmail() {
        assertEquals(true, User.checkEmail("instrumentaldad@nerv.org"));
    }

    @Test
    void getEmailTest() {
        User user = new User("thisemail@place.com", "5551234567", "fakeuser", "abc123!", "Fakemen"
        ,"Fake","Guy","User", 1);
        String expectedEmail = "thisemail@place.com";
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

}
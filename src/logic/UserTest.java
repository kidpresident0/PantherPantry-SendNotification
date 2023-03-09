package logic;

import org.junit.Test;

import static org.junit.Assert.*;

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
}
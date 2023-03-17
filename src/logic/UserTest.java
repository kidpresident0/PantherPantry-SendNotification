package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for User
 * @authors John Christian, ...
 * @version 2023.02.21
 */
class UserTest {

    @Test
    void getEmailTest() {
        User user = new User("thisemail@place.com", "5551234567", "fakeuser", "abc123!", "Fakemen"
        ,"Fake","Guy","User", 1);
        String expectedEmail = "thisemail@place.com";
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

}
package test;

import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    // Local subclass to test the abstract User class
    static class TestUser extends User {
        public TestUser(String login, int pin) {
            super(login, pin);
        }
    }

    @Test
    void testGetLogin() {
        User user = new TestUser("alice", 1111);
        assertEquals("alice", user.getLogin());
    }



}

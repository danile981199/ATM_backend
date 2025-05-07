package test;
import services.DatabaseService;

import models.Administrator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    @Test
    void testAdministratorConstructor() {
        Administrator admin = new Administrator("admin", 1111);
        assertNotNull(admin);
    }

    @Test
    void testCreateAccountDoesNotThrow() {
        Administrator admin = new Administrator("admin", 1111);

        // Generate a unique login for each test run
        String login = "testuser_" + System.currentTimeMillis();

        assertDoesNotThrow(() -> {
            admin.createAccount(login, 1234, 100.0);
        });

        // Optional: clean up
        DatabaseService.deleteUser(login);
    }


    @Test
    void testDeleteAccountDoesNotThrow() {
        Administrator admin = new Administrator("admin", 1111);
        assertDoesNotThrow(() -> {
            admin.deleteAccount("ghost");
        });
    }
}

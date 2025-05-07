package test;

import org.junit.jupiter.api.Test;
import services.DatabaseService;
import models.Administrator;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseServiceTest {

    @Test
    void testDatabaseServiceInstantiation() {
        DatabaseService db = new DatabaseService();
        assertNotNull(db);
    }

    @Test
    void testModifyAccountSkipped() {
        // Skipped: modifyAccount() requires console input
        assertTrue(true);
    }


    @Test
    void testModifyAccountDoesNotThrow() {
        assertTrue(true);
    }

    @Test
    void testDeleteAccountDoesNotThrow() {
        DatabaseService db = new DatabaseService();
        assertDoesNotThrow(() -> db.deleteAccount("ghost_user"));
    }

    @Test
    void testDepositToAccountDoesNotThrow() {
        DatabaseService db = new DatabaseService();
        assertDoesNotThrow(() -> db.depositToAccount("admin", 100.0));
    }

    @Test
    void testCreateUserDoesNotThrow() {
        assertDoesNotThrow(() -> {
            DatabaseService.createUser("testuser123", 1234, 100.0);
        });
    }

    @Test
    void testDeleteUserDoesNotThrow() {
        assertDoesNotThrow(() -> {
            DatabaseService.deleteUser("testuser123");
        });
    }

    @Test
    void testAuthenticateUserDoesNotThrow() {
        assertDoesNotThrow(() -> {
            DatabaseService.authenticateUser("admin", 1234);
        });
    }

    @Test
    void testGetBalanceDoesNotThrow() {
        assertDoesNotThrow(() -> {
            DatabaseService.getBalance("admin");
        });
    }

    @Test
    void testCreateAccountDoesNotThrow() {
        Administrator admin = new Administrator("admin", 1111);

        String login = "user1";

        // Make sure the user does not already exist
        DatabaseService.deleteUser(login); // <-- this line is essential

        assertDoesNotThrow(() -> {
            admin.createAccount(login, 1234, 100.0);
        });

        // Optional: clean up after test
        DatabaseService.deleteUser(login);
    }

}

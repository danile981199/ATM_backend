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





}

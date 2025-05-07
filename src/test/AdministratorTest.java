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




}

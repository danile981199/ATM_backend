package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.Customer;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("user1", 12345, 5000.0,true);
    }

    @Test
    public void testWithdrawCash_ValidAmount() {
        boolean result = customer.withdrawCash(1000.0);
        assertTrue(result);
        assertEquals(4000.0, customer.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawCash_TooMuch() {
        boolean result = customer.withdrawCash(6000.0);
        assertFalse(result);
        assertEquals(5000.0, customer.getBalance(), 0.001);
    }

    @Test
    public void testDepositCash_ValidAmount() {
        boolean result = customer.depositCash(2000.0);
        assertTrue(result);
        assertEquals(7000.0, customer.getBalance(), 0.001);
    }

    @Test
    public void testDepositCash_NegativeAmount() {
        boolean result = customer.depositCash(-500.0);
        assertFalse(result);
    }

    @Test
    public void testGetBalance() {
        assertEquals(5000.0, customer.getBalance(), 0.001);
    }
}

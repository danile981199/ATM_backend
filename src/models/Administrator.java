package models;

import java.util.HashMap;
import java.util.Map;

public class Administrator extends User {
    private Map<String, Customer> customers = new HashMap<>();

    public Administrator(String login, int pin) {
        super(login, pin);
    }

    public void createAccount(String login, int pin, double balance) {
        customers.put(login, new Customer(login, pin, balance));
        System.out.println("Account Created Successfully: " + login);
    }

    public void deleteAccount(String login) {
        if (customers.remove(login) != null) {
            System.out.println("Account Deleted Successfully: " + login);
        } else {
            System.out.println("Account Not Found.");
        }
    }
}

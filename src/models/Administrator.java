package models;

import services.DatabaseService;

public class Administrator extends User {

    public Administrator(String login, int pin) {
        super(login, pin);
    }

    public void createAccount(String login, int pin, double balance) {
        if (DatabaseService.createUser(login, pin, balance)) {
            System.out.println("Account Created Successfully: " + login);
        } else {
            System.out.println("Failed to create account.");
        }
    }

    public void deleteAccount(String login) {
        if (DatabaseService.deleteUser(login)) {
            System.out.println("Account Deleted Successfully: " + login);
        } else {
            System.out.println("Account Not Found.");
        }
    }
}

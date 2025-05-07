package models;


import services.DatabaseService;

public class Customer extends User {

    private double balance;
    private boolean isTest = false;

    // For production use (syncs with DB)
    public Customer(String login, int pin, double balance) {
        super(login, pin);
        this.balance = balance;
        DatabaseService.updateBalance(login, balance);
    }

    // For unit testing (no DB)
    public Customer(String login, int pin, double balance, boolean isTest) {
        super(login, pin);
        this.balance = balance;
        this.isTest = isTest;
    }

    public boolean withdrawCash(double amount) {
        double currentBalance = isTest ? balance : DatabaseService.getBalance(login);
        if (amount > 0 && amount <= currentBalance) {
            if (isTest) {
                balance -= amount;
            } else {
                DatabaseService.updateBalance(login, currentBalance - amount);
            }
            return true;
        }
        return false;
    }

    public boolean depositCash(double amount) {
        if (amount > 0) {
            if (isTest) {
                balance += amount;
            } else {
                double currentBalance = DatabaseService.getBalance(login);
                DatabaseService.updateBalance(login, currentBalance + amount);
            }
            return true;
        }
        return false;
    }

    public double getBalance() {
        return isTest ? balance : DatabaseService.getBalance(login);
    }
}

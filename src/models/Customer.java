package models;

public class Customer extends User {
    private double balance;

    public Customer(String login, int pin, double balance) {
        super(login, pin);
        this.balance = balance;
    }

    public void withdrawCash(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Cash Successfully Withdrawn: " + amount);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    public void depositCash(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Cash Deposited Successfully: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void displayBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

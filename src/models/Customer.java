package models;

import services.DatabaseService;

public class Customer extends User {
    private double balance;
    public Customer(String login, int pin,double balance) {
        super(login, pin);
        this.balance = balance;
    }

    private double getBalance() {
        return balance;
    }



    public void withdrawCash(double amount) {
        double balance = DatabaseService.getBalance(login);
        if (amount > 0 && amount <= balance) {
            DatabaseService.updateBalance(login, balance - amount);
            System.out.println("Cash Successfully Withdrawn: " + amount);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    public void depositCash(double amount) {
        if (amount > 0) {
            double balance = DatabaseService.getBalance(login);
            DatabaseService.updateBalance(login, balance + amount);
            System.out.println("Cash Deposited Successfully: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void displayBalance() {
        double balance = DatabaseService.getBalance(login);
        System.out.println("Current Balance: " + balance);
    }
}

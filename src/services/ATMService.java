package services;

import models.Customer;
import models.Administrator;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;




public class ATMService {
    public void processLogin(String login, int pin) {
        try (Connection conn = DatabaseService.getConnection()) {
            String query = "SELECT login, pin, role, balance FROM users WHERE login = ? AND pin = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setInt(2, pin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                if (role.equals("customer")) {
                    Customer customer = new Customer(login, pin, rs.getDouble("balance"));
                    handleCustomer(customer);
                } else if (role.equals("admin")) {
                    Administrator admin = new Administrator(login, pin);
                    handleAdministrator(admin);
                }
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }


    private void handleCustomer(Customer customer) {

        System.out.println("Select an option:");
        System.out.println("1 - Withdraw Cash");
        System.out.println("2 - Deposit Cash");
        System.out.println("3 - Display Balance");
        System.out.println("4 - Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter amount to withdraw: ");
                customer.withdrawCash(scanner.nextDouble());
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                customer.depositCash(scanner.nextDouble());
                break;
            case 3:
                customer.displayBalance();
                break;
            case 4:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }



    private void handleAdministrator(Administrator admin) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin logged in. Choose an option:");
        System.out.println("1 - View all user accounts");
        System.out.println("2 - Modify an account");
        System.out.println("3 - Delete an account");
        System.out.println("4 - Deposit to an account");
        System.out.println("5 - Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                new DatabaseService().viewAllAccounts(); // Ensure this method exists
                break;
            case 2:
                System.out.print("Enter account login to modify: ");
                String modifyLogin = scanner.nextLine();
                new DatabaseService().modifyAccount(modifyLogin); // Ensure method exists
                break;
            case 3:
                System.out.print("Enter account login to delete: ");
                String deleteLogin = scanner.nextLine();
                new DatabaseService().deleteAccount(deleteLogin); // Ensure method exists
                break;
            case 4:
                System.out.print("Enter account login to deposit to: ");
                String depositLogin = scanner.nextLine();
                System.out.print("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                new DatabaseService().depositToAccount(depositLogin, amount); // Ensure method exists
                break;

            default:
                System.out.println("Invalid choice. Try again.");
                handleAdministrator(admin);
        }
    }

}

package services;

import models.Customer;
import models.Administrator;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        }
    }
}

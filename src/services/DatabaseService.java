package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;




public class DatabaseService {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/atm_db?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "5924636Hh!@";


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Ensure MySQL Driver is loaded
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void viewAllAccounts() {
        try (Connection conn = DatabaseService.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT login, balance FROM users")) {

            System.out.println("User Accounts:");
            while (rs.next()) {
                System.out.println("Login: " + rs.getString("login") + ", Balance: " + rs.getDouble("balance"));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user accounts!", e);
        }
    }


    public void modifyAccount(String login) {
        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE users SET balance = ? WHERE login = ?")) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter new balance: ");
            double newBalance = scanner.nextDouble();
            stmt.setDouble(1, newBalance);
            stmt.setString(2, login);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account updated successfully.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error modifying account!", e);
        }
    }


    public void deleteAccount(String login) {
        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE login = ?")) {

            stmt.setString(1, login);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting account!", e);
        }
    }



    public void depositToAccount(String login, double amount) {
        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE users SET balance = balance + ? WHERE login = ?")) {

            stmt.setDouble(1, amount);
            stmt.setString(2, login);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deposit successful.");
            } else {
                System.out.println("Account not found.");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error processing deposit!", e);
        }
    }

    public static boolean authenticateUser(String login, int pin) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE login = ? AND pin = ?")) {
            stmt.setString(1, login);
            stmt.setInt(2, pin);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a match is found
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error authenticating user.", e);
        }
    }


    public static boolean createUser(String login, int pin, double balance) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (login, pin, balance) VALUES (?, ?, ?)")) {
            stmt.setString(1, login);
            stmt.setInt(2, pin);
            stmt.setDouble(3, balance);
            return stmt.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user.", e);
        }
    }


    public static boolean deleteUser(String login) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE login = ?")) {
            stmt.setString(1, login);
            return stmt.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user.", e);
        }
    }


    public static double getBalance(String login) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM users WHERE login = ?")) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
            return -1; // If user not found
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving balance.", e);
        }
    }


    public static boolean updateBalance(String login, double newBalance) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE users SET balance = ? WHERE login = ?")) {

            stmt.setDouble(1, newBalance);
            stmt.setString(2, login);

            return stmt.executeUpdate() > 0;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);  // Now handling ClassNotFoundException
        } catch (SQLException e) {
            throw new RuntimeException("Error updating balance.", e);
        }
    }





}


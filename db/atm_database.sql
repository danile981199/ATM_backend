CREATE DATABASE atm_db;
USE atm_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    pin INT NOT NULL,
    role ENUM('customer', 'admin') NOT NULL,
    balance DOUBLE DEFAULT 0
);

INSERT INTO users (login, pin, role, balance) VALUES ('Adnan123', 12345, 'customer', 10000);
INSERT INTO users (login, pin, role) VALUES ('Javed123', 12345, 'admin');

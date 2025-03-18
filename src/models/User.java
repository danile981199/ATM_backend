package models;

import services.DatabaseService;

public abstract class User {
    protected String login;

    public User(String login, int pin) {
        this.login = login;
    }

    public boolean authenticate(String login, int pin) {
        return DatabaseService.authenticateUser(login, pin); // Verify credentials from MySQL
    }

    public String getLogin() {
        return login;
    }
}

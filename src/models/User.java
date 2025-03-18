package models;

public abstract class User {
    protected String login;
    protected int pin;

    public User(String login, int pin) {
        this.login = login;
        this.pin = pin;
    }

    public boolean authenticate(String login, int pin) {
        return this.login.equals(login) && this.pin == pin;
    }
}

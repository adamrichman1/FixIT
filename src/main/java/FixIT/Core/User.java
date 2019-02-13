package FixIT.Core;

/**
 * This class represents a User in the FixIT system. Subclasses include Staff and Customer.
 */
public class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

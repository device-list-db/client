package com.holo.login;

/**
 * Account manager
 * @since 0.1.0
 * @version 0.2.0
 */
public class Account {
    private String username;
    private boolean admin;

    /**
     * Create a blank Account object
     */
    public Account() {
        username = "";
        admin = false;
    }

    /**
     * Sets variables once the account is logged in
     * @param username of the account
     * @param isAdmin Admin status of the account
     */
    public void login(String username, boolean isAdmin) {
        this.username = username;
        admin = isAdmin;
    }

    public String getUsername() { return username; }

    public boolean isAdmin() { return admin; }

    public boolean loggedIn() { return !username.equals(""); }
}

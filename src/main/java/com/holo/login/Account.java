package com.holo.login;

/**
 * Account manager
 * @since 0.1.0
 * @version 0.2.0
 */
public class Account {
    private String username;
    private boolean admin;
    private int userId;
    private String name;

    /**
     * Create a blank Account object
     */
    public Account() {
        username = "";
        admin = false;
        userId = -1;
        name = "";
    }

    public void login(String username, boolean isAdmin, int userId, String name) {
        this.username = username;
        admin = isAdmin;
        this.userId = userId;
        this.name = name;
    }

    /**
     * Sets variables once the account is logged in
     * @param username of the account
     * @param isAdmin Admin status of the account
     * @deprecated Does not assign all of the new variables correctly
     */
    public void login(String username, boolean isAdmin) {
        this.username = username;
        admin = isAdmin;
    }

    public String getUsername() { return username; }

    public boolean isAdmin() { return admin; }

    public int getUserId() { return userId; }

    public String getUserIdString() { return userId + ""; }

    public String getName() { return name; }

    public boolean loggedIn() { return !username.equals(""); }
}

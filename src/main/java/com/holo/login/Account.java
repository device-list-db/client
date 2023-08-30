package com.holo.login;

public class Account {
    private String username;
    private boolean admin;

    public Account() {
        username = "";
        admin = false;
    }

    public void login(String username, boolean isAdmin) {
        this.username = username;
        admin = isAdmin;
    }

    public String getUsername() { return username; }

    public boolean isAdmin() { return admin; }

    public boolean loggedIn() { return !username.equals(""); }
}

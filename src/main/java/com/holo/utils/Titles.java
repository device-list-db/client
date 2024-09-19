package com.holo.utils;

public enum Titles {
    PRIMARYSCREEN("HOLO SYSTEM: Main Screen"),
    MAINSCREEN("Devices"),
    ADDDEVICE("Add Device"), 
    DEBT("Debts"),
    ADMIN("Admin");

    private String title;

    private Titles(String title) {
        this.title = title;
    }

    public String getTitle() { return this.title; }
}

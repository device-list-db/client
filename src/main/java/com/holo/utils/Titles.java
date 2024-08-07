package com.holo.utils;

public enum Titles {
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

package com.holo.utils;

public class Person {
    private int id;
    private String name;
    private String username;

    public Person() {
        username = ""; // Username could be blank
    }

    public void registerPerson(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerPerson(int id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public String getUsername() { return this.username; }

    /**
     * Should not be used, used only for displaying the table correctly.
     * @return number of fields in a device
     */
    public static int getFieldCount() { return 3; }
}

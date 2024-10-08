package com.holo.utils;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private Person rentee;
    private LocalDate dueDate;
    private boolean rented;
    private String id;


    public Book() {
        id = "";
        title = "";
        author = "";
        isbn = "";
        rentee = new Person();
        dueDate = LocalDate.of(1, 1, 1);
        rented = false;
    }

    public void registerBook(String title, String author, String isbn, String id, Person rentee, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.rentee = rentee;
        this.dueDate = dueDate;
        rented = true;
    }

    public void registerBook(String title, String author, String isbn, String id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        rented = false;
    }

    public String getTitle() { return this.title; }

    public String getAuthor() { return this.author; }

    public String getIsbn() { return this.isbn; }

    public String getRentee() {
        try {
            if (this.rentee.equals(new Person())) {
                return "";
            } else if (this.rentee.getName().contains("_")) {
                String[] tmp = this.rentee.getName().split("_");
                String tmp2 = tmp[0] + " " + tmp[1];
                return tmp2;
            }
        } catch (NullPointerException e) {
            return "";
        }
        return "NULL";
    }

    public String getDueDate() {
        if (this.dueDate.equals(LocalDate.of(1, 1, 1))) {
            return "";
        } else {
            return this.dueDate.toString();
        }
    }

    public boolean getRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getId() {
        return id;
    }

    /**
     * Should not be used, used only for displaying the table correctly.
     * @return number of fields in a device
     */
    public static int getFieldCount() { return 5; }
}

package com.holo.utils;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private Person rentee;
    private LocalDate dueDate;


    public Book() {
        title = "";
        author = "";
        isbn = "";
        rentee = new Person();
        dueDate = LocalDate.of(1, 1, 1);
    }

    public void registerBook(String title, String author, String isbn, Person rentee, LocalDate dueDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.rentee = rentee;
        this.dueDate = dueDate;
    }

    public void registerBook(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() { return this.title; }

    public String getAuthor() { return this.author; }

    public String getIsbn() { return this.isbn; }

    public String getRentee() {
        if (this.rentee.equals(new Person())) {
            return "";
        } else {
            String[] tmp = this.rentee.getName().split("_");
            String tmp2 = tmp[0] + " " + tmp[1];
            return tmp2;
        }
    }

    public String getDueDate() {
        if (this.dueDate.equals(LocalDate.of(1, 1, 1))) {
            return "";
        } else {
            return this.dueDate.toString();
        }
    }

    /**
     * Should not be used, used only for displaying the table correctly.
     * @return number of fields in a device
     */
    public static int getFieldCount() { return 5; }
}

package com.holo.gui;

import com.holo.utils.Titles;

import javafx.event.Event;
import javafx.fxml.FXML;

public class BookController {
    @FXML
    private void addBook(Event event) {
        event.consume();
        ClientMain.setRoot("AddBook", Titles.ADDBOOK.getTitle());
    }

    @FXML
    private void backPressed(Event event) {
        event.consume();
        ClientMain.setRoot("MainScreen", Titles.PRIMARYSCREEN.getTitle());
    }
}

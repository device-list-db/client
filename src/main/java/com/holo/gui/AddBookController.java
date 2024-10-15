package com.holo.gui;

import java.io.IOException;
import java.util.UUID;

import com.holo.utils.Titles;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddBookController {
    @FXML private TextField authorName;
    @FXML private TextField isbn;
    @FXML private TextField bookName;

    private void submit() {
        String author = authorName.getText().strip();
        author = author.replaceAll(" ", "_");
        try {
            ClientMain.getNetworkManager().send("GET-AUTHOR-ID " + author);
            int authorId = Integer.parseInt(ClientMain.getNetworkManager().recieve());
            if (authorId == -1) {
                ClientMain.getNetworkManager().send("ADD-AUTHOR " + author);
                ClientMain.getNetworkManager().recieve();
                ClientMain.getNetworkManager().send("GET-AUTHOR-ID " + author);
                authorId = Integer.parseInt(ClientMain.getNetworkManager().recieve());
            }
            String bookNameStr = bookName.getText().strip();
            bookNameStr = bookNameStr.replaceAll(" ", "_");
            ClientMain.getNetworkManager().send("REGISTER-BOOK " + UUID.randomUUID().toString() + " " + isbn.getText().strip() + " " + authorId + " " + bookNameStr);
            ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
        } catch (IOException e) {
            ClientMain.showError("Something went wrong");
        }
    }

    @FXML
    private void onSubmit(Event event) {
        event.consume();
        submit();
        ClientMain.setRoot("BookPage", Titles.BOOK.getTitle());
    }

    @FXML
    private void onSubmitMore(Event event) {
        event.consume();
        submit();
        authorName.clear();
        isbn.clear();
        bookName.clear();
    }

    @FXML
    private void onCancel(Event event) {
        event.consume();
        ClientMain.setRoot("BookPage", Titles.BOOK.getTitle());
    }
}

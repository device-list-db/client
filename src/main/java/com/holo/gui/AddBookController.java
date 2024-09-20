package com.holo.gui;

import java.io.IOException;
import java.util.UUID;

import com.holo.utils.Titles;

import javafx.event.Event;
import javafx.fxml.FXML;

public class AddBookController {
    @FXML private String authorName;
    @FXML private String isbn;
    @FXML private String bookName;

    @FXML
    private void onSubmit(Event event) {
        event.consume();
        String author = authorName.strip();
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
            bookName = bookName.strip();
            bookName = bookName.replaceAll(" ", "_");
            ClientMain.getNetworkManager().send("REGISTER-BOOK " + UUID.randomUUID().toString() + " " + isbn.getText().strip() + " " + authorId + " " + bookNameStr);
            ClientMain.getNetworkManager().recieve();
        } catch (IOException e) {
            ClientMain.showError("Something went wrong");
        }
    }

    @FXML
    private void onCancel(Event event) {
        event.consume();
        ClientMain.setRoot("BookPage", Titles.BOOK.getTitle());
    }
}

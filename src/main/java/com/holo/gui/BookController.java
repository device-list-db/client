package com.holo.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.holo.utils.Book;
import com.holo.utils.Person;
import com.holo.utils.Titles;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookController implements Initializable {
    @FXML private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> title;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Book, String> isbn;
    @FXML private TableColumn<Book, String> rentee;
    @FXML private TableColumn<Book, String> dueDate;

    @Override
    public void initialize(URL Location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        rentee.setCellValueFactory(new PropertyValueFactory<Book, String>("rentee"));
        dueDate.setCellValueFactory(new PropertyValueFactory<Book, String>("dueDate"));

        try {
            tableView.getItems().setAll(setValues());
        } catch (IOException e) {
            ClientMain.showError("Server Error 500");
        }
    }

    private List<Book> setValues() throws IOException {
        ClientMain.getNetworkManager().send("GET-BOOKS");
        ArrayList<Book> al = new ArrayList<>();
        int numTimes = Integer.parseInt(ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()));
        for (int i = 0; i < numTimes; i++) {
            Book tmp = new Book();
            String title = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            String author = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            String isbn = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            String test = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            if (test.equals("BOOK-NEXT")) {
                // No one has rented out the book
                tmp.registerBook(title, author, isbn);
            } else if (test.equals("BOOK-MORE")) {
                // Someone has rented out the book
                String pName = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
                String pId = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
                String date = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
                Person tmp2 = new Person();
                tmp2.registerPerson(pId, pName);
                String[] dateTmp = date.split("T");
                LocalDate tmp3 = LocalDate.parse(dateTmp[0], DateTimeFormatter.ISO_DATE);
                tmp.registerBook(title, author, isbn, tmp2, tmp3);
            }
            al.add(tmp);
        }
        ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
        return al;
    }

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

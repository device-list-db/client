package com.holo.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.holo.utils.Person;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController {
    @FXML private TableView<Person> table;
    @FXML private TableColumn<Person, String> id;
    @FXML private TableColumn<Person, String> name;
    @FXML private TableColumn<Person, String> username;

    @FXML
    private void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));

        try {
            table.getItems().setAll(setValues());
        } catch(IOException e) {
            ClientMain.showError("Server Error 500");
        }
    }

    private List<Person> setValues() throws IOException {
        ClientMain.getNetworkManager().send("GET-PEOPLE");
        ArrayList<Person> al = new ArrayList<>();
        int numTimes = Integer.parseInt(ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()));
        for (int i = 0; i < numTimes; i++) {
            Person tmp = new Person();
            String id = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            String username = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            String name = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            if (username.length() > 0)
                tmp.registerPerson(id, name, username);
            else
                tmp.registerPerson(id, name);
            al.add(tmp);
        }
        return al;
    }

    @FXML
    private void backPressed(Event event) {
        event.consume();
        ClientMain.setRoot("MainScreen", "HOLO SYSTEM: Main Screen");
    }
}

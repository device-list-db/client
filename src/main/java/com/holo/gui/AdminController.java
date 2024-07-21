package com.holo.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.holo.utils.Person;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
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
        ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()); // Clear the USER-FINISH message
        return al;
    }

    @FXML
    private void backPressed(Event event) {
        event.consume();
        ClientMain.setRoot("MainScreen", "HOLO SYSTEM: Main Screen");
    }

    @FXML
    private void createAccount(Event event) {
        event.consume();
        Optional<Person> p = Optional.ofNullable(table.getSelectionModel().getSelectedItem());
        if (p.isEmpty()) {
            ClientMain.showError("A person needs to be selected");
            return;
        }
        if (p.get().getId().length() != 0) {
            if (!p.get().getUsername().equals("null")) {
                ClientMain.showError("An account already exists for this person");
                return;
            }
            // TODO: Create a special "login" screen where the only option is register for this option

        }
    }

    @FXML
    private void removePerson(Event event) {
        event.consume();
        // TODO: Add a confirmation to remove the person, and if yes, remove them from the database
    }

    @FXML
    private void addPerson(Event event) {
        event.consume();
        TextInputDialog tx = new TextInputDialog();
        tx.setHeaderText("Please enter the name of the person: ");
        tx.setTitle("Person Registration");
        String name = tx.showAndWait().get();
        try {
            ClientMain.getNetworkManager().send("ADD-PERSON " + name);
            ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

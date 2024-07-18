package com.holo.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.holo.utils.Titles;

import javafx.event.Event;
import javafx.fxml.FXML;

/**
 * @since 0.2.0
 * @version 0.1.0
 */
public class MainScreenController {

    @FXML
    private void initialize(URL location, ResourceBundle resources) {
        ClientMain.setTitle("HOLO SYSTEM: Main Screen");
    }

    @FXML
    private void deviceClicked(Event event) {
        event.consume();
        ClientMain.setRoot("DevicePage", Titles.MAINSCREEN.getTitle());
    }
}

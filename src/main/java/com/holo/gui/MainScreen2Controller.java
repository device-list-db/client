package com.holo.gui;

import javafx.event.Event;
import javafx.fxml.FXML;

public class MainScreen2Controller {

    @FXML
    private void deviceClicked(Event event) {
        event.consume();
        ClientMain.setRoot("MainScreen");
    }
}

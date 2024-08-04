package com.holo.gui;

import javafx.event.Event;
import javafx.fxml.FXML;

public class DebtController {
    @FXML
    private void backPressed(Event event) {
        event.consume();
        ClientMain.setRoot("MainScreen", "HOLO SYSTEM: Main Screen");
    }
}

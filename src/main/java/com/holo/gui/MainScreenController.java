package com.holo.gui;

import com.holo.utils.Titles;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @since 0.2.0
 * @version 0.1.0
 */
public class MainScreenController {

    @FXML Button adminButton;

    @FXML
    private void initialize() {
        if (ClientMain.account.isAdmin()) adminButton.setDisable(false);
    }

    @FXML
    private void deviceClicked(Event event) {
        event.consume();
        ClientMain.setRoot("DevicePage", Titles.MAINSCREEN.getTitle());
    }

    @FXML
    private void debtClicked(Event event) {
        event.consume();
        ClientMain.setRoot("DebtPage", Titles.DEBT.getTitle());
    }

    @FXML
    private void adminClicked(Event event) {
        event.consume();
        ClientMain.setRoot("AdminPage", Titles.ADMIN.getTitle());
    }
}

package com.holo.gui;

import java.io.IOException;

import com.holo.utils.Titles;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

/**
 * Model code for the AddDevice screen
 * @since 0.1.0
 * @version 0.2.0
 */
public class AddDeviceController {
    /**
     * A Popup controller object
     */
    public static Popup thisPopup;
    @FXML private TextField deviceSerial;
    @FXML private TextField deviceMac;
    @FXML private TextField deviceName;
    @FXML private Label errorMessage;
    private boolean editMode;

    /**
     * Initialize an empty model
     */
    public AddDeviceController() {
        editMode = false;
    }

    @FXML
    private void initialize() {
        editMode = false;
    }

    // On press - Attempts to format the user input to be correct to add to the device database
    @FXML
    private void onSubmit(Event event) {
        event.consume();
        String owner = ClientMain.account.getUsername();
        String dSer = deviceSerial.getText().trim();
        String dMac = deviceMac.getText().trim();
        String dNam = "";
        String tmp[] = deviceName.getText().split(" ");
        if (tmp.length > 1) {
            for (int i = 0; i < tmp.length; i++) {
                dNam += tmp[i] + "_";
            }
        } else
            dNam = deviceName.getText().trim();
        
        owner += " ";
        dSer += " ";
        dMac += " ";
        dNam += " ";

        try {
            String command = "";
            String networkComplete = "";
            if (!editMode) {
                command = "REGISTER-DEVICE ";
                networkComplete = "DEVICE-YES";
            } else {
                command = "UPDATE-DEVICE ";
                networkComplete = "DEVICE-UPDATE-YES";
            }
            ClientMain.getNetworkManager().send(command + owner + dSer + dMac + dNam);
            if (ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()).equals(networkComplete)) {
                    ClientMain.setRoot("DevicePage", Titles.MAINSCREEN.getTitle());
            } else {
                errorMessage.setTextFill(Color.FIREBRICK);
                errorMessage.setText("Device enrollment failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Go back to the main screen instead of closing the application out
    @FXML
    private void onCancel(Event event) {
        event.consume();
        ClientMain.setRoot("DevicePage", Titles.MAINSCREEN.getTitle());
    }

    // Go back to the main screen instead of closing the application out
    @FXML
    private void exitApplication(ActionEvent event) {
        event.consume();
        ClientMain.setRoot("DevicePage", Titles.MAINSCREEN.getTitle());
    }
}

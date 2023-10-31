package com.holo.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class AddDeviceController {
    public static String deviceSerialDefault;
    public static String deviceMacDefault;
    public static String deviceNameDefault;
    public static String deviceOwner;
    public static Popup thisPopup;
    @FXML private TextField deviceSerial;
    @FXML private TextField deviceMac;
    @FXML private TextField deviceName;
    @FXML private Label errorMessage;
    private boolean editMode;

    public AddDeviceController() {
        editMode = false;
    }

    @FXML
    private void initialize() {
        try {
        if (deviceSerialDefault.isEmpty() || deviceMacDefault.isEmpty() || deviceNameDefault.isEmpty())
            return;
        } catch (NullPointerException e) {
            return;
        }
        deviceSerial.setText(deviceSerialDefault);
        deviceSerial.setEditable(false);
        deviceMac.setText(deviceMacDefault);
        deviceName.setText(deviceNameDefault);
        deviceSerialDefault = null;
        deviceMacDefault = null;
        deviceNameDefault = null;
        editMode = true;
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

        try {
            if (!editMode) {
                ClientMain.getNetworkManager().send("REGISTER-DEVICE " + owner + " " + dSer + " " + dMac + " " + dNam);
                if (ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()).equals("DEVICE-YES")) {
                    ClientMain.setRoot("MainScreen");
                } else {
                    errorMessage.setTextFill(Color.FIREBRICK);
                   errorMessage.setText("Device enrollment failed");
                }
            } else {
                owner = deviceOwner;
                ClientMain.getNetworkManager().send("UPDATE-DEVICE " + owner + " " + dSer + " " + dMac + " " + dNam);
                if (ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()).equals("DEVICE-UPDATE-YES")) {
                    ClientMain.setRoot("MainScreen");
                } else {
                    errorMessage.setTextFill(Color.FIREBRICK);
                   errorMessage.setText("Device enrollment failed");
                }
            }
            AddDeviceController.thisPopup.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Go back to the main screen instead of closing the application out
    @FXML
    private void onCancel(Event event) {
        event.consume();
        AddDeviceController.thisPopup.hide();
    }

    // Go back to the main screen instead of closing the application out
    @FXML
    private void exitApplication(ActionEvent event) {
        event.consume();
        AddDeviceController.thisPopup.hide();
    }
}

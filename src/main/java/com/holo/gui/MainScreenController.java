package com.holo.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.holo.utils.Device;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;

public class MainScreenController implements Initializable {
    @FXML private TableView<Device> tableView;
    @FXML private TableColumn<Device, String> deviceSerial;
    @FXML private TableColumn<Device, String> macAddress;
    @FXML private TableColumn<Device, String> deviceName;
    @FXML private TableColumn<Device, String> deviceOwner;
    @FXML private CheckBox allDevices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceSerial.setCellValueFactory(new PropertyValueFactory<Device, String>("serialNumber"));
        macAddress.setCellValueFactory(new PropertyValueFactory<Device, String>("macAddress"));
        deviceName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceName"));
        deviceOwner.setCellValueFactory(new PropertyValueFactory<Device, String>("owner"));

        allDevices.setSelected(ClientMain.account.isAdmin());

        try {
            tableView.getItems().setAll(setValues());
        } catch (IOException e) {
            ClientMain.showError("Server Error 500");
        }
    }

    // Create a list that will be used to populate the table for devices
    private List<Device> setValues() throws IOException {
        if (!allDevices.isSelected())
            ClientMain.getNetworkManager().send("GET-DEVICES " + ClientMain.account.getUsername());
        else
            ClientMain.getNetworkManager().send("GET-DEVICES-ALL");
        ArrayList<Device> al = new ArrayList<>();
        int numTimes = Integer.parseInt(ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()));
        for (int i = 0; i < numTimes; i++) {
            Device tmp = new Device();
            String serial = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            char[] mac = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()).toCharArray();
            String name = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            if (name.contains("_")) {
                String[] tmp1 = name.split("_");
                name = "";
                for (int j = 0; j < tmp1.length; j++) {
                    name += tmp1[j] + " ";
                }
                name = name.trim();
            }
            String owner = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            tmp.registerDevice(serial, mac, name, owner);
            al.add(tmp);
        }
        // Removes the DEVICE-FINISH messgae from the queue
        ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
        return al;
    }

    @FXML
    private void handleAllDevices(Event event) {
        event.consume();
        try {
            tableView.getItems().setAll(setValues());
        } catch (IOException e) {
            ClientMain.showError("Server Error 500");
        }
    }

    @FXML
    private void handleAddDevice(Event event) {
        event.consume();
        ClientMain.setRoot("AddDevice");
    }

    @FXML
    private void handleDeletingDevice(Event event) {
        event.consume();
        Device device = tableView.getSelectionModel().getSelectedItem();
        if (ClientMain.account.isAdmin() || device.getOwner().equals(ClientMain.account.getUsername())) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + device + "?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
                try {
                    ClientMain.getNetworkManager().send("DELETE-DEVICE " + device.getOwner() + " " + device.getSerialNumber() + " " + device.getMacAddress() + " " + device.getDeviceName());
                    ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
                    try {
                        tableView.getItems().setAll(setValues());
                    } catch (IOException e) {
                        ClientMain.showError("Server Error 500");
                    }
                } catch (IOException e) {
                    ClientMain.showError("Server error 500");
                }
        } else {
            ClientMain.showError("Credentials not authorized for this operation.");
        }
    }

    @FXML
    private void handleEditDevice(Event event) {
        event.consume();
        Device device = tableView.getSelectionModel().getSelectedItem();
        if (!ClientMain.account.isAdmin() && !device.getOwner().equals(ClientMain.account.getUsername())) {
            ClientMain.showError("Credentials not authorized for this operaion.");
            return;
        }
        Device d = tableView.getSelectionModel().getSelectedItem();
        AddDeviceController.setVariables(d.getSerialNumber(), d.getMacAddress(), d.getDeviceName(), device.getOwner());
        AddDeviceController.thisPopup = ClientMain.showPopup("AddDevice").get();
        AddDeviceController.thisPopup.show(ClientMain.getWindow());
    }
}

package com.holo.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.holo.utils.Debt;
import com.holo.utils.Person;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DebtController implements Initializable {
    @FXML private TableView<Debt> tableView;
    @FXML private TableColumn<Debt, String> debtor;
    @FXML private TableColumn<Debt, String> debtee;
    @FXML private TableColumn<Debt, String> amountOwed;
    @FXML private TableColumn<Debt, String> amountPaid;
    @FXML private TableColumn<Debt, String> memo;

    @Override
    public void initialize(URL Location, ResourceBundle resources) {
        debtor.setCellValueFactory(new PropertyValueFactory<Debt, String>("debtor"));
        debtee.setCellValueFactory(new PropertyValueFactory<Debt, String>("debtee"));
        amountOwed.setCellValueFactory(new PropertyValueFactory<Debt, String>("amountOwed"));
        amountPaid.setCellValueFactory(new PropertyValueFactory<Debt, String>("amountPaid"));
        memo.setCellValueFactory(new PropertyValueFactory<Debt, String>("memo"));

        try {
            tableView.getItems().setAll(setValues());
        } catch (IOException e) {
            ClientMain.showError("Server Error 500");
        }
    }

    private List<Debt> setValues() throws IOException {
        ClientMain.getNetworkManager().send("DEBT-GET");
        ArrayList<Debt> al = new ArrayList<>();
        // Retrieve number of debts
        int numTimes = Integer.parseInt(ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()));
        Person you = new Person();
        you.registerPerson(ClientMain.account.getUserIdString(), ClientMain.account.getName());
        for (int i = 0; i < numTimes; i++) {
            Debt debt = new Debt();
            // Retrieve debtor information
            Person tmp = new Person();
            tmp.registerPerson(ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()), ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()));
            double totalAmount = Double.parseDouble(ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()));
            double amountPaid = Double.parseDouble(ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()));
            String memo = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
            debt.registerDebt(tmp, you, totalAmount, amountPaid, memo);
            al.add(debt);
        }
        ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
        return al;
    }

    @FXML
    private void backPressed(Event event) {
        event.consume();
        ClientMain.setRoot("MainScreen", "HOLO SYSTEM: Main Screen");
    }
}

package com.holo.gui;

import java.io.IOException;

import com.holo.utils.Encryption;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LoginController {
    @FXML private Text actionTarget;
    @FXML private TextField userName;
    @FXML private PasswordField password;

    @FXML
    private void handleLogin(Event event) {
        event.consume();
        String user = userName.getText().trim();
        String pass = password.getText().trim();
        String storedPass = "NULL";
        try {
            ClientMain.getNetworkManager().send("LOGIN " + user);
            storedPass = ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve());
        } catch (IOException e) {
            new Alert(AlertType.WARNING, "Client cannot retrieve messages from server.", ButtonType.OK).showAndWait();
            ClientMain.logger.logError(e);
        }
        if (storedPass.equals("NULL")) {
            setError("Unauthorized access");
            return;
        }

        if (Encryption.verifyPassword(pass, storedPass)) {
            try {
                ClientMain.getNetworkManager().send("ADMIN-RESPONSE " + user);
                ClientMain.account.login(user, ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()).equals("YES"));
                ClientMain.getNetworkManager().send("LOG LOGIN " + user); // Tell the server to log the login
                ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()); // Remove the server response from the message queue
                ClientMain.setRoot("MainScreen");
            } catch (IOException e) {
                ClientMain.logger.logError(e);
                setError("Unauthorized access");
            }
        } else {
            setError("Unauthorized access");
        }
    }

    @FXML
    private void handleRegister(Event event) {
        event.consume();
        TextInputDialog tx = new TextInputDialog();
        tx.setHeaderText("Please enter your name: ");
        tx.setTitle("Registration");
        String user = userName.getText().trim();
        String pass = password.getText().trim();
        if (user.contains(" ")) {
            setError("Username cannot contain a space");
            return;
        }
        if (user.length() < 1) {
            setError("You must enter a username.");
            return;
        }
        if (user.equals("NULL")) {
            setError("Username is a reserved word.");
            return;
        }
        if (pass.length() < 1) {
            setError("You must enter a password.");
            return;
        }
        if (pass.equals(user)) {
            setError("Password cannot be the same as the username");
            //return;
            // TODO: Uncomment above after all testing is done
        }
        String name = tx.showAndWait().get();
        if (name.length() < 1) {
            setError("You must enter a name");
            return;
        }

        try {
            ClientMain.getNetworkManager().send("REGISTER " + user + " " + Encryption.encryptPassword(pass) + " " + name);
            if (ClientMain.getNetworkManager().parseServerMessage(ClientMain.getNetworkManager().recieve()).equals("REGISTRATION-PASS"))
                setSuccess("Registration successful");
            else
                setError("Registration failed");
        } catch (IOException e) {
            ClientMain.logger.logError(e);
            setError("Registration failed");
        }
    }

    private void setError(String error) {
        actionTarget.setFill(Color.FIREBRICK);
        actionTarget.setText(error);
    }

    private void setSuccess(String message) {
        actionTarget.setFill(Color.GREEN);
        actionTarget.setText(message);
    }
}

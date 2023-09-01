package com.holo.gui;

import java.io.IOException;
import java.net.Socket;

import com.holo.login.Account;
import com.holo.network.NetworkManager;
import com.holo.utils.Logger;
import com.holo.utils.VerifyFXML;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ClientMain extends Application {

    private static Scene scene;
    static Stage stage;
    private static NetworkManager nm;
    private static Dotenv dotenv;
    public static Account account = new Account();
    public static final Logger logger = new Logger();

    @Override
    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("LoginPage")); // Loads loginPage.fxml
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Load CSS values
        ClientMain.stage = primaryStage;
        stage.setTitle("Device DB Login Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void showError(String error) {
        new Alert(AlertType.ERROR, error, ButtonType.OK).showAndWait();
    }

    public static void setRoot(String fxml) {
        try {
            if (!VerifyFXML.validFXMLFile(fxml)) throw new IOException("Unable to read the file");
            scene.setRoot(loadFXML(fxml));
            stage.sizeToScene();
            stage.centerOnScreen();
        } catch (IOException e) {
            showError("FXML Security failure");
            ClientMain.logger.logError(e);
        }
    }

    // TODO: Figure out how to do popups
    /*public static Optional<Popup> getPopup(String fxml) {
        Popup popup = new Popup();
        try {
            if (!VerifyFXML.validFXMLFile(fxml)) throw new IOException("Unable to read the file");
            popup.getContent().add(loadFXML(fxml));
        } catch(IOException e) {}
        return Optional.ofNullable(popup);
    }*/
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoad = new FXMLLoader(ClientMain.class.getResource(fxml + ".fxml"));
        return fxmlLoad.load();
    }

    public static void main(String[] args) {
        VerifyFXML.setupWhitelist();
        dotenv = Dotenv.configure().load();
        try {
            setConnector(new NetworkManager(new Socket(dotenv.get("SERVADR"), Integer.parseInt(dotenv.get("SERVPORT")))));
            launch(args);
        } catch (IOException e) {
            ClientMain.logger.logError(e);
        }
    }

    private static void setConnector(NetworkManager netMan) {
        nm = netMan;
    }

    public static NetworkManager getNetworkManager() {
        return nm;
    }
}

package com.holo.gui;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

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
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Application start point after {@link com.holo.SuperMain SuperMain}
 * @since 0.1.0
 * @version 0.2.0
 */
public class ClientMain extends Application {

    private static Scene scene;
    static Stage stage;
    private static NetworkManager nm;
    private static Dotenv dotenv;
    /**
     * Global {@link com.holo.login.Account Account} object
     */
    public static Account account = new Account();
    /**
     * Global {@link com.holo.utils.Logger Logger} object
     */
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

    /**
     * Set the title for the window
     * @param title The new title
     */
    public static void setTitle(String title) {
        stage.setTitle(title);
    }

    /**
     * The head error display function, if an error needs to be shown to the user.
     * @param error The message of the error
     */
    public static void showError(String error) {
        new Alert(AlertType.ERROR, error, ButtonType.OK).showAndWait();
    }

    public static void showInfo(String info) {
        new Alert(AlertType.INFORMATION, info, ButtonType.OK).showAndWait();
    }

    /**
     * Changes the root of the stage to display a page
     * @param fxml File name
     * @deprecated Does not update the title of the screen
     */
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

    /**
     * Changes the root of the stage to display a page
     * @param fxml File name
     */
    public static void setRoot(String fxml, String title) {
        setTitle(title);
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

    /**
     * Create a popup object to display
     * @param fxml File name of the popup
     * @return An Optional object possibly containing a Popup object
     */
    public static Optional<Popup> showPopup(String fxml) {
        Popup popup = new Popup();
        try {
            if (!VerifyFXML.validFXMLFile(fxml)) throw new IOException("Unable to read the file");
            popup.getContent().add(loadFXML(fxml));
        } catch(IOException e) {}
        return Optional.ofNullable(popup);
    }

    /**
     * Get the scene window
     * @return The primary Window object
     */
    public static Window getWindow() {
        return scene.getWindow();
    }
    
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

    /**
     * Obtain the global network manager
     * @return NetworkManager object
     */
    public static NetworkManager getNetworkManager() {
        return nm;
    }
}

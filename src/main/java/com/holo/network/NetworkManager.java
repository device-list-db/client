package com.holo.network;

import com.holo.Talker;
import com.holo.gui.ClientMain;

import java.net.Socket;
import java.io.IOException;

/**
 * Class to bridge low level network communication with high level methods
 */
public class NetworkManager {
    private Talker talker;

    /**
     * Create the bridge class
     * @param socket The socket to use
     * @throws IOException If the socket was unexpectently closed
     */
    public NetworkManager(Socket socket) throws IOException {
        talker = new Talker(socket);
    }

    /**
     * Get the message that was sent from the server
     * @return The message recieved
     * @throws IOException If the socket was unexpectently closed
     */
    public String recieve() throws IOException {
        return talker.recieve();
    }

    /**
     * Sends a message through the bridge
     * @param message The message to send
     * @throws IOException If the socket was unexpecently closed
     */
    public void send(String message) throws IOException {
        talker.send(message);
    }

    public String parseServerMessage(String message) {
        String[] tmp = message.split(" ");
        switch(tmp[0]) {
            case "ADMIN-TOTAL": // Add to this when only the first argument is the required response
            case "LOGIN-PASS":
            case "ADMIN-PASS":
            case "DEVICE-SERIAL":
            case "DEVICE-MAC":
            case "DEVICE-OWNER":
            case "DEVICE-NAME":
            case "DEVICE-TOTAL":
            case "ADMIN-RESPONSE":
                return tmp[1];
            case "ERROR":
                ClientMain.showError(tmp[1]); // Show the server given error, then return ERROR
            case "REGISTRATION-PASS": // Add to this when the server response is the full required response
            case "REGISTRATION-FAIL":
            case "D-REGISTRATION-PASS":
            case "D-REGISTRATION-FAIL":
            case "D-UPDATE-SUCCESS":
            case "D-UPDATE-FAIL":
            case "DEVICE-FINISH":
            case "YES":
            case "NO":
            case "DEVICE-YES":
            case "DEVICE-NO":
            case "LOG-YES":
            case "DEVICE-UPDATE-YES":
                return tmp[0];
            case "KILL": // Special: Server kills the client
                System.exit(-1);
        }
        return "NULL";
    }
}

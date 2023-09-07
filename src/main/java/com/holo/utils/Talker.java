package com.holo.utils;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;

/**
 * Allows the communicatuion between client and server
 *
 */
public class Talker {
    private DataOutputStream writeStream;
    private BufferedReader readStream;

    /**
     * Creates the talker objecct for use
     * @param client The socket of the other side
     * @throws IOException If either stream is unable to be made
     */
    public Talker(Socket client) throws IOException {
        writeStream = new DataOutputStream(client.getOutputStream());
        readStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    /**
     * Sends a message to the other side
     * @param arg The string to parse to the other sie
     * @throws IOException If the socket was closed on either side
     */
    public void send(String arg) throws IOException {
        System.out.println("SEND: " + arg); // TODO: Remove after debug
        writeStream.writeUTF(arg+"\n");
        writeStream.flush();
    }

    /**
     * Recieves a message from the other side
     * @return The recieved message
     * @throws IOException If the socket was closed on either side
     */
    public String recieve() throws IOException {
        clear();
        clear();
        String tmp = readStream.readLine();
        System.out.println("RECIEVE: " + tmp);
        return new String(tmp); // TODO: Set back to readStream.readAllBytes after debug
    }

    private void clear() throws IOException {
        readStream.read();
    }
}

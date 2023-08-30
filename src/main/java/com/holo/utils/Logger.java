package com.holo.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    public Logger() {
    }

    public void logError(Exception e) {
        try {
            FileWriter New_File = new FileWriter("Error-log.txt", true);
            BufferedWriter Buff_File = new BufferedWriter(New_File);
            PrintWriter Print_File = new PrintWriter(Buff_File, true);
            e.printStackTrace(Print_File);
        }
        catch (IOException ie) {
            throw new RuntimeException("Cannot write the Exception to file", ie);
        }
    }
}
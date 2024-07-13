package com.holo.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logger class for issues/debug messages
 * @since 0.1.0
 * @version 0.1.0
 */
public class Logger {
    public Logger() {}

    /**
     * Log an error to the error log file
     * @param e Exception to log
     */
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
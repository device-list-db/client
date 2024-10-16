package com.holo.utils;

import java.util.ArrayList;

/**
 * Security class to verify a FXML file is one that is expected
 * @since 0.1.0
 * @version 0.1.0
 */
public class VerifyFXML {
    private static ArrayList<String> validFiles = new ArrayList<String>();
    
    /**
     * Run only once. Sets up the whitelist, and keeps it in the memory
     */
    public static void setupWhitelist() {
        validFiles.add("AddDevice");
        validFiles.add("LoginPage");
        validFiles.add("DevicePage");
        validFiles.add("MainScreen");
        validFiles.add("DebtPage");
        validFiles.add("AdminPage");
        validFiles.add("BookPage");
        validFiles.add("AddBook");
    }

    /**
     * Verifies the whitelist has not been tampered with, and checks if the supplied file is in the whitelist
     * @param fxml name of the file
     * @return if the name of the file is allowed to run
     */
    public static boolean validFXMLFile(String fxml) {
        if (validFiles.size() != 8) return false;
        return validFiles.contains(fxml);
    }
}

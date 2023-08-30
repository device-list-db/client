package com.holo.utils;

import java.util.ArrayList;

public class VerifyFXML {
    private static ArrayList<String> validFiles = new ArrayList<String>();
    public static void setupWhitelist() {
        validFiles.add("AddDevice");
        validFiles.add("LoginPage");
        validFiles.add("MainScreen");
    }

    public static boolean validFXMLFile(String fxml) {
        if (validFiles.size() != 3) return false;
        return validFiles.contains(fxml);
    }
}

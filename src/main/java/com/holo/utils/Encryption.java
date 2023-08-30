package com.holo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {
    private static BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    public static String encryptPassword(String password) {
        pe = new BCryptPasswordEncoder();
        return pe.encode(password);
    }

    public static boolean verifyPassword(CharSequence rawPassword, String encodedPassword) {
        pe = new BCryptPasswordEncoder();
		return pe.matches(rawPassword, encodedPassword);
    }
}

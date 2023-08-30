package com.holo;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.holo.utils.Encryption;

public class EncryptionTest {
    @Test
    public void EncryptAndVerifyHelloWorld() {
        String encPass = Encryption.encryptPassword("HelloWorld");
        assertTrue(Encryption.verifyPassword("HelloWorld", encPass));
    }

    @Test
    public void EncryptAndFailHelloWorld() {
        String encPass = Encryption.encryptPassword("HelloWorld");
        assertFalse(Encryption.verifyPassword("Hello World", encPass));
    }

    @Test
    public void EncryptAndFailHelloWorldTest2() {
        String encPass = Encryption.encryptPassword("HelloWorld");
        assertFalse(Encryption.verifyPassword("Helloworld", encPass));
    }
}

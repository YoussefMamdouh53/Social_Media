package com.debi.social_media.utils;

import javafx.scene.control.Alert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    // Constants for hashing
    private static final int SALT_LENGTH = 16; // Salt length in bytes
    private static final String ALGORITHM = "SHA-256"; // Hashing algorithm


    public static String hashPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);

            // Combine password and salt
            String passwordWithSalt = password + encodedSalt;

            // Create SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = digest.digest(passwordWithSalt.getBytes());

            // Encode the hash to Base64
            String hashedPassword = Base64.getEncoder().encodeToString(hash);

            // Concatenate salt and hashed password
            return encodedSalt + hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }


    public static String[] splitSaltAndHash(String concatenatedString) {
        // The salt is always a fixed length after Base64 encoding
        int saltLength = Base64.getEncoder().encodeToString(new byte[SALT_LENGTH]).length();
        String encodedSalt = concatenatedString.substring(0, saltLength);
        String hashedPassword = concatenatedString.substring(saltLength);
        return new String[]{encodedSalt, hashedPassword};
    }


    public static boolean verifyPassword(String password, String concatenatedSaltHash) {
        String[] parts = splitSaltAndHash(concatenatedSaltHash);
        String encodedSalt = parts[0];
        String hashedPassword = parts[1];

        // Recompute the hash with the provided password and extracted salt
        String passwordWithSalt = password + encodedSalt;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = digest.digest(passwordWithSalt.getBytes());
            String newHashedPassword = Base64.getEncoder().encodeToString(hash);

            return newHashedPassword.equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }
}
package javacode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Key {
    private Logger logger = Logger.getLogger(Key.class.getName());
    private Scanner scanner = new Scanner(System.in);

    // Constructor to configure logger to write to the same log file as the SendKey class
    public Key() {
        try {
            // Ensure that the logs directory exists
            Path logDir = Paths.get("logs");
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);  // Create 'logs' directory if it doesn't exist
            }

            // Create or reuse the FileHandler that writes logs to the specified file
            FileHandler fileHandler = new FileHandler("logs/sendkey.log", true); // true to append to the file

            // Set a simple formatter to format the log messages
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Add the FileHandler to the logger
            logger.addHandler(fileHandler);

            // Set the logging level
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize logger handler.", e);
        }
    }

    // Method to generate a random key
    public String generateRandomKey() {
        StringBuilder randomKey = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            randomKey.append(Long.toString(Math.abs(random.nextLong())));
        }
        logger.log(Level.INFO, "Generated random key: " + randomKey.toString());
        return randomKey.toString();
    }

    // Method to capture the user's input
    public String getUserKey() {
        logger.log(Level.INFO, "Enter the User Passphrase (A line):");
        String userKey = scanner.nextLine();
        logger.log(Level.INFO, "User entered passphrase: " + userKey);
        return userKey;
    }
}

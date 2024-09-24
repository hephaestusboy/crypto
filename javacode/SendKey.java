package javacode;

import java.io.IOException;
import java.util.logging.*;

public class SendKey {
    private String randomKey;
    private Logger logger = Logger.getLogger(SendKey.class.getName());

    // Constructor to configure logger to write to a file
    public SendKey() {
        try {
            // Create a FileHandler that writes log to a specified file
            FileHandler fileHandler = new FileHandler("logs/sendkey.log", true); // 'true' to append to the file

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

    // Method that logs the random key from the Algorithm class
    public void sendIntegerKey(Algorithm algorithm) {
        randomKey = algorithm.getRandomKeyGenerated();

        // Log the random key
        logger.log(Level.INFO, "Generated random key: " + randomKey);

        // Optional: Log additional information or perform actions with the key
    }
}

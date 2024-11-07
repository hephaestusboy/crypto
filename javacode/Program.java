package javacode;

import java.util.Scanner;

public class Program {
    static String randomkey;
    static String finalkeyString;
    static String encrypted;
    static String decrypted;
    static String inputString;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        // Get the operation type from the user
        System.out.println("Select operation: 1 for Encryption, 2 for Decryption");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            // Perform encryption
            System.out.println("Enter the string to encrypt:");
            inputString = scanner.nextLine();
            encrypt(inputString);
        } else if (choice == 2) {
            // Perform decryption
            System.out.println("Enter the string to decrypt:");
            inputString = scanner.nextLine();
            decrypt(inputString);
        } else {
            System.out.println("Invalid choice. Please select 1 or 2.");
        }

        scanner.close();
    }

    private static void encrypt(String input) throws Exception {
        Algorithmsofkey algorithm = new Algorithmsofkey();
        finalkeyString = algorithm.runAlgorithm();
        randomkey = algorithm.getRandomKeyGenerated();
        encryptor encrypt = new encryptor();
        PaddingScript padder = new PaddingScript();

        boolean isPadded = (input.length() % 2 != 0);
        // Encrypt the input string
        encrypted = encrypt.encrypt(input, finalkeyString);

        // Add padding if necessary
        if (isPadded) {
            encrypted = padder.addPadding(encrypted);
            encrypted += "P"; // Append 'P' to indicate padding
        } else {
            encrypted += "N"; // Append 'N' to indicate no padding
        }

        System.out.println("Final Encrypted Message is: " + encrypted);
    }

    private static void decrypt(String input) throws Exception {
        KeyDecryption keydecryption = new KeyDecryption();
        finalkeyString = keydecryption.convertKey();
        decryptor decrypt = new decryptor();
        PaddingScript padder = new PaddingScript();

        // Check the last character to determine padding
        char paddingIndicator = input.charAt(input.length() - 1);
        if (paddingIndicator == 'P') {
            // Remove padding indicator for decryption
            decrypted = decrypt.decrypt(input.substring(0, input.length() - 1), finalkeyString);
            decrypted = padder.removePadding(decrypted);
        } else if (paddingIndicator == 'N') {
            decrypted = decrypt.decrypt(input.substring(0, input.length() - 1), finalkeyString);
        } else {
            System.out.println("Invalid input format.");
            return;
        }
        System.out.println("Decrypted String is : "+ decrypted);
    }
}

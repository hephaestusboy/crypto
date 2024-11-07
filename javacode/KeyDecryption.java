package javacode;

import java.util.Scanner;

public class KeyDecryption {
    private String key;

    public String convertKey() {
        char ch;
        String randomKey;

        // Read user input for the random key and user key
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the random key: ");
        randomKey = scanner.nextLine();
        System.out.print("Enter the user passphrase: ");
        String userKey = scanner.nextLine();
        
        StringBuilder convertedKey = new StringBuilder();

        // Convert each character of the user key to its ASCII % 100 representation
        for (int i = 0; i < userKey.length(); i++) {
            ch = userKey.charAt(i);
            convertedKey.append((ch % 100));
        }

        // Create two key strings for interleaving
        String keyString1 = convertedKey.toString();
        String keyString2 = randomKey;
        StringBuilder finalKey = new StringBuilder();

        // Interleave the two key strings
        int j = 0, k = 0;
        for (int i = 0; i < (keyString1.length() + keyString2.length()); i++) {
            if (j < keyString1.length() && (i % 2 == 0 || k >= keyString2.length())) {
                finalKey.append(keyString1.charAt(j));
                j++;
            } else if (k < keyString2.length()) {
                finalKey.append(keyString2.charAt(k));
                k++;
            }
        }

        // Assign the final interleaved key
        key = finalKey.toString();
        // Uncomment the following line for debugging purposes
        // System.out.println("Final Key for Decryption is: " + key);

        return key; // Return the final key for decryption
    }

    // Getter for key if you want to use it elsewhere
    public String getKey() {
        return key;
    }
}
package javacode;

public class PaddingScript {
    
    // Method to add padding
    public String addPadding(String input) {
        int blockSize = 16; // AES block size is 16 bytes
        int paddingNeeded = blockSize - (input.length() % blockSize);
        
        // Create padding character
        char paddingChar = (char) paddingNeeded; // Use the number of padding bytes as the padding character
        
        StringBuilder paddedInput = new StringBuilder(input);
        
        // Append the padding
        for (int i = 0; i < paddingNeeded; i++) {
            paddedInput.append(paddingChar);
        }
        
        return paddedInput.toString();
    }

    // Method to remove padding
    // Method to remove padding
public String removePadding(String input) {
    int length = input.length();
    
    // Check if the input is empty or too short to have padding
    if (length == 0) {
        return input; // Return as is if empty
    }

    char paddingChar = input.charAt(length - 1); // Get the padding character
    // int paddingValue = (int) paddingChar; // Get the padding value as an integer

    // // Ensure the padding value does not exceed the length of the input
    // if (paddingValue < 1 || paddingValue > length) {
    //     throw new IllegalArgumentException("Invalid padding value: " + paddingValue);
    // }

    // Remove padding
    return input.substring(0, length);
}
}
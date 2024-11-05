package javacode;

import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.zip.DataFormatException;

public class decryptor {

    public String decrypt(String encryptedText, String customKey) throws Exception {
        // Ensure the key length is at least 600 bits (75 characters)
        if (customKey.length() < 75) {
            throw new IllegalArgumentException("Key must be more than 600 bits (75 characters).");
        }

        boolean isPadded = !(hasSpecialCharacters(encryptedText));

        // Derive a 256-bit key from the custom key
        String derivedKey = CryptoUtils.deriveKey(customKey);

        // Decrypt the encrypted string
        String decrypted = decryptAes(encryptedText, derivedKey);

        // Decompress the decrypted string
        String decompressed = CryptoUtils.decompress(Base64.getDecoder().decode(decrypted));

        // Remove padding character if it was added
        if (isPadded && decompressed.endsWith("X")) {
            decompressed = decompressed.substring(0, decompressed.length() - 1);
        }

        //System.out.println("Decrypted string: " + decompressed);
        return decompressed;
    }

    private static String decryptAes(String cipherText, String key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(key); // Decode Base64 key to byte array
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(CryptoUtils.hexToByteArray(cipherText));
        return new String(decryptedBytes);
    }

    public static boolean hasSpecialCharacters(String str) {
        // Regular expression to match any special character
        String specialCharacters = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(specialCharacters);
        return pattern.matcher(str).find();
    }
}

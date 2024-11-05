package javacode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.Deflater;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class encryptor {
    private String encrypted;

    public String encrypt(String inputString, String customKey) throws Exception {
        // Pad the string if its length is odd
        boolean isPadded = false;
        if (inputString.length() % 2 != 0) {
            inputString += 'X';
            isPadded = true;
        }

        // Ensure the key length is at least 600 bits (75 characters)
        if (customKey.length() < 75) {
            throw new IllegalArgumentException("Key must be more than 600 bits (75 characters).");
        }

        // Derive a 256-bit key from the custom key
        String derivedKey = CryptoUtils.deriveKey(customKey);

        // Compress the input
        String compressedInput = Base64.getEncoder().encodeToString(CryptoUtils.compress(inputString));

        // Encrypt the compressed input
        encrypted = encryptAes(compressedInput, derivedKey);
        //System.out.println("Encrypted string (Hex): " + encrypted);

        return encrypted;
    }

    public String getEncrypted() {
        return encrypted;
    }

    private static String encryptAes(String plainText, String key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(key); // Decode Base64 key to byte array
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return CryptoUtils.byteArrayToHex(encryptedBytes);
    }
}

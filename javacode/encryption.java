package javacode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class encryption {

    static String encrypted;

    public String encryptionfunction(String finalkeyString)throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter string to be encrypted: ");
        String inputString = scanner.nextLine();

        // Track if padding was added
        boolean isPadded = false;

        // Pad the string if its length is odd
        if (inputString.length() % 2 != 0) {
            inputString += 'X';
            isPadded = true;
        }

        // Ask for custom key input
        // System.out.print("Enter a custom key (more than 600 bits): ");
        String customKey = finalkeyString;

        // Ensure the key length is at least 600 bits (75 characters)
        if (customKey.length() < 75) {
            throw new IllegalArgumentException("Key must be more than 600 bits (75 characters).");
        }

        // Derive a 256-bit key from the custom key using SHA-256 hash
        String derivedKey = deriveKey(customKey);

        // Compress the input
        String compressedInput = Base64.getEncoder().encodeToString(compress(inputString));
        
        // Encrypt the compressed input
        encrypted = encryptAes(compressedInput, derivedKey);
        System.out.println("Encrypted string (Hex): " + encrypted);

        // Decrypt the encrypted string
        String decrypted = decryptAes(encrypted, derivedKey);

        // Decompress the decrypted string
        String decompressed = decompress(Base64.getDecoder().decode(decrypted));

        // Remove padding character if it was added
        if (isPadded && decompressed.endsWith("X")) {
            decompressed = decompressed.substring(0, decompressed.length() - 1);
        }
        
        System.out.println("Decrypted string: " + decompressed);
        return decompressed;
    }

    public String getencrypted()
    {
            return encrypted;
    }

    // Derives a 256-bit key from a custom key using SHA-256
    private static String deriveKey(String key) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(key.getBytes());
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    private static String encryptAes(String plainText, String key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(key); // Decode Base64 key to byte array
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedone = cipher.doFinal(plainText.getBytes());
        return byteArrayToHex(encryptedone);
    }

    private static String decryptAes(String cipherText, String key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(key); // Decode Base64 key to byte array
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(hexToByteArray(cipherText));
        return new String(decrypted);
    }

    private static byte[] compress(String input) throws IOException {
        byte[] inputBytes = input.getBytes();
        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);
        compressor.setInput(inputBytes);
        compressor.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(inputBytes.length);
        byte[] buffer = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buffer);
            bos.write(buffer, 0, count);
        }
        bos.close();
        return bos.toByteArray();
    }

    private static String decompress(byte[] inputBytes) throws IOException, DataFormatException {
        Inflater decompressor = new Inflater();
        decompressor.setInput(inputBytes);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(inputBytes.length);
        byte[] buffer = new byte[1024];
        while (!decompressor.finished()) {
            int count = decompressor.inflate(buffer);
            bos.write(buffer, 0, count);
        }
        bos.close();
        return bos.toString();
    }

    private static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static byte[] hexToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
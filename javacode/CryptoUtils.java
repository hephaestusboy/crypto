package javacode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.DataFormatException;
import java.util.Base64;

public class CryptoUtils {

    // Derives a 256-bit key from a custom key using SHA-256
    public static String deriveKey(String key) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(key.getBytes());
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public static byte[] compress(String input) throws IOException {
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

    public static String decompress(byte[] inputBytes) throws IOException, DataFormatException {
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

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] hexToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}

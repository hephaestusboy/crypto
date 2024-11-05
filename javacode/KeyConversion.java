package javacode;

public class KeyConversion {
    private String key;

    public String convertKey() {
        char ch;
        String randomKey;

        Key rin = new Key(); // Assuming there's a Key class that provides randomkey() and userkey()
        StringBuilder convertedKey = new StringBuilder();

        // Generate a random key and user key
        randomKey = rin.generateRandomKey();
        String userKey = rin.getUserKey();

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
        //System.out.println("Randomly Generated Final key = " + key);

        // Optional: Uncomment the following line for debugging purposes
        // System.out.println("Converted key is: " + convertedKey + "\nKeyString1 is: " + keyString1 + "\nKeyString2 is: " + keyString2 + "\nFinal Key is: " + key);

        return randomKey;
    }

    // Getter for key if you want to use it elsewhere
    public String getKey() {
        return key;
    }
}

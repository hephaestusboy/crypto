package javacode;

public class Algorithm {
    private String randomKeyGenerated;

    public long runAlgorithm() {
        int result = 0;
        KeyConversion keyConversion = new KeyConversion();
        randomKeyGenerated = keyConversion.convertKey();
        // Optionally print the generated key if needed for debugging
        // System.out.println("Randomly generated key = " + randomKeyGenerated);
        return result;
    }

    // Getter for randomKeyGenerated (if needed in other classes)
    public String getRandomKeyGenerated() {
        return randomKeyGenerated;
    }
}

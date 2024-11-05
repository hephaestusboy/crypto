package javacode;

public class Algorithmsofkey {
    private String randomKeyGenerated;

    public String runAlgorithm() {
        KeyConversion keyConversion = new KeyConversion();
        randomKeyGenerated = keyConversion.convertKey();
        String finalkeyString = keyConversion.getKey();
        // Optionally print the generated key if needed for debugging
        //System.out.println("Randomly generated key = " + randomKeyGenerated);
        return finalkeyString;
    }

    // Getter for randomKeyGenerated (if needed in other classes)
    public String getRandomKeyGenerated() {
        return randomKeyGenerated;
    }
}

package javacode;

public class Program {
    static String randomkey;
    static String finalkeyString;
    static String encrypted;
    static String decrypted;
    public static void main(String[] args) throws Exception{
        Algorithmsofkey algorithm = new Algorithmsofkey();
        finalkeyString = algorithm.runAlgorithm();
        randomkey = algorithm.getRandomKeyGenerated();
        encryption encrypt = new encryption();
        decrypted = encrypt.encryptionfunction(finalkeyString);
        encrypted = encrypt.getencrypted();
        SendKey sendKey = new SendKey();
        sendKey.sendIntegerKey(algorithm);
        System.out.println("Final Encrypted Message is :" + encrypted);
        System.out.println("Final Random Key Generated is:" + randomkey);
        System.out.println("Final decrypted Message is : " + decrypted);
    }
}

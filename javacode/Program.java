package javacode;

public class Program {
    static String randomkey;
    static String finalkeyString;
    static String encrypted;
    static String decrypted;
    static String inputString;
    public static void main(String[] args) throws Exception{
        Algorithmsofkey algorithm = new Algorithmsofkey();
        finalkeyString = algorithm.runAlgorithm();
        randomkey = algorithm.getRandomKeyGenerated();
        encryptor encrypt = new encryptor();
        decryptor decrypt = new decryptor();
        paddingscript padder = new paddingscript();

        inputString = "Hello World";
        encrypted = encrypt.encrypt(inputString, finalkeyString);
        boolean isPadded = (inputString.length() % 2 != 0);
        if(isPadded)
        {
            encrypted = padder.addpadding(encrypted);
        }
        decrypted = decrypt.decrypt(encrypted, finalkeyString);


        SendKey sendKey = new SendKey();
        sendKey.sendIntegerKey(algorithm);
        System.out.println("Final Encrypted Message is :" + encrypted);
        //System.out.println("Final Random Key Generated is:" + randomkey);
        System.out.println("Final decrypted Message is : " + decrypted);
    }
}

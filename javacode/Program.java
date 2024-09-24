package javacode;

public class Program {
    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        algorithm.runAlgorithm();

        SendKey sendKey = new SendKey();
        sendKey.sendIntegerKey(algorithm);
    }
}

package javacode;

public class keyconvertion {
    String randomkey;
    String key;
    public void keyconvert()
    {
        char ch;
        key rin = new key();
        StringBuilder stkeyconverted = new StringBuilder();
        randomkey = rin.randomkey();
        String stkey = rin.userkey();
        for(int i=0;i<stkey.length();i++)
        {
            ch = stkey.charAt(i);
            stkeyconverted.append((ch%100));
        }
        String keyString1 = stkeyconverted.toString();
        String keyString2 = randomkey;
        StringBuilder keyString = new StringBuilder();
        int j=0,k=0;
        for (int i = 0; i < (keyString1.length() + keyString2.length()); i++) 
        {
            if (j < keyString1.length() && (i % 2 == 0 || k >= keyString2.length())) 
            {
                keyString.append(keyString1.charAt(j));
                j++;
            } 
            else if (k < keyString2.length()) 
            {
                keyString.append(keyString2.charAt(k));
                k++;
            }
         
        }
        key = keyString.toString();
        System.out.println("Converted key is "+stkeyconverted+"\n KeyString1 is "+keyString1+"\n Keystring2 is "+keyString2+"\n Key is "+key);
    }
}
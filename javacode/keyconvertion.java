package javacode;

public class keyconvertion {
    public void keyconvert()
    {
        char ch;
        key rin = new key();
        long stkeyconverted=0;
        int randomkey = rin.randomkey();
        String stkey = rin.userkey();
        for(int i=0;i<stkey.length();i++)
        {
            ch = stkey.charAt(i);
            stkeyconverted += (ch%100);
            if((i+1)!=stkey.length())
            {
                stkeyconverted *= 100;
            }
        }
        String keyString1 = Long.toString(stkeyconverted);
        String keyString2 = Integer.toString(randomkey);
        StringBuilder keyString = new StringBuilder();
        int j=0,k=0;
        for(int i=0;i<(keyString1.length()+keyString2.length());i++)
        {
            if(j<keyString1.length() && k<keyString2.length())
            {
                if(i%2==0 && j<keyString1.length())
                {
                    keyString.append(keyString1.charAt(j));
                    j++;
                }
                if(i%2==1 && k<keyString2.length())
                {
                    keyString.append(keyString2.charAt(k));
                    k++;
                }
            }
            if(j==keyString1.length())
            {
                keyString.append(keyString2.charAt(k));
                k++;
            }
            if(k==keyString2.length())
            {
                keyString.append(keyString1.charAt(j));
                j++;
            }
        }
        String key = keyString.toString();
        System.out.println("Converted key is "+stkeyconverted+"\n KeyString1 is "+keyString1+"\n Keystring2 is "+keyString2+"\n Key is "+key);
        // return new Pair<long,Integer>(stkeyconverted,keybackup);
    }
}
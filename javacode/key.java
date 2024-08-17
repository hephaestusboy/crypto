package javacode;
import java.util.*;
public class key {
    Scanner sc = new Scanner(System.in);
    public String randomkey()
    {
        StringBuilder rand_int1 = new StringBuilder();
        Random rand = new Random();
        for(int i=0;i<6;i++)
        {
            rand_int1.append(Integer.toString(rand.nextInt(100000000,999999999)));
        }
        return rand_int1.toString();
    }
    public String userkey()
    {
        System.out.println("Enter the User Key for Password(A Single Word)");
        String stkey = sc.nextLine();
        return stkey;
    }
}
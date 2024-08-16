package javacode;
import java.util.*;
public class key {
    Scanner sc = new Scanner(System.in);
    public int randomkey()
    {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(100000000,999999999);
        return rand_int1;
    }
    public String userkey()
    {
        System.out.println("Enter the User Key for Password(A Single Word)");
        String stkey = sc.nextLine();
        return stkey;
    }
}
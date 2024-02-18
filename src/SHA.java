//import statements
import java.security.*;
import java.util.Scanner;
import java.math.*;

//class to implement the SHA algorithm
public class SHA {
    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 11\n"
                + "	Ex Name     : Implementation of SHA-1 Algorithm  \n"
                + "**********************************************************\n"
                + "");
    }
    public static void main(String args[])throws Exception{
        print_info();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the message : ");
        String message = scan.nextLine();
        
        //creating object for message digest class
        MessageDigest md = MessageDigest.getInstance("SHA");
        
        //computing hash value using digest method
        byte hash[] = md.digest(message.getBytes());
        
        //converting the generated hash value to a hexadecimal value
        BigInteger bi = new BigInteger(1,hash);
        String hash_value = bi.toString(16);
        
        while(hash_value.length()<32){
            hash_value = "0"+hash_value;
        }
        
        System.out.println("The hash value for the given message "+message+" is : "+hash_value);
    }
}

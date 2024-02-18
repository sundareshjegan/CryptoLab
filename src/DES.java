import java.util.Scanner;
import java.security.*;
import javax.crypto.*;

public class DES {
    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 07\n"
                + "	Ex Name     : Implementation of DES     \n"
                + "**********************************************************\n"
                + "");
    }
    public static void main(String args[]) throws Exception{
        print_info();
        
        Scanner scan = new Scanner(System.in);
        //creating key generator instance for DES
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        SecretKey key = kg.generateKey();
        
        System.out.print("Enter the password : ");
        String message = scan.nextLine();
        if(message.length()>=8){
            //create instance to generate cipher text
            Cipher ct = Cipher.getInstance("DES");
            
            //initialization of encryption process
            ct.init(Cipher.ENCRYPT_MODE, key);
            byte[] output = message.getBytes();
            
            //encrypt the given message
            byte[] result = ct.doFinal(output);

            System.out.println("The Encrypted Message : "+new String(result));

            ct.init(Cipher.DECRYPT_MODE, key);
            byte[] result1 = ct.doFinal(result);
            System.out.println("The Decrypted Message : "+new String(result1));
        }
        else{
            System.out.println("Invalid Password");
        }
    }
}

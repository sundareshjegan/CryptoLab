import java.util.Scanner;
import java.security.*;
import javax.crypto.*;

public class AES {
    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 08\n"
                + "	Ex Name     : Implementation of AES     \n"
                + "**********************************************************\n"
                + "");
    }
    public static void main(String args[]) throws Exception{
        print_info();
        
        Scanner scan = new Scanner(System.in);
        //creating key generator instance for AES
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        SecretKey key = kg.generateKey();
        
        System.out.print("Enter the Aadhar Number : ");
        String aadhar_no = scan.nextLine();
        if(aadhar_no.length()==12){
            //create instance to generate cipher text
            Cipher ct = Cipher.getInstance("AES");
            
            //initialization of encryption process
            ct.init(Cipher.ENCRYPT_MODE, key);
            byte[] output = aadhar_no.getBytes();
            
            //encrypt the given message
            byte[] encrypted_text = ct.doFinal(output);

            System.out.println("The Encrypted Text : "+new String(encrypted_text));

            ct.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted_text = ct.doFinal(encrypted_text);
            System.out.println("The Decrypted Text : "+new String(decrypted_text));
        }
        else{
            System.out.println("Invalid Aadhar Number");
        }
    }
}

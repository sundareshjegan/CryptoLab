import java.security.*;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;


public class DigitalSignature {
    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 12\n"
                + "	Ex Name     : Implementation of Digital Signature Standard  \n"
                + "**********************************************************\n"
                + "");
    }
    public static void main(String args[]) throws Exception{
        print_info();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the message : ");
        String message = scan.nextLine();
        
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        //generate a random intial vector
        SecureRandom initial_vector = new SecureRandom();
        
        kpg.initialize(2048, initial_vector);
        //generating private key and pulic key pair
        KeyPair kp = kpg.generateKeyPair();
        
        //seperate private key and public key
        PrivateKey privatekey = kp.getPrivate();
        PublicKey publickey = kp.getPublic();
        
        Signature signature = Signature.getInstance("SHA256withRSA");
        //signing with private key
        signature.initSign(privatekey);
        signature.update(message.getBytes());
        
        //signing process
        byte sign_bytes[] = signature.sign();
        
        System.out.println("Signature Generated");
        //System.out.println(DatatypeConverter.printHexBinary(sign_bytes));
        
        //verification process
        signature.initVerify(publickey);
        signature.update(message.getBytes());
        //signature.update("fakemessage for negative output".getBytes());
        
        boolean result = signature.verify(sign_bytes);
        if(result){
            System.out.println("Valid Signature");
        }
        else{
            System.out.println("Invalid Signature");
        }
        System.out.println("Verification Status : "+result);
    }
}

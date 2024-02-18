/*
Name : Sundareshwaran. J
Program Name : Caeser Cipher
*/
import java.util.Scanner;
public class CaeserCipher {
    static void printMenu(){
        System.out.println("MENU\n----\n1. Encryption\n2. Decryption");
        System.out.print("Enter your choice(1/2): ");
    }
    
    static String encrypt(String plainText){
        String result = "";
        int length = plainText.length();
        
        for(int i=0;i<length;i++){
            char ch = plainText.charAt(i);
            //System.out.println(ch);
        }
        return result;
    }
    static String decrypt(String plainText){
        String result = "";
        int length = plainText.length();
        
        for(int i=0;i<length;i++){
            char ch = plainText.charAt(i);
            //System.out.println(ch);
        }
        return result;
    }
    
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        
        printMenu();
        int choice = scan.nextInt();
        switch(choice){
            case 1:             
                System.out.println("The default Key for Caeser Cipher is 3 ");
                System.out.println("Enter the Plain Text : ");
                String plainText = scan.nextLine();
                plainText = scan.nextLine().toLowerCase();
                System.out.println("Encrypted Text : "+encrypt(plainText));
            break;
            
            case 2:
                System.out.println("Enter the Cipher Text : ");
                String cipherText = scan.nextLine();
                cipherText = scan.nextLine().toLowerCase();
                System.out.println("Decrypted Text : "+decrypt(cipherText));
            
            break;
                
        }
        
    }
}

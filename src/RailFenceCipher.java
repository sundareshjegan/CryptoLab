
import java.util.*;

public class RailFenceCipher {

    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 05\n"
                + "	Ex Name     : Implementation of RailFence Cipher     \n"
                + "**********************************************************\n"
                + "");
    }

    //function to check the character is alphabet
    static boolean isAlpha(String str) {
        for (char c : str.toCharArray()) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumber(int number) {
        return !String.valueOf(number).matches("\\d+");
    }
    
    //function to encrypt the plain text using railfence cipher
    static String encrypt(String plain_text, int depth) {
        String cipher_text = "";
        int length = plain_text.length();
        int cycle = 2 * (depth - 1);
        for (int i = 0; i < depth; i++) {
            for (int j = i; j < length; j += cycle) {
                cipher_text += plain_text.charAt(j);
                int second = (j - 1) + cycle - i;
                if (i != 0 && i != depth - 1 && second < length) {
                    cipher_text += plain_text.charAt(second);
                }
            }
        }
        return cipher_text.toUpperCase();
    }
    //function to decrypt the cipher text to plain text
    public static String decrypt(String cipherText, int depth) {
        int cipher_length = cipherText.length();
        char[] plainText = new char[cipher_length];
        int index = 0;
        for (int i = 0; i < depth; i++) {
          int railIndex = i;
          boolean goingDown = true;
          while (railIndex < cipher_length) {
            plainText[railIndex] = cipherText.charAt(index++);
            if (i == 0 || i == depth - 1) {
              railIndex += 2 * (depth - 1);
            } else if (goingDown) {
              railIndex += 2 * (depth - i - 1);
              goingDown = false;
            } else {
              railIndex += 2 * i;
              goingDown = true;
            }
          }
        }
        return new String(plainText).toLowerCase();
    }
    
    public static void main(String args[]){
        print_info();
        Scanner scan = new Scanner(System.in);
        int choice;
        do{
            System.out.print("\n\n1. Encrypt\n2. Decrypt\n3. Exit\nEnter your choice(1/2/3) : ");
            choice = scan.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter the Plain Text : ");
                    String plain_text = scan.next();
                    if(isAlpha(plain_text)){
                        System.out.print("Enter the Depth : ");
                        String p_depth = scan.next();
                        int depth = 0;
                        try{
                            depth = Integer.parseInt(p_depth);}catch(Exception e){
                            System.out.println("Sorry, input must be a positive number.");
                            break;
                        }
                        if(depth>0){
                            System.out.print(encrypt(plain_text,depth));
                        }                            
                        else{
                            System.out.println("Sorry, input must be a positive number.");
                        }
                     }
                    else{
                        System.out.println("Sorry, input must be a word.");
                    }                                              
                break;
                
                case 2:
                    System.out.print("Enter the Cipher Text : ");
                    String cipher_text = scan.next().toUpperCase();
                    if(isAlpha(cipher_text)){
                        System.out.print("Enter the Depth : ");
                        String c_depth = scan.next();
                        int depth;
                        try{
                            depth = Integer.parseInt(c_depth);}catch(Exception e){
                            System.out.println("Sorry, input must be a positive number.");
                            break;
                        }
                        if(depth>0){
                            System.out.print(decrypt(cipher_text,depth));
                        }                            
                        else{
                            System.out.println("Sorry, input must be a positive number.");
                        }
                    }
                    else{
                        System.out.println("Sorry, input must be a word.");
                    }
                break;
                
                case 3:
                    System.out.println("Bye");
                break;
                
                default:
                    System.out.println("Invalid Choice..!");
            }
        }while(choice!=3);
    }
}

/*
Name : Sundareshwaran. J
Roll Number : 20UIT037
Program Name : Implementation of Viginere Cipher
*/
import java.util.Scanner;

public class VigenereCipher {
   
    static void print_info(){
        System.out.println(""
        + "**********************************************************\n" +
        "    IT1681 - Cryptography and  Networks Security Laboratory\n" +
        "\n" +
        "	Roll Number : 20UIT037\n" +
        "	Name        : SUNDARESHWARAN. J\n" +
        "	Ex No	    : 03\n" +
        "	Ex Name     : Implementation of Viginere Cipher	\n" +
        "**********************************************************\n" +
        "");
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
    
    //function to encrypt the plain text
    static String vigenereEncrypt(String plain_text, String key){
        String cipher_text="";
        plain_text = plain_text.toLowerCase();
        
        int plain_text_length = plain_text.length();
        int key_length = key.length();
        
        //loop to generate key with respect to length of plain text
        String generated_key="";        
        for(int i=0;i<plain_text_length;i++){
            generated_key+=key.charAt(i%key_length);
        }
        
        //loop to generate cipher text
        for(int i=0;i<plain_text_length;i++){
            int j = plain_text.charAt(i)-97;
            int k = generated_key.charAt(i)-97;
            cipher_text+=(char)(((j+k)%26)+65);         
        }
        return cipher_text;
    }
    
    //function to decrypt the cipher text to plain text
    static String vigenereDecrypt(String cipher_text, String key){
        String plain_text="";
        cipher_text = cipher_text.toUpperCase();
        
        int cipher_text_length = cipher_text.length();
        int key_length = key.length();
        
        //key generation
        String generated_key="";
        for(int i=0;i<cipher_text_length;i++){
            generated_key+=key.charAt(i%key_length);
        }
        
        //decrypt cipher text to plain text
        for(int i=0;i<cipher_text_length;i++){
            int j = cipher_text.charAt(i)-65;
            int k = generated_key.charAt(i)-97;
            plain_text+=(char)((j-k)>=0?((j-k)%26)+97:26+(j-k)+97);         
        }
        return plain_text;
    }
    //main function
    public static void main(String args[]){
        print_info();
        Scanner scan = new Scanner(System.in);
        System.out.println("\tVIGINERE CIPHER MENU\n\t-------- ------ ----");
        int choice;
        do{
            System.out.print("\n\n1. Encrypt\n2. Decrypt\n3. Exit\nEnter your choice(1/2/3) : ");
            choice = scan.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter the Plain Text : ");
                    String plain_text = scan.next();
                    if(isAlpha(plain_text)){
                        System.out.print("Enter the key : ");
                        String key = scan.next().toLowerCase();
                        if(isAlpha(key)){
                            System.out.print("Cipher Text : "+vigenereEncrypt(plain_text,key));
                        }
                        else{
                            System.out.println("Sorry,input must be a word");
                        }
                    }
                    else{
                        System.out.println("Sorry,input must be a word");
                    }
                    break;

                case 2:
                    System.out.print("Enter the Cipher Text : ");
                    String cipher_text = scan.next();
                    if(isAlpha(cipher_text)){
                        System.out.print("Enter the key : ");
                        String key = scan.next().toLowerCase();
                        if(isAlpha(key)){
                            System.out.print("Plain Text : "+vigenereDecrypt(cipher_text,key));
                        }
                        else{
                            System.out.println("Sorry,input must be a word");
                        }
                    }
                    else{
                        System.out.println("Sorry,input must be a word");
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

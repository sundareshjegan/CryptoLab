import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowColumnar {
    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 06\n"
                + "	Ex Name     : Implementation of Row-Columnar Transposition Cipher \n"
                + "**********************************************************\n"
                + "");
    }
    
    Scanner in=new Scanner(System.in);
   
    //Method to convert String into 2d array
    protected char[][] matrix(String text,int klen,int Tlen,int flag){
        char[] alp={'A','B','C','D','E','F','G','H','I','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int Olen=Tlen,in=0;
        if(text.length()%klen!=0){
            do{
                text=text+alp[in];
                in++;
            }while(text.length()%klen!=0);
        }
        int k=0;
        Tlen=text.length();
        char[][] word=new char[Tlen/klen][klen];
        for(int i=0;i<((flag==0)?klen:(Tlen/klen));i++){
            for(int j=0;j<((flag==0)?(Tlen/klen):klen);j++){
                word[((flag==0)?j:i)][((flag==0)?i:j)]=text.charAt(k);
                k++;
            }
        }
        return word;
    }
   
    //Method to encrypt the plaintext into cipher text
    protected void Encrypt(String text,int[] key,int klen,int Tlen,int flag){
        char[][] remix=matrix(text,klen,Tlen,flag);
        int[] ok=new int[klen];
        System.arraycopy(key, 0, ok, 0, klen);
        Arrays.sort(key);
        int c=0,index=0;
        String ans="";
        for(int i=0;i<klen;i++){
            for(int k=0;k<ok.length;k++){
                if(key[i]==ok[k]){
                    index=k;
                }
            }
            for(int j=0;j<remix.length;j++){
                ans=ans+""+remix[j][index];
            }
        }
        System.out.print("Encrypted Text : "+ans+"\n");
    }
   
    //Method to decrypt the Cipher text into plain text
    protected void Decrypt(String text,int[] key,int klen,int Tlen,int flag){
        char[][] remix=matrix(text,klen,Tlen,flag);
        int[] ok=new int[klen];
        System.arraycopy(key, 0, ok, 0, klen);
        Arrays.sort(key);
        int c=0,index=0,n=0;
        String ans="";
        for(int i=0;i<remix.length;i++){
            while(ans.length()<(i+1)*klen){
                for(int k=0;k<ok.length;k++){
                    if(ok[n]==key[k]){
                        index=k;
                        n++;
                        break;
                    }
                }
                ans=ans+""+remix[i][index];
                if(n>=klen){
                    n=0;
                }
            }
        }
        String pattern = "AB|ABC|ABCD|ABCDE|ABCDEF|ABCDEFG|ABCDEFGH|ABCDEFGHI";
        String replacement = "";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(ans);
        String result = m.replaceAll(replacement);
        System.out.println("Decrypted Text : "+result.toLowerCase());
    }
   
   
    //Get the input required inputs from the user
    public void input(int flag){
        if(flag==1)
            System.out.print("Enter the Plain Text : ");
        else
            System.out.print("Enter the Cipher Text : ");
        String text=in.nextLine();
        text=text.toUpperCase();
        if(text.matches(".*\\d.*"))//method to find whether the integer is prest in the string
            System.out.println("\nSorry,input must be a word.....\n");
        else{
            System.out.print("Enter the Keyword : ");
            String keyword=in.nextLine();
            if(keyword.matches(".*\\d.*")){
                System.out.println("\nSorry,input must be a word.....\n");
            }
            else{
                int len=text.length();
                keyword=keyword.replaceAll("\\s", "");
                text=text.replaceAll("\\s", "");
                int klen=keyword.length();
                int[] key=new int[klen];
                for(int i=0;i<klen;i++){
                    key[i]=(int)(keyword.charAt(i)-65);
                }
                if(flag==1){
                    Encrypt(text,key,klen,len,flag);
                }
                else{
                    Decrypt(text,key,klen,len,flag);
                }
                   
            }
        }
    }
   
     public static void main(String arg[]){
        print_info();
        int choice,flag=0;
        boolean shouldbreak=false;
        RowColumnar obj=new RowColumnar();
        Scanner in=new Scanner(System.in);
        do{
        System.out.println("\nEnter the choice...\n1.Encryption\n2.Decryption\n3.Exit\n");
        System.out.print("Enter the choice : ");
        choice=in.nextInt();
        switch(choice){
            case 1:
                System.out.println("----------ENCRYPTION-------------");
                flag=1;
                obj.input(flag);
                break;
            case 2:
                System.out.println("----------DECRYPTION-------------");
                flag=0;
                obj.input(flag);
                break;
            default:
                System.out.println("Bye.....");
                shouldbreak=true;
        }
        if(shouldbreak)
            break;
        }while(choice<3);
    }
}
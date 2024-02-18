
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HillCipher {
    Scanner in=new Scanner(System.in);
    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 04\n"
                + "	Ex Name     : Implementation of Hill Cipher     \n"
                + "**********************************************************\n"
                + "");
    }
    //Method to encrypt the plaintext into cipher text
    protected void Encrypt(String text,int m,int n,int[][] a){
            int Olen=text.length();
            if(text.length()%n!=0){
                do{
                    text=text+"X";
                }while(text.length()%n!=0);
            }
            int Tlen=text.length();
            List<String> la=new ArrayList<String>();
            for(int i=0;i<Tlen;i+=n){
                la.add(text.substring(i,i+n));
            }
            if(m==3){
                int count=0;
                for(int k=0;k<la.size();k++){
                    for(int i=0;i<m;i++){
                        int sum=0;
                        for(int j=0;j<n;j++){
                            char c=la.get(k).charAt(j);
                            int res=a[j][i]*((int)c-65);
                            sum+=res;
                        }
                        if(count<Olen)
                            System.out.print((char)((sum%26)+65));
                        count++;
                    }
                }
            }
            else{
                int count=0;
                for(int k=0;k<la.size();k++){
                    for(int i=0;i<m;i++){
                        int sum=0;
                        for(int j=0;j<n;j++){
                            char c=la.get(k).charAt(j);
                            int res=a[i][j]*((int)(c)-65);
                            sum+=res;
                        }
                        if(count<Olen)
                            System.out.print((char)((sum%26)+65));
                        count++;
                    }
                }
                 System.out.println();
            }
    }
   
    //method to find the determinant for the inverse
    public double determinant(int[][] a,int m){
        if(m==2){
            double det = a[0][0] * a[1][1] - a[0][1] * a[1][0];
            double n=det%26;
            if(n<0){
                n=n+26;
            }
            int i=-1;double ans=0;
            while(ans!=1){
                i++;
                ans=(n*i)%26;
            }
            return i;
        }
        else if(m==3){
            double det = a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1])
                        - a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0])
                        + a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);
            double n=det%26;
            if(n<0){
                n=n+26;
            }
            int i=0;double ans=0;
            while(ans!=1){
                i++;
                ans=(n*i)%26;
            }
            return i;
        }
        return 0;
    }
   
    //method to findex adjacent for the inverse
    public int[][] adjacent(int det,int[][] a,int m){
        if(m==2){
            int temp=a[0][0];
            a[0][0]=a[1][1]*det;
            a[1][1]=temp*det;
            a[1][0]=-a[1][0]*det;
            a[0][1]=-a[0][1]*det;
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
                    a[i][j]=a[i][j]%26;
                    if(a[i][j]<0){
                        a[i][j]+=26;
                    }
                }
            }
            return a;
        }
        else if(m==3){
            int[][] adj = new int[3][3];
            adj[0][0] = a[1][1] * a[2][2] - a[1][2] * a[2][1];
            adj[0][1] = -(a[0][1] * a[2][2] - a[0][2] * a[2][1]);
            adj[0][2] = a[0][1] * a[1][2] - a[0][2] * a[1][1];
            adj[1][0] = -(a[1][0] * a[2][2] - a[1][2] * a[2][0]);
            adj[1][1] = a[0][0] * a[2][2] - a[0][2] * a[2][0];
            adj[1][2] = -(a[0][0] * a[1][2] - a[0][2] * a[1][0]);
            adj[2][0] = a[1][0] * a[2][1] - a[1][1] * a[2][0];
            adj[2][1] = -(a[0][0] * a[2][1] - a[0][1] * a[2][0]);
            adj[2][2] = a[0][0] * a[1][1] - a[0][1] * a[1][0];
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
                    adj[i][j]=adj[i][j]%26;
                    if(adj[i][j]<0){
                        adj[i][j]+=26;
                    }
                }
            }
            return adj;
        }    
        return a;
    }
   
    //Method to decrypt the Cipher text into plain text
    protected void Decrypt(String text,int m,int n,int[][] a){
        int det=(int)determinant(a,m);
        int[][] inverse=adjacent(det,a,m);
        if(m==3){
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    inverse[i][j]=inverse[i][j]*det;
                }
            }
        }
        Encrypt(text,m,n,inverse);
    }
   
    //function to print the input and output values
    protected void print(String text,int m,int n,int[][] a,int flag){
        if(flag==1)
            System.out.println("The plain Text : "+text);
        else
            System.out.println("The Cipher Text : "+text);
        System.out.println("Matrix : "+m+"x"+n);
    }
   
     //Get the input required inputs from the user
    public void input(int flag){
        int f=0;
        if(flag==1)
            System.out.print("Enter the Plain Text : ");
        else
            System.out.print("Enter the Cipher Text : ");
        String text=in.next();
        text=text.toUpperCase();
        if(text.matches(".*\\d.*"))
            System.out.println("\nSorry,input must be a word\n");
        else{
           
            System.out.print("Enter the Row and Column seperated by space : ");
            int m=in.nextInt();
            int n=in.nextInt();
            if(n!=m && n>0 && m>0)
                System.out.println("\nSorry,Matrix is not a square Matrix\n");
            else if(n<0 || m<0)
                System.out.println("\nSorry,input must be a positive number\n");
            else{
                String s[][]=new String[m][n];
                System.out.print("Enter the "+m+"x"+n+" Matrix Value Seperated by space : ");
                for(int i=0;i<m;i++){
                    for(int j=0;j<n;j++){
                        s[i][j]=in.next();
                        if(s[i][j].matches("[a-zA-Z]+")){
                            f=1;
                        }
                    }
                }
                if(f==1){
                    System.out.println("\nSorry,input must be a positive number\n");
                }
                else{
                int a[][]=new int[m][n];
                for(int i=0;i<m;i++){
                    for(int j=0;j<n;j++){
                        a[i][j]=Integer.parseInt(s[i][j]);
                        if(a[i][j]<0)
                            f=2;
                      }
                }
                if(f==2){
                        System.out.println("\nSorry,input must be a positive number\n");
                }
                else{
                    print(text,m,n,a,flag);
                    if(flag==1){
                        System.out.print("\nEncrypted Text :");
                        Encrypt(text,m,n,a);
                    }
                    else{
                        System.out.print("\nDecrypted Text :");
                        Decrypt(text,m,n,a);
                    }
                }
            }
            }
        }
    }
   
    public static void main(String arg[]){
        print_info();
        int choice,flag=0;
        Scanner in=new Scanner(System.in);
        HillCipher obj=new HillCipher();
        boolean shouldbreak=false;
        do{
        System.out.println("\nEnter the choice...\n1.Encryption\n2.Decryption\n3.Exit\n");
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

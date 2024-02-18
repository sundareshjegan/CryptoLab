import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class DiffieHellman {
    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 10\n"
                + "	Ex Name     : Implementation of DiffieHellman Key Exchange Algorithm \n"
                + "**********************************************************\n"
                + "");
    }
    public static void main(String args[])
    {
        print_info();
        double ya=0,yb=0,ka=0,kb=0;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter value of q : ");
        double q = scan.nextDouble();

        System.out.print("Enter the primitive root(alpha) : ");
        double alpha = scan.nextDouble();
        
        if(alpha>q)
            System.out.println("Alpha is greater than q");
        
        else
            System.out.print("Enter Private key of User A : ");
            double xa = scan.nextDouble();
            
            System.out.print("Enter Private key of User B : ");
            double xb = scan.nextDouble();
            
            //check the chosen private key is greater than q
            if(xa>q || xb > q)
                System.out.println("Private keys are greater than q");
            
            else{
                ya = Math.pow(alpha,xa);
                ya=ya%q;
                yb = Math.pow(alpha,xb);
                yb=yb%q;
                
                //generating secret key for user A
                ka=Math.pow(yb,xa);
                ka=ka%q;
                
                //generating secret key for user B
                kb=Math.pow(ya,xb);
                kb=kb%q;                
            }            
            if(ka==kb)
                System.out.println("The shared secret key is : " +(int)ka);     
    }
}
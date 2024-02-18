
import java.util.*;

public class PlayFairCipher {

    static void print_info() {
        System.out.println(""
                + "**********************************************************\n"
                + "    IT1681 - Cryptography and  Networks Security Laboratory\n"
                + "\n"
                + "	Roll Number : 20UIT037\n"
                + "	Name        : SUNDARESHWARAN. J\n"
                + "	Ex No	    : 02\n"
                + "	Ex Name     : Implementation of Playfair Cipher	\n"
                + "**********************************************************\n"
                + "");
    }

    final static int index = 5;
    Scanner in = new Scanner(System.in);

    public static boolean check(char[][] key, char a, int x, int y) {
        for (int k = 0; k <= x; k++) {
            y = (x > 0) ? 5 : y;
            for (int l = 0; l < y; l++) {
                if (key[k][l] == a) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<String> pair(String text) {
        int Tlen = text.length();
        int olen = Tlen;
        List<String> a = new ArrayList<String>();
        int c = 0;
        try {
            for (int i = 2; i <= Tlen + 1; i += 2) {
                a.add(text.substring(i - 2, i));
                String check = a.get(c);
                if (check.charAt(0) == check.charAt(1)) {
                    a.set(c, check.charAt(1) + "X");
                    i--;
                }
                c++;
            }
            System.out.print("{ ");
            for (String s : a) {
                System.out.print(s + " ");
            }
        } catch (Exception e) {
            String excep = e.getMessage();
            System.out.print("{ ");
            for (String s : a) {
                System.out.print(s + " ,");
            }
            if (excep == excep) {
                a.add(text.substring(olen - 1) + "Z");
            }
            System.out.print(a.get(c) + " ");
        }
        System.out.print("}\n");
        return a;
    }

    public static void cipherText(List<String> pair, char[][] key) {
        int Llen = pair.size();
        int ki1_row = 0, ki1_col = 0, ki2_row = 0, ki2_col = 0;
        System.out.print("\nEncrypted Text:");
        for (int i = 0; i < Llen; i++) {
            String s = pair.get(i);
            
            char c1 = s.charAt(0);
            char c2 = s.charAt(1);
            
            for (int j = 0; j < index; j++) {
                for (int k = 0; k < index; k++) {
                    if (key[j][k] == c1) {
                        ki1_row = j;
                        ki1_col = k;
                    }
                    if (key[j][k] == c2) {
                        ki2_row = j;
                        ki2_col = k;
                    }
                }
            }
            if (ki1_row == ki2_row) {
                c1 = key[ki1_row][(ki1_col != index - 1) ? ki1_col + 1 : 0];
                c2 = key[ki2_row][(ki2_col != index - 1) ? ki2_col + 1 : 0];
            } 
            else if (ki1_col == ki2_col) {
                c1 = key[(ki1_row != index - 1) ? ki1_row + 1 : 0][ki1_col];
                c2 = key[(ki2_row != index - 1) ? ki2_row + 1 : 0][ki2_col];
            } 
            else {
                c1 = key[ki1_row][ki2_col];
                c2 = key[ki2_row][ki1_col];
            }
            System.out.print(c1 + "" + c2);
        }
    }

    public static void Encrypt(int flag) {
        Scanner in = new Scanner(System.in);
        char[] alp = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[][] key = new char[index][index];

        System.out.print("Enter the Text : ");
        String text = in.nextLine();
        if (text.matches(".*\\d.*")) {
            System.out.println("\nSorry,input must be a word\n");
        } else {
            System.out.print("Enter the key : ");
            String keyword = in.nextLine();
            if (keyword.matches(".*\\d.*")) {
                System.out.println("Sorry,input must be a word");
            } else {
                int len = text.length();
                System.out.print("Keyword:");
                keyword = keyword.replaceAll("\\s", "");
                System.out.println(keyword = keyword.toUpperCase());
                System.out.print("PlainText:");
                text = text.replaceAll("\\s", "");
                
                System.out.println((text = text.toUpperCase()) + "\n");
                int klen = keyword.length();
                int c = 0;
                for (int i = 0; i < index; i++) {
                    for (int j = 0; j < index;) {
                        if (c >= klen) {
                            if (check(key, alp[c - klen], i, j)) {
                                key[i][j] = alp[c - klen];
                                j++;
                            }
                        } else {
                            char ch = keyword.charAt(c);
                            if (check(key, ch, i, j)) {
                                key[i][j] = keyword.charAt(c);
                                j++;
                            }
                        }

                        c++;
                    }
                }

                System.out.println("The Text Pairs are : \n");
                List<String> pairs = new ArrayList<>();
                pairs = pair(text);

                System.out.println("\nThe Key Matrix\n--- --- ------");
                for (int i = 0; i < index; i++) {
                    System.out.println("---------------------");
                    for (int j = 0; j < index; j++) {
                        System.out.print("| ");
                        System.out.print(key[i][j] + " ");
                    }
                    System.out.println("|");
                }
                System.out.println("---------------------");
                if (flag == 1) {
                    cipherText(pairs, key);
                } else {
                    Decrypt(pairs, key);
                }
                System.out.println("\n");
            }
        }
    }

    public static void Decrypt(List<String> pair, char[][] key) {
        int Llen = pair.size();
        int ki1_row = 0, ki1_col = 0, ki2_row = 0, ki2_col = 0;
        System.out.print("\nEncrypted Text:");
        for (int i = 0; i < Llen; i++) {
            String s = pair.get(i);
            
            char c1 = s.charAt(0);
            char c2 = s.charAt(1);
            
            for (int j = 0; j < index; j++) {
                for (int k = 0; k < index; k++) {
                    if (key[j][k] == c1) {
                        ki1_row = j;
                        ki1_col = k;
                    }
                    if (key[j][k] == c2) {
                        ki2_row = j;
                        ki2_col = k;
                    }
                }
            }
            if (ki1_row == ki2_row) {
                c1 = key[ki1_row][(ki1_col != 0) ? ki1_col - 1 : index - 1];
                c2 = key[ki2_row][(ki2_col != 0) ? ki2_col - 1 : index - 1];
            } 
            else if (ki1_col == ki2_col) {
                c1 = key[(ki1_row != 0) ? ki1_row - 1 : index - 1][ki1_col];
                c2 = key[(ki2_row != 0) ? ki2_row - 1 : index - 1][ki2_col];
            } 
            else {
                c1 = key[ki1_row][ki2_col];
                c2 = key[ki2_row][ki1_col];
            }
            System.out.print(c1 + "" + c2);
        }

    }

    public static void main(String arg[]) {
        print_info();
        int choice, flag = 0;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("\n\n1.Encryption\n2.Decryption\n3.Exit\nEnter your choice : ");
            choice = in.nextInt(); 

            switch (choice) {
                case 1: {
                    System.out.println("\n-----------ENCRYPTION-----------");
                    flag = 1;
                    Encrypt(flag);
                    break;
                }
                case 2: {
                    System.out.println("\n-----------DECRYPTION-----------");
                    flag = 0;
                    Encrypt(flag);
                    break;
                }
                case 3: {
                    System.out.println("Bye...");
                    System.exit(0);
                }
                default:
                    System.out.println("Invalid Choice..!");
            }
        } while (choice != 3);
    }
}

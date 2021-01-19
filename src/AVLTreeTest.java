import java.util.*;
import java.util.Scanner;


 // AVL agac test 

 public class AVLTreeTest{
    
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // AVL Ağacı nesnesinin oluşturulması 
        AVLTree avl = new AVLTree();
        AVLNode root = null;
        System.out.println("-----------AVLTree AGAC Test-------");
        char ch;
       
        //  Ağaç işlemlerini gerçekleştirilmesi 
        do    
        {
            System.out.println("--------------AVLTree İşlemleri----------");
            System.out.println("|   1. EKLE                             |");
            System.out.println("|   2. ARA                              |");
            System.out.println("|   3. DÜĞÜMLERİ SAY                    |");
            System.out.println("|   4. BOŞ KONTROL ET                   |");
            System.out.println("|   5. AĞACI TEMİZLE                    |");
            System.out.println("|   6. SİL                              |");
            System.out.println("-----------------------------------------");
            System.out.println("||=====================================||");
            System.out.println("||YUKARIDAKİ İŞLEMLERDEN BİRİNİ SEÇİNİZ:||");
            System.out.println("||=====================================||");
            int sec = input.nextInt();
            switch (sec) {
                case 1:
                    System.out.println("||=====================================||");
                    System.out.println("||Eklenecek tam sayı öğesini girin:    ||");
                    System.out.println("||=====================================||");
                    avl.insert(input.nextInt());
                    break;
                case 2:
                    System.out.println("||=====================================||");
                    System.out.println("||Aramak için tamsayı eleman girin:    ||");
                    System.out.println("||=====================================||");
                    System.out.println("||=====================================||");
                    System.out.println("||Arama sonuçları :||"+ avl.ara(input.nextInt()));
                    System.out.println("||=====================================||");
                    System.out.println();
                    break;
                case 3:
                    System.out.println("||=====================================||");
                    System.out.println("||Düğümler = ||" + avl.dugumleriSay());
                    System.out.println("||=====================================||");
                    break;
                case 4:
                    System.out.println("||=====================================||");
                    System.out.println("||Boş durum = ||" + avl.Bos());
                    System.out.println("||=====================================||");
                    break;
                case 5:
                    System.out.println("||=====================================||");
                    System.out.println("|| Ağaç Temizlendi ||");
                    System.out.println("||=====================================||");
                    avl.Bosalt();
                    break;
                case 6:
                    System.out.println("||=====================================||");
                    System.out.println("||silinecek tam sayı öğesini girin:    ||");
                    System.out.println("||=====================================||");
                    avl.sil(input.nextInt());
                    break;
                default:

                    System.out.println("\n\n=========================================");
                    System.out.println("\n\t||Hatali Giris Yaptiniz..||");
                    System.out.println("\n=========================================\n");
                    break;
            }
            /* Ekran ağacı */ 
            
            System.out.print("\n||Post order : ");
            avl.postorder();
            System.out.print("\n||Pre order : ");
            avl.preorder();
            System.out.print("\n||In order : ");
            avl.insert();
            
            System.out.println("\n||==============================================||");
            System.out.println("||Devam etmek istiyor musunuz (e veya h yazın)||");
            System.out.println("||==============================================||\n");
            ch = input.next().charAt(0); 
            
            if(ch == 'h'|| ch == 'H'){
                System.out.println("\n||=======================================||");
                System.out.println("program sonlandırıldı");
                System.out.println("||========================================||\n");
                break;
            }
        } while (ch == 'E'|| ch == 'e');               
    }
 }
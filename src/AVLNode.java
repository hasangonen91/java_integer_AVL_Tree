// Sınıf AVLNode 
 class AVLNode
 {    
     AVLNode sol, sag;
     int veri;
     int yukseklik;
     
     // Constructor 
     public AVLNode()
     {
         sol = null;
         sag = null;
         veri = 0;
         yukseklik = 0;
     }
     // Constructor 
     public AVLNode(int n)
     {
         sol = null;
         sag = null;
         veri = n;
         yukseklik = 0;
     }     
 }

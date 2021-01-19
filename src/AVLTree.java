  // sınıf AVLTree 
 class AVLTree{
     
     private AVLNode root;     
 
     // Constructor 
    public AVLTree(){
         root = null;
     }
     // Ağacın boş olup olmadığını kontrol etme işlevi
    public boolean Bos(){
         return root == null;
     }
     // Ağacı mantıksal olarak boş yap 
    public void Bosalt(){
         root = null;
     }
     //Veri ekleme işlevi 
    public void insert(int veri){
         root = ekle(veri, root);
     }
     //Veri silme işlevi 
    public void sil( int veri ){
            root = sil(this.root, veri);
            return;
        }
     // Düğüm yüksekliğini alma işlevi 
    private int yukseklik(AVLNode t )
     {
        return t == null ? -1 : t.yukseklik;
     }
     // Maksimum sol / sağ düğüm işlevi
    private int max(int lhs, int rhs)
     {
        return lhs > rhs ? lhs : rhs;
     }
     // Verileri yinelemeli olarak ekleme işlevi
    private AVLNode ekle(int x, AVLNode t)
     {
        if (t == null)
            t = new AVLNode(x);
        else if (x < t.veri){
             t.sol = ekle( x, t.sol );
             if( yukseklik( t.sol ) - yukseklik( t.sag ) == 2 )
                 if( x < t.sol.veri )
                     t = solcocukdondur( t );
                 else
                     t =solcocuklucift( t );
         }
         else if( x > t.veri )
         {
             t.sag = ekle( x, t.sag );
             if( yukseklik( t.sag ) - yukseklik( t.sol ) == 2 )
                 if( x > t.sag.veri)
                     t = sagcocukdondur( t );
                 else
                     t = sagcocuklucift( t );
         }
         else
           ;  // Çiftleme hiçbir şey değil değersiz
         t.yukseklik = max( yukseklik( t.sol ), yukseklik( t.sag ) ) + 1;
         return t;
     }
      // N düğümünün Denge faktörünü aldık
    private int getBalance(AVLNode a) {
        if (a == null)
            return 0;
        return yukseklik(a.sol) - yukseklik(a.sag);
    }
    public void preOrder(AVLNode root) {
        if (root != null) {
            preOrder(root.sol);
            System.out.printf("%d ", root.veri);
            preOrder(root.sag);
        }
    }
    private AVLNode minNode(AVLNode a) {
        AVLNode current = a;
        // en soldaki yaprağı bulmak için aşağı indirdik
        while (current.sol != null)
            current = current.sol;
        return current;
    }
    private AVLNode sil(AVLNode a, int value) {
        // ADIM 1: standart Binary Search Tree silme yaptık

        if (a == null)
            return a;

        // Silinecek değer a'nın değerinden küçükse
        // sonra sol alt ağaçta duruyor
        if ( value < a.veri )
            a.sol = sil(a.sol, value);

        // Silinecek değer a'nın değerinden büyükse
        // o zaman doğru alt ağaçta duruyor
        else if( value > a.veri )
            a.sag = sil(a.sag, value);

        // değer a'nın değeriyle aynıysa, bu düğümdür
        // silindi :D
        else {
            // tek çocuğu olan veya çocuğu olmayan düğüm
            if( (a.sol == null) || (a.sag == null) ) {

                AVLNode temp;
                if (a.sol != null)
                        temp = a.sol;
                else
                    temp = a.sag;

                // Çocuk yok
                if(temp == null) {
                    temp = a;
                    a = null;
                }
                else // Bir çocuk var
                    a = temp; // Boş olmayan çocuğun içeriğini kopyaladık

                temp = null;
            }
            else {
                // iki çocuklu düğüm sıralı halefi aldık 
                // en küçük sağ alt ağaça aldık
                AVLNode temp = minNode(a.sag);

                // Sıralı halefin verilerini bu düğüme kopyaladık
                a.veri = temp.veri;

                //Sıralı cocukları sil
                a.sag = sil(a.sag, temp.veri);
            }
        }

        // Ağacın sadece bir düğümü varsa o zaman geri dön
        if (a == null)
            return a;

        //  DÜĞÜMÜN YÜKSEKLİĞİNİ GÜNCELLEDİK
        a.yukseklik = Math.max(yukseklik(a.sol), yukseklik(a.sag)) + 1;

        // BU DÜĞÜMÜN DENGE FAKTÖRÜNÜ ALDIK
        //  kontrol etmek için bu düğüm dengesizleşti
        int balance = getBalance(a);

        // Bu düğüm dengesiz hale gelirse, o zaman 4 durum vardır

        // sol taraf Sol kısım
        if (balance > 1 && getBalance(a.sol) >= 0)
            return sagcocukdondur(a);

        // Sol taraf sağ ksım
        if (balance > 1 && getBalance(a.sol) < 0) {
            a.sol =  solcocukdondur(a.sol);
            return sagcocukdondur(a);
        }

        // sol taraf sağ kısım
        if (balance < -1 && getBalance(a.sag) <= 0)
            return solcocukdondur(a);

        // sağ sol kısım
        if (balance < -1 && getBalance(a.sag) > 0) {
            a.sag = sagcocukdondur(a.sag);
            return solcocukdondur(a);
        }

        return a;
    }   
     //b Sol çocukla ikili ağaç düğümünü döndür      
     private AVLNode solcocukdondur(AVLNode k2)
     {
         AVLNode k1 = k2.sol;
         k2.sol = k1.sag;
         k1.sag = k2;
         k2.yukseklik = max( yukseklik( k2.sol ), yukseklik( k2.sol ) ) + 1;
         k1.yukseklik = max( yukseklik( k1.sol ), k2.yukseklik ) + 1;
         return k1;
     }

     /* Sağ çocukla ikili ağaç düğümünü döndür */
     private AVLNode sagcocukdondur(AVLNode k1)
     {
         AVLNode k2 = k1.sag;
         k1.sag = k2.sol;
         k2.sol = k1;
         k1.yukseklik = max( yukseklik( k1.sol ), yukseklik( k1.sag ) ) + 1;
         k2.yukseklik = max( yukseklik( k2.sag ), k1.yukseklik ) + 1;
         return k2;
     }
     /**
     * Çift döndürmeli ikili ağaç düğümü: ilk soldaki çocuk
      * doğru çocuğu ile; sonra yeni sol çocuklu k3 düğümü */
     private AVLNode solcocuklucift(AVLNode k3)
     {
         k3.sol = sagcocukdondur( k3.sol );
         return solcocukdondur( k3 );
     }
     /**
      * Çift döndürme ikili ağaç düğümü: ilk sağ çocuk
      * sol çocuğuyla; sonra yeni sağ çocuklu k1 düğümü */      
     private AVLNode sagcocuklucift(AVLNode k1)
     {
         k1.sag = solcocukdondur( k1.sag );
         return sagcocukdondur( k1 );
     }    
     /* Düğüm sayısını sayma işlevleri */
     public int dugumleriSay()
     {
         return dugumleriSay(root);
     }
     private int dugumleriSay(AVLNode r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += dugumleriSay(r.sol);
             l += dugumleriSay(r.sag);
             return l;
         }
     }
     //Bir elemanı arama fonksiyonları 
     public boolean ara(int val)
     {
         return ara(root, val);
     }
     private boolean ara(AVLNode r, int val)
     {
         boolean bulundu = false;
         while ((r != null) && !bulundu)
         {
             int rval = r.veri;
             if (val < rval)
                 r = r.sol;
             else if (val > rval)
                 r = r.sag;
             else
             {
                 bulundu = true;
                 break;
             }
             bulundu = ara(r, val);
         }
         return bulundu;
     }
     //Sıralı geçiş işlevi 
     public void insert()
     {
         insert(root);
     }
     private void insert(AVLNode r)
     {
         if (r != null)
         {
             insert(r.sol);
             System.out.print(r.veri +" ");
             insert(r.sag);
         }
     }
     // Ön sipariş geçişi işlevi 
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(AVLNode r)
     {
         if (r != null)
         {
             System.out.print(r.veri +" ");
             preorder(r.sol);             
             preorder(r.sag);
         }
     }
     // Postorder geçişi işlevi 
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(AVLNode r)
     {
         if (r != null)
         {
             postorder(r.sol);             
             postorder(r.sag);
             System.out.print(r.veri +" ");
         }
     }     

 }
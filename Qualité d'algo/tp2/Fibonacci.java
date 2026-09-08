public class Fibonacci {
    public static int compteurAdditions = 0;

    public static final int fibo_rec_naif(final int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        compteurAdditions++; 
        return fibo_rec_naif(n - 1) + fibo_rec_naif(n - 2);
    }

    public static final int fibo_iter(final int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int precedent2 = 0;
        int precedent1 = 1;
        int courant = 0;

        for (int i = 2; i <= n; i++) {
            courant = precedent1 + precedent2;
            compteurAdditions++;

            precedent2 = precedent1;
            precedent1 = courant;
        }

        return courant;
    }


    public static final int fibo_rec(final int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibo_rec_aide(n, 2, 0, 1);
    }

   
    private static int fibo_rec_aide(final int n, final int i, final int precedent2, final int precedent1) {
        final int courant = precedent1 + precedent2;
        compteurAdditions++;


        if (i == n) {
            return courant;
        }

        return fibo_rec_aide(n, i + 1, precedent1, courant);
    }

    public static void main(final String[] args) {
        final int n = 10; 

        compteurAdditions = 0; 
        int resNaif = fibo_rec_naif(n);
        int addNaif = compteurAdditions;

        compteurAdditions = 0; 
        int resIter = fibo_iter(n);
        int addIter = compteurAdditions;

        compteurAdditions = 0; 
        int resRec = fibo_rec(n);
        int addRec = compteurAdditions;
        
        System.out.println("--- Résultats pour n = " + n + " ---");
        System.out.println("Fibo Naïf     : Valeur = " + resNaif + " | Additions = " + addNaif);
        System.out.println("Fibo Itératif : Valeur = " + resIter + " | Additions = " + addIter);
        System.out.println("Fibo Récur.   : Valeur = " + resRec  + " | Additions = " + addRec);
        
        System.out.println("\n-> Vérification : Les additions itératives et récursives sont-elles identiques ? " 
                           + (addIter == addRec ? "OUI" : "NON"));
    }
}

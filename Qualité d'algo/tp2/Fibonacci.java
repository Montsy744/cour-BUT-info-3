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

    public static void main(final String[] args) {
        final int n = 5;
        compteurAdditions = 0; 
        final int resultat = fibo_rec_naif(n);
        
        System.out.println("Fibo(" + n + ") = " + resultat);
        System.out.println("Nombre d'additions effectuees : " + compteurAdditions);
    }
}

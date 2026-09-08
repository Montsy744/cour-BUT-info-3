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

    public static void main(final String[] args) {
        final int n = 5;
        compteurAdditions = 0; 
        final int resultat = fibo_rec_naif(n);
        
        System.out.println("Fibo(" + n + ") = " + resultat);
        System.out.println("Nombre d'additions effectuees : " + compteurAdditions);
    }
}

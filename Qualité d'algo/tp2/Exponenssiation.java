public class Exponenssiation {

    public static double puissance(final double x, final int n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 1 / puissance(x, -n);
        }
        return x * puissance(x, n - 1);
    }

}

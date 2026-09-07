public class implement_algo {

    public final int algo3(final int[] tab) {
        int x = 0;
        for (int i = 0; i < tab.length - 1; i++) {
            x = x + tab[i];
        }
        return x;
    }

    public final int algo4(final int[] tab) {
        int x = 0;
        for(int i = 0; i < tab.length - 1; i++) {
            for(int j = 0; j < tab.length - 1; j++) {
                x ++;
            }
        }

        return x;
    }

    public final int algo6(final int[] tab) {
        int x = 0;
        int i = tab.length;
        while (i > 0) {
            x++;
            i = i/2;
        }
        return x;
    }

    public final int recherche_lineaire(
        final int[] tab,
        final int val
    ) {
        int i = 0;
        while (i < tab.length && tab[i] != val) {
            i++;
        }
        if (i < tab.length) {
            return i;
        }
        return -1;
    }
}
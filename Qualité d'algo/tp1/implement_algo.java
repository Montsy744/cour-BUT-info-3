public class implement_algo {

    static class OperationCounter {

        private long count = 0;

        public void tick() {
            count++;
        }

        public long get() {
            return count;
        }

        public void reset() {
            count = 0;
        }
    }

    public final int algo3(
            final int[] tab,
            final OperationCounter counter) {
        int x = 0;
        counter.tick();
        for (int i = 0; i < tab.length - 1; i++) {
            counter.tick();
            x = x + tab[i];
            counter.tick();
        }
        return x;
    }

    public final int algo4(
            final int[] tab,
            final OperationCounter counter) {
        int x = 0;
        counter.tick();
        for (int i = 0; i < tab.length - 1; i++) {
            counter.tick();
            for (int j = 0; j < tab.length - 1; j++) {
                counter.tick();
                x++;
                counter.tick();
            }
        }

        return x;
    }

    public final int algo6(
            final int[] tab,
            final OperationCounter counter) {
        int x = 0;
        int i = tab.length;
        counter.tick();
        counter.tick();
        while (i > 0) {
            x++;
            counter.tick();
            i = i / 2;
            counter.tick();
        }
        return x;
    }

    public final int recherche_lineaire(
            final int[] tab,
            final int val,
            final OperationCounter counter) {
        int i = 0;
        counter.tick();
        while (i < tab.length && tab[i] != val) {
            counter.tick();
            counter.tick();
            i++;
            counter.tick();
        }
        if (i < tab.length) {
            counter.tick();
            return i;
        }
        return -1;
    }

    public static void main(String args[]) {

    }
}
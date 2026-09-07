import java.util.Random;

public class implement_algo {

    static final Random RANDOM = new Random(42);
    static final int BOUND = 1_000_000;

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

    static final int[] randomArray(final int size, final int bound) {
        final int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(bound);
        }
        return array;
    }

    private static void warmupJVM(final implement_algo instance) {
        final OperationCounter dummyCounter = new OperationCounter();
        final int[] warmupTab = randomArray(10, BOUND);

        for (int i = 0; i < 10_000; i++) {
            instance.algo3(warmupTab, dummyCounter);
            instance.algo4(warmupTab, dummyCounter);
            instance.algo6(warmupTab, dummyCounter);
            instance.recherche_lineaire(warmupTab, 42, dummyCounter);
            dummyCounter.reset();
        }
        System.out.println("JVM chaude. Début des mesures.\n");
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
        final implement_algo instance = new implement_algo();
        final OperationCounter counter = new OperationCounter();

        warmupJVM(instance);

        final int[] TAILLES = {10, 100, 1000, 5000}; 
        final int NB_REPETITION = 30;

        System.out.printf("%-10s | %-20s | %-15s | %-15s%n", "Taille (N)", "Algorithme", "Temps moyen (ns)", "Opérations moy.");
        System.out.println("-----------------------------------------------------------------------------");

        for (int n : TAILLES) {
            long totalTimeAlgo3 = 0, totalOpsAlgo3 = 0;
            long totalTimeAlgo4 = 0, totalOpsAlgo4 = 0;
            long totalTimeAlgo6 = 0, totalOpsAlgo6 = 0;
            long totalTimeRech = 0, totalOpsRech = 0;

            for (int r = 0; r < NB_REPETITION; r++) {
                final int[] tab = randomArray(n, BOUND);
                final int valRecherche = RANDOM.nextInt(BOUND);

                counter.reset();
                long startTime = System.nanoTime();
                instance.algo3(tab, counter);
                long endTime = System.nanoTime();
                totalTimeAlgo3 += (endTime - startTime);
                totalOpsAlgo3 += counter.get();

                counter.reset();
                startTime = System.nanoTime();
                instance.algo4(tab, counter);
                endTime = System.nanoTime();
                totalTimeAlgo4 += (endTime - startTime);
                totalOpsAlgo4 += counter.get();

                counter.reset();
                startTime = System.nanoTime();
                instance.algo6(tab, counter);
                endTime = System.nanoTime();
                totalTimeAlgo6 += (endTime - startTime);
                totalOpsAlgo6 += counter.get();

                counter.reset();
                startTime = System.nanoTime();
                instance.recherche_lineaire(tab, valRecherche, counter);
                endTime = System.nanoTime();
                totalTimeRech += (endTime - startTime);
                totalOpsRech += counter.get();
            }

            System.out.printf("%-10d | %-20s | %-15.2f | %-15.2f%n", n, "algo3", (double)totalTimeAlgo3 / NB_REPETITION, (double)totalOpsAlgo3 / NB_REPETITION);
            System.out.printf("%-10d | %-20s | %-15.2f | %-15.2f%n", n, "algo4", (double)totalTimeAlgo4 / NB_REPETITION, (double)totalOpsAlgo4 / NB_REPETITION);
            System.out.printf("%-10d | %-20s | %-15.2f | %-15.2f%n", n, "algo6", (double)totalTimeAlgo6 / NB_REPETITION, (double)totalOpsAlgo6 / NB_REPETITION);
            System.out.printf("%-10d | %-20s | %-15.2f | %-15.2f%n", n, "recherche_lineaire", (double)totalTimeRech / NB_REPETITION, (double)totalOpsRech / NB_REPETITION);
            System.out.println("-----------------------------------------------------------------------------");
        }
    }
}
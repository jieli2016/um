package io.rulai.core.utils;

import java.util.Random;

public class Randomizer {
    static Random random = new Random(System.currentTimeMillis());

    public static Random getRandom() {
    	return random;
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }
    
    public static double nextDouble() {
        double rand = random.nextDouble();
        return rand;
    }
    
    /**
     * between 0.0 and 1.0
     */
    public static float nextFloat() {
        float rand = random.nextFloat();
        return rand;
    }

    public static int nextInt() {
        int rand = random.nextInt();
        return rand;
    }

    public static int nextInt(int max) {
    	// 0.02 micro seconds per call
        int rand = random.nextInt(max);
        return rand;
    }

    public static long nextLong() {
        long rand = random.nextLong();
        return rand;
    }

    public static int nextPositiveInt() {
        int i = random.nextInt();
        while (i <= 0) {
            i = random.nextInt();
        }
        return i;
    }
    /**
     * http://en.wikipedia.org/wiki/Knuth_shuffle
     */
    public static void shuffle(float[] array) {
        for (int n = array.length - 1; n > 0; n--) {
            int k = nextInt(n + 1);
            float temp = array[n];
            array[n] = array[k];
            array[k] = temp;
        }
    }

    /**
     * http://en.wikipedia.org/wiki/Knuth_shuffle
     */
    public static void shuffle(int[] array) {
        for (int n = array.length - 1; n > 0; n--) {
            int k = nextInt(n + 1);
            int temp = array[n];
            array[n] = array[k];
            array[k] = temp;
        }
    }

    /**
     * http://en.wikipedia.org/wiki/Knuth_shuffle
     */
    public static <K> void shuffle(K[] array) {
        for (int n = array.length - 1; n > 0; n--) {
            int k = nextInt(n + 1);
            K temp = array[n];
            array[n] = array[k];
            array[k] = temp;
        }
    }

}

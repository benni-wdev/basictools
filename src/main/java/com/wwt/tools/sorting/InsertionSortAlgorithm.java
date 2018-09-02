package com.wwt.tools.sorting;

public class InsertionSortAlgorithm implements SortAlgorithm {



    private static class InsertionSortAlgorithmHolder {
        private static final InsertionSortAlgorithm INSTANCE = new InsertionSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private InsertionSortAlgorithm() {
    }

    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static InsertionSortAlgorithm getInstance() {
        return InsertionSortAlgorithmHolder.INSTANCE;
    }



    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        for (int i = 1; i < array.length; i++) {
            E tmp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(tmp) > 0) {
                array[j + 1] = array[j];
                j -= 1;
            }
            array[j + 1] = tmp;
        }
    }

    @Override
    public String toString() {
        return "InsertionSortAlgorithm{}";
    }
}

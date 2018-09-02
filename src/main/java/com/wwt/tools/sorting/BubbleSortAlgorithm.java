package com.wwt.tools.sorting;

import static com.wwt.tools.sorting.Sorting.swap;

/**
 * @author benw-at-wwt
 */
public class BubbleSortAlgorithm implements SortAlgorithm {

    private static class BubbleSortAlgorithmHolder {
        private static final BubbleSortAlgorithm INSTANCE = new BubbleSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private  BubbleSortAlgorithm() {}
    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static BubbleSortAlgorithm getInstance() { return BubbleSortAlgorithmHolder.INSTANCE; }


    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        for (int i = array.length-1; i >= 0;i -- ) {
            for (int j = 0; j < i; j++) {
                if(array[j].compareTo(array[i]) > 0) {
                    swap(array,i,j);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "BubbleSortAlgorithm{}";
    }


}

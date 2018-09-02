package com.wwt.tools.sorting;

import static com.wwt.tools.sorting.Sorting.swap;
/**
 *
 * @author benw-at-wwt
 */
public class QuickSortAlgorithm implements SortAlgorithm {

    private static class QuickSortAlgorithmHolder {
        private static final QuickSortAlgorithm INSTANCE = new QuickSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private  QuickSortAlgorithm() {}


    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }

    @Override
    public String toString() {
        return "QuickSortAlgorithm{}";
    }

    private <E extends Comparable<E>>  void sort(E[] array, int low, int high) {
        if(low < high) {
            int splitIndex = partition(array,low,high);
            sort(array, low, splitIndex-1);
            sort(array, splitIndex+1, high);
        }

    }

    private <E extends Comparable<E>> int partition(E[] array, int low, int high) {
        E pivot = array[high];
        int returnValue = low;

        for (int j = low; j <= high- 1; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                swap(array,returnValue,j);
                returnValue++;
            }
        }
        swap(array,returnValue,high);
        return returnValue;
    }

    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static QuickSortAlgorithm getInstance() { return QuickSortAlgorithmHolder.INSTANCE; }
}


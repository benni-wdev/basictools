package com.wwt.tools.sorting;


import static com.wwt.tools.sorting.Sorting.swap;


/**
 * @author benw-at-wwt
 */
public class HeapSortAlgorithm implements SortAlgorithm {

    private static class HeapSortAlgorithmHolder {
        private static final HeapSortAlgorithm INSTANCE = new HeapSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private HeapSortAlgorithm() {
    }
    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static HeapSortAlgorithm getInstance() {
        return HeapSortAlgorithmHolder.INSTANCE;
    }


    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        for (int i = array.length - 1; i >= 0; i--)
            reHeap(array, array.length, i);

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, i, 0);
            reHeap(array, i, 0);
        }
    }

    @Override
    public String toString() {
        return "HeapSortAlgorithm{}";
    }

    private <E extends Comparable<E>> void reHeap(E[] array, int length, int i) {
        boolean done = false;
        E tmp = array[i];
        int parent = i;
        int child = 2 * (i + 1) - 1;
        while ((child < length) && (!done)) {
            if (child < length - 1) {
                if (array[child].compareTo(array[child + 1]) < 0) {
                    child += 1;
                }
            }
            if (tmp.compareTo(array[child]) >= 0) {
                done = true;
            }
            else {
                array[parent] = array[child];
                parent = child;
                child = 2 * (parent + 1) - 1;
            }
        }
        array[parent] = tmp;
    }




}





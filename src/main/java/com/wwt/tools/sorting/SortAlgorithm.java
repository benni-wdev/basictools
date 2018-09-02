package com.wwt.tools.sorting;



/**
 *
 * @author benw-at-wwt
 */
public interface SortAlgorithm {

    /**
     *
     * @return
     */
    <E extends Comparable<E>> void sort(E[] array);
}

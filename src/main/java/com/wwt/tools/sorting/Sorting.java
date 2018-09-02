package com.wwt.tools.sorting;

/**
 * @author benw-at-wwt
 */
public final class Sorting {

    private Sorting() {}

    public static <E> void swap(E[] array, int i, int j) {
        E element = array[i];
        array[i]  = array[j];
        array[j]  = element;
    }
}

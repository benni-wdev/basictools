package com.wwt.tools.sorting;

import org.junit.Test;

/**
 * @author benw-at-wwt
 */
public class HeapSortAlgorithmTest extends BaseTest {

    @Test
    public void heapAlgorithmTest() {
        this.sortTestCases(HeapSortAlgorithm.getInstance());
        this.assertArrays();
        printArrays();
    }
}

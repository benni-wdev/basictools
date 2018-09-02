package com.wwt.tools.sorting;

import org.junit.Test;

/**
 * @author benw-at-wwt
 */
public class QuickSortAlgorithmTest extends BaseTest {


    @Test
    public void quickSortAlgorithmTest() {
        this.sortTestCases(QuickSortAlgorithm.getInstance());
        this.assertArrays();
        printArrays();
    }
}

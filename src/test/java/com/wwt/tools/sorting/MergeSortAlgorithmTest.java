package com.wwt.tools.sorting;

import org.junit.Test;

/**
 * @author benw-at-wwt
 */
public class MergeSortAlgorithmTest extends BaseTest {

    @Test
    public void mergeSortAlgorithmTest() {
        this.sortTestCases(MergeSortAlgorithm.getInstance());
        this.assertArrays();
        printArrays();
        //assertTrue(false);
    }
}

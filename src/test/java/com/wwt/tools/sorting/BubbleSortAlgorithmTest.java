package com.wwt.tools.sorting;

import org.junit.Test;

/**
 * @author benw-at-wwt
 */
public class BubbleSortAlgorithmTest extends BaseTest {

    @Test
    public void bubbleSortAlgorithmTest() {
        this.sortTestCases(BubbleSortAlgorithm.getInstance());
        this.assertArrays();
        printArrays();
    }
}

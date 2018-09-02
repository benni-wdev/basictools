package com.wwt.tools.sorting;

import org.junit.Test;

/**
 * @author benw-at-wwt
 */
public class InsertionSortAlgorithmTest extends BaseTest {

    @Test
    public void insertionSortAlgorithmTest() {
        this.sortTestCases(InsertionSortAlgorithm.getInstance());
        this.assertArrays();
    }
}

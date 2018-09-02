package com.wwt.tools.sorting;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author benw-at-wwt
 */
public class SortingTest {


    @Test
    public void swapTest() {
        BaseTest.TestElement [] arr = new BaseTest.TestElement[2];
        arr[1] = new BaseTest.TestElement(1);
        arr[0] = new BaseTest.TestElement(0);
        Sorting.swap(arr,0,1);
        assertEquals("1",arr[0].toString());
        assertEquals("0",arr[1].toString());
    }
}

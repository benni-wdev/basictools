/* Copyright 2018-2019 Wehe Web Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wwt.tools.basetools.sort;

import com.wwt.tools.basetools.common.BaseTest;
import org.junit.Ignore;

import static org.junit.Assert.*;

/**
 * @author benw-at-wwt
 */
@SuppressWarnings({"unused","WeakerAccess"})
@Ignore
public class BaseSortingTest extends BaseTest {


    private final TestElement[][] testCases;

    public BaseSortingTest() {
        testCases = new TestElement[5][];
        testCases[0] = createAscTestElementArray(10);
        testCases[1] = createConstantTestElementArray(10);
        testCases[2] = createDescTestElementArray(10);
        testCases[3] = createRandomTestElementArray(10);
        testCases[4] = createRandomTestElementArray(1);

    }

    public void sortTestCases(SortAlgorithm s) {
        for (TestElement[] testCase : testCases) {
            s.sort( testCase );
        }
    }

    public void printArrays() {
        for (int i = 0; i < testCases.length; i++) {
            printArray("testCases["+i+"]=",testCases[i]);
        }
    }

    public void assertArrays() {
        for (TestElement[] testCase : testCases) {
            assertAscFull( testCase );
        }
    }

    <E extends Comparable<E>> void assertAscFull(E[] arr) {
        for(int i = 0;i<arr.length-1;i++) {
            assertTrue((arr[i].compareTo(arr[i+1])) <= 0);
        }
    }

    <E extends Comparable<E>> void assertDscFull(E[] arr) {
        for(int i = 0;i<arr.length-1;i++) {
            assertTrue((arr[i].compareTo(arr[i+1])) >= 0);
        }
    }


    static <E extends Comparable<E>> void printArray(String identifier,E[] arr) {
        StringBuilder s = new StringBuilder(identifier);
        s.append("[");
        for (E e : arr) {
            s.append( e ).append( "," );
        }
        s.append("]");
        System.out.println(s.toString());
    }




}

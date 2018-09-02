package com.wwt.tools.sorting;


import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * @author benw-at-wwt
 */
@Ignore
public class BaseTest {


    static class TestElement implements Comparable<TestElement> {

        private int integer;

        TestElement(int i) {
            integer = i;
        }

        @Override
        public int compareTo(TestElement o) {
            if(integer==o.getInteger()) return 0;
            return integer<o.getInteger()? -1:1;
        }

        private int getInteger() {return integer;}

        @Override
        public String toString(){ return Integer.toString(integer);}
    }


    Comparable[][] testCases;

    BaseTest() {
        testCases = new Comparable[5][];
        testCases[0] = createAscTestElementArray(10);
        testCases[1] = createConstantTestElementArray(10);
        testCases[2] = createDescTestElementArray(10);
        testCases[3] = createRandomTestElementArray(10);
        testCases[4] = createRandomTestElementArray(1);

    }

    void sortTestCases(SortAlgorithm s) {
        for (int i = 0; i < testCases.length; i++) {
            s.sort(testCases[i]);
        }
    }

    void printArrays() {
        for (int i = 0; i < testCases.length; i++) {
            printArray("testCases["+i+"]=",testCases[i]);
        }
    }

    void assertArrays() {
        for (int i = 0; i < testCases.length; i++) {
            assertAscFull(testCases[i]);
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
        for(int i = 0;i<arr.length;i++) {
           s.append(arr[i]+",");
        }
        s.append("]");
        System.out.println(s.toString());
    }


    /* ------- Generate Testdata ------------*/

    static TestElement[] createRandomTestElementArray(int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement((ThreadLocalRandom.current().nextInt()));
        }
        return returnValue;
    }

    static TestElement[] createAscTestElementArray(int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement(i);
        }
        return returnValue;
    }

    static TestElement[] createDescTestElementArray(int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement(size-i);
        }
        return returnValue;
    }

    static TestElement[] createConstantTestElementArray(int size) {
        TestElement [] returnValue = new TestElement[size];
        for(int i = 0;i<returnValue.length;i++) {
            returnValue[i] = new TestElement(1);
        }
        return returnValue;
    }

}

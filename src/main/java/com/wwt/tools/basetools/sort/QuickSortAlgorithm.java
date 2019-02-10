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

import com.wwt.tools.basetools.common.GenericArrayHelper;

/**
 *
 * @author benw-at-wwt
 */
public class QuickSortAlgorithm implements SortAlgorithm {

    private static class QuickSortAlgorithmHolder {
        private static final QuickSortAlgorithm INSTANCE = new QuickSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private  QuickSortAlgorithm() {}


    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length - 1);
    }

    @Override
    public String toString() {
        return "QuickSortAlgorithm{}";
    }

    private <E extends Comparable<E>>  void sort(E[] array, int low, int high) {
        if(low < high) {
            int splitIndex = partition(array,low,high);
            sort(array, low, splitIndex-1);
            sort(array, splitIndex+1, high);
        }

    }

    private <E extends Comparable<E>> int partition(E[] array, int low, int high) {
        E pivot = array[high];
        int returnValue = low;

        for (int j = low; j <= high- 1; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                GenericArrayHelper.swap(array,returnValue,j);
                returnValue++;
            }
        }
        GenericArrayHelper.swap(array,returnValue,high);
        return returnValue;
    }

    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static QuickSortAlgorithm getInstance() { return QuickSortAlgorithmHolder.INSTANCE; }
}


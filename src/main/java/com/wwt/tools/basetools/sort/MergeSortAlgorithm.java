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

import java.util.Arrays;

class MergeSortAlgorithm implements SortAlgorithm {


    private static class MergeSortAlgorithmHolder {
        private static final MergeSortAlgorithm INSTANCE = new MergeSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private MergeSortAlgorithm() {
    }
    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static MergeSortAlgorithm getInstance() {
        return MergeSortAlgorithmHolder.INSTANCE;
    }


    @Override
    public <E extends Comparable<E>> void sort(E[] array) {

        sort(array, 0, array.length - 1);
    }

    @Override
    public String toString() {
        return "MergeSortAlgorithm{}";
    }

    private <E extends Comparable<E>> void sort(E[] array, int low, int high)  {
        if(low < high) {
            int pivot = (low + high) / 2;
            sort(array, low, pivot);
            sort(array, pivot + 1, high);
            merge(array,low,pivot,high);
        }
    }

    private <E extends Comparable<E>> void merge(E[] array, int low, int pivot, int high) {
        int length = high - low + 1;
        E [] working = Arrays.copyOfRange(array,low,high+1);

        int m1 = 0;
        int m2 = pivot - low + 1;
        for (int i = 0; i < length; i++) {
            if (m2 <= high - low) {
                if (m1 <= pivot - low) {
                    if (working[m1].compareTo(working[m2]) > 0) {
                        array[i + low] = working[m2++];
                    }
                    else {
                        array[i + low] = working[m1++];
                    }
                }
                else {
                    array[i + low] = working[m2++];
                }
            }
            else {
                array[i + low] = working[m1++];
            }
        }
    }


}







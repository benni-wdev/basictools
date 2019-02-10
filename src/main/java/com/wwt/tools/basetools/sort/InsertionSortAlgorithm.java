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

public class InsertionSortAlgorithm implements SortAlgorithm {



    private static class InsertionSortAlgorithmHolder {
        private static final InsertionSortAlgorithm INSTANCE = new InsertionSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private InsertionSortAlgorithm() {
    }

    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static InsertionSortAlgorithm getInstance() {
        return InsertionSortAlgorithmHolder.INSTANCE;
    }



    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        for (int i = 1; i < array.length; i++) {
            E tmp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(tmp) > 0) {
                array[j + 1] = array[j];
                j -= 1;
            }
            array[j + 1] = tmp;
        }
    }

    @Override
    public String toString() {
        return "InsertionSortAlgorithm{}";
    }
}

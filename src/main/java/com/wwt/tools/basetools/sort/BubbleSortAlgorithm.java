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
 * @author benw-at-wwt
 */
public class BubbleSortAlgorithm implements SortAlgorithm {

    private static class BubbleSortAlgorithmHolder {
        private static final BubbleSortAlgorithm INSTANCE = new BubbleSortAlgorithm();
    }

    /**
     * not instantiatable -> singleton because object is stateless
     */
    private  BubbleSortAlgorithm() {}
    /**
     * Singleton instance
     *
     * @return the one and only instance
     */
    public static BubbleSortAlgorithm getInstance() { return BubbleSortAlgorithmHolder.INSTANCE; }


    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        for (int i = array.length-1; i >= 0;i -- ) {
            for (int j = 0; j < i; j++) {
                if(array[j].compareTo(array[i]) > 0) {
                    GenericArrayHelper.swap(array,i,j);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "BubbleSortAlgorithm{}";
    }


}

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
package com.wwt.tools.basetools.common;

/**
 * Helper class which contains some static methods which are used in various places within the project
 *
 * @author benw-at-wwt
 */
public final class GenericArrayHelper {

    /**
     * not instantiatable
     */
    private GenericArrayHelper() {}


    /**
     * the well known swap of 2 elements for the given array for positions i and j
     * @param array the array
     * @param i first index
     * @param j second index
     */
    public static void swap(Object[] array, int i, int j) {
        if(i>= array.length || i < 0) throw new ArrayIndexOutOfBoundsException(i);
        if(j>= array.length || j < 0) throw new ArrayIndexOutOfBoundsException(j);
        if(i==j) return;
        Object element = array[i];
        array[i]  = array[j];
        array[j]  = element;
    }

    /**
     * creates a new array with increased capacity and copies all elements of the given array into the new one at
     * the same position or returns the same array object if capacity is already >= the requested capacity.
     * Please note that if capacity is increased the method increases it at least twice the current capacity.
     *
     * @param array the array for which the capacity should be increased
     * @param requestedCapacity the requested capacity of the array
     * @return the same array object if current capacity is enough or a new array with increased capacity
     */
    public static Object[] guaranteeCapacity(Object[] array, int requestedCapacity) {
        int currentCapacity = array.length;
        if(currentCapacity < requestedCapacity) {
            Object[] newArray = new Object[Math.max(requestedCapacity,currentCapacity*2)];
            System.arraycopy( array,0,newArray,0,currentCapacity);
            return newArray;
        }
        else {
            return array;
        }
    }
}

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
package com.wwt.tools.basetools.heap;

import java.util.Arrays;
import java.util.Collection;

import static com.wwt.tools.basetools.common.GenericArrayHelper.*;

/**
 * Implementation of a minimum heap with a binary heap.
 *
 * @author benw-at-wwt
 */
public class BinaryHeap<E extends Comparable<E>> implements MinHeap<E> {

    /**
     * The underlying array holding the elements
     */
    private E[] array;
    /**
     * the actual size of the heap
     */
    private int size;

    /**
     * Constructs an empty heap with an underlying array of the given capacity
     * @param initialCapacity the initial size of the array
     */
    public BinaryHeap(int initialCapacity) {
        //noinspection unchecked
        array = (E[])new Comparable[initialCapacity];
        size  = 0;
    }

    /**
     * Constructs an heap containing all the elements of the given collection with underlying array size equals to the size of the collection
     * @param initialContent A collection of initial heap elements
     */
    @SuppressWarnings("WeakerAccess")
    public BinaryHeap(Collection<E> initialContent) {
        initWithCollection(initialContent);
    }


    @Override
    public void insert(E element) {
        if(element == null) throw new IllegalArgumentException("cannot insert null");
        int newSize = size+1;
        //noinspection unchecked
        array = (E[]) guaranteeCapacity(array,newSize);
        array[size] = element;
        minHeapifyBottomUp(size);
        size = newSize;
    }

    @Override
    public void remove(E element) {
        int i = getElementIndex(element);
        if(i < size) {
            removeElementAtIndex( i );
        }
    }

    @Override
    public E extractMin() {
        if(size == 0) return null;
        E returnValue = array[0];
        removeElementAtIndex(0);
        return returnValue;
    }

    @Override
    public E getMin() {
        if(size == 0) return null;
        return array[0];
    }


    @Override
    public void decreaseKey(E element) {
        int i = getElementIndex( element );
        if(i < size) minHeapifyBottomUp(i);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        Arrays.fill(array, null);
    }

    @Override
    public Collection<E> toCollection() {
        return Arrays.asList(array);
    }

    @Override
    public void initWithCollection(Collection<E> initElements) {
        //noinspection unchecked
        array = (E[]) new Comparable[initElements.size()];
        size  = 0;
        for(E e:initElements) {
            array[size] = e;
            size++;
        }
        for(int i = size/2;i>=0;i--) {
            minHeapifyTopDown( i );
        }
    }

    @Override
    public String toString() {
        return "BinaryHeap{" +
                "array=" + Arrays.toString( array ) +
                ", size=" + size +
                '}';
    }

    /**
     * constructs printable formatted String of the binary heap (for sys print)
     */
    @SuppressWarnings("WeakerAccess")
    public String dump() {
        StringBuilder sb = new StringBuilder();
        int max = (int)(Math.log(size)/Math.log(2))+1;
        int maxLength = 0;
        int positions = (int)Math.pow( 2,max-1);
        for(int i = 0; i< size;i++){
            if(array[i].toString().length() > maxLength) maxLength = array[i].toString().length();
        }
        maxLength++;
        int counter = 0;
        for(int i=0;i< (Math.log(size)/Math.log(2))+1;i++){
            int elements = (int) Math.pow( 2,i );
            int free = Math.max((positions/(elements+1))+1,1);
            int elementsWritten = 0;
            for(int j=1;j<=positions&&counter < size&&elementsWritten<elements;j++){
                if(j%free == 0) {
                    for(int l=0;l<maxLength - array[counter].toString().length();l++) sb.append(" ");
                    sb.append(array[counter]);
                    counter++;
                    elementsWritten++;
                }
                else {
                    for(int l=0;l<maxLength;l++) sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    E[] getArray() {
        return array;
    }

    /**
     * Removes an Element out of the Heap at array index i and rearranges the heap to met heap condition
     * @param i the index of the element in the underlying array
     */
    private void removeElementAtIndex(int i) {
        swap( array,i,size-1);
        E tmp = array[size-1];
        array[size-1] = null;
        size--;
        if(size>1) {
            if (tmp.compareTo( array[i] ) < 0)
                minHeapifyTopDown( i );
            else if (tmp.compareTo( array[i] ) > 0)
                minHeapifyBottomUp( i );
        }
    }

    /**
     * rearranges the heap to met heap condition from the given index down
     * @param i the index of the element in the underlying array from the element to start with
     */
    private void minHeapifyTopDown(int i) {
        int leftChildIndex = getLeftChildIndex(i);
        int rightChildIndex = getRightChildIndex(i);
        int min = i;
        if(leftChildIndex < size && array[leftChildIndex].compareTo(array[min] ) < 0 )
            min = leftChildIndex;
        if(rightChildIndex < size && array[rightChildIndex].compareTo(array[min] ) < 0 )
            min = rightChildIndex;
        if(min != i) {
            swap(array,min,i);
            minHeapifyTopDown(min);
        }
    }

    /**
     * rearranges the heap to met heap condition from the given index upwards
     * @param i the index of the element in the underlying array from the element to start with
     */
    private void minHeapifyBottomUp(int i) {
        int parentIndex = getParentIndex(i);
        if(i == parentIndex) return;
        if(array[parentIndex].compareTo(array[i] ) > 0 ) {
            swap(array,parentIndex,i);
            minHeapifyBottomUp(parentIndex);
        }
    }

    /**
     * searches for the given element within the array and returns the index of that element or -1
     * @param element to search for
     * @return -1 if the element is not in the heap or the index of the element within the underlying array
     */
    private int getElementIndex(E element) {
        int returnIndex = -1;
        for(int i = 0; i < size; i++) {
            if(array[i].equals( element )) {
                returnIndex = i;
                break;
            }
        }
        return returnIndex;
    }

    /**
     * calculates the left child index for the given input index
     * @param i parent index
     * @return left child index
     */
    private int getLeftChildIndex(int i) {
        return 2*i+1;
    }

    /**
     * calculates the right child index for the given input index
     * @param i parent index
     * @return right child index
     */
    private int getRightChildIndex(int i) {
        return 2*i+2;
    }

    /**
     * calculates the parent index for the given input index
     * @param i any child index
     * @return parent index
     */
    private int getParentIndex(int i) {
        return (i-1)/2;
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
    private Comparable[] guaranteeCapacity(Comparable[] array, int requestedCapacity) {
        int currentCapacity = array.length;
        if(currentCapacity < requestedCapacity) {
            Comparable[] newArray = new Comparable[Math.max(requestedCapacity,currentCapacity*2)];
            System.arraycopy( array,0,newArray,0,currentCapacity);
            return newArray;
        }
        else {
            return array;
        }
    }


}

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

import java.util.Collection;

/**
 * Interface implemented by Minimum Heaps
 *
 * @author benw-at-wwt
 */
public interface MinHeap<T extends Comparable<T>> {

    /**
     * Inserts a new element into the heap and guarantees heap conditions on return.
     * Runtime depends on the implementation
     * @param element to insert
     */
    void insert(T element);

    /**
     * Removes the given element from the heap and guarantees heap conditions on return
     * @param element to remove
     */
    void remove(T element);

    /**
     * Returns the minimal element from the heap and removes it from the heap
     * Runtime is always O(log(size))
     * @return the minimal element according to the compareTo implementation
     */
    T extractMin();

    /**
     * Returns the minimal element from the heap but does not remove it
     * Runtime is O(1)
     * @return the minimal element according to the compareTo implementation
     */
    T getMin();

    /**
     * Guarantees heap conditions on return if the element in input has a decreased position in the order of the elements
     * Runtime depends on the implementation
     * @param element element which is considered as starting point to heapify the heap again
     */
    void decreaseKey(T element);

    /**
     * Returns the current size of the heap
     * @return size value
     */
    int size();

    /**
     * Check if heap is empty
     * @return true if heap is empty, otherwise false
     */
    boolean isEmpty();

    /**
     * Empties the heap
     */
    void clear();

    /**
     * Returns the current content of the Heap as java.util.Collection
     *
     * @return the collection
     */
    Collection<T> toCollection();

    /**
     * clears the heap and initialises the heap with the elements in the collection. The heap conditions are guaranteed on completion.
     * @param initElements the elements to insert
     */
    void initWithCollection(Collection<T> initElements);


}

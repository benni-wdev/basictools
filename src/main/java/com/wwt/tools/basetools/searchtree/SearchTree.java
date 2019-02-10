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
package com.wwt.tools.basetools.searchtree;

import java.util.Optional;

/**
 *
 *
 *
 * @author benw-at-wwt
 */
public interface SearchTree<T extends Comparable<T>> {

    /**
     * Inserts a new element into the tree.
     * Runtime depends on the implementation
     * @param element to insert
     */
    void insert(T element);

    /**
     * Removes the given element from the tree
     * @param element to remove
     */
    @SuppressWarnings("unused")
    Optional<T> remove(T element);

    /**
     * Finds the element and returns it if present, otherwise the method returns null
     * @param element element to search for (important is just the compareTo Method)
     * @return the element stored in the Tree with element.compareTo(inputElement) == 0
     */
    Optional<T> find(T element);

    /**
     * Empties the tree
     */
    void clear();
}

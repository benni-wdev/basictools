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

/**
 * @author benw-at-wwt
 */
class Node<E> {

    Node<E> parent;
    Node<E> leftChild;
    Node<E> rightChild;
    E element;


    void setParent(Node<E> parent) {
        this.parent = parent;
    }

    /**
     * change the left child of the node, automatically adjusts the parent of the child
     *
     * @param leftChild new left child
     */
    void setLeftChild(Node<E> leftChild) {
        if (leftChild != null) leftChild.setParent( this );
        this.leftChild = leftChild;
    }

    /**
     * change the right child of the node, automatically adjusts the parent of the child
     *
     * @param rightChild new right child
     */
    void setRightChild(Node<E> rightChild) {
        if (rightChild != null) rightChild.setParent( this );
        this.rightChild = rightChild;
    }

    void setElement(E element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return element.toString();
    }


}

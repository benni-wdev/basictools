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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author benw-at-wwt
 */
public class BinarySearchTree <E extends Comparable<E>> implements SearchTree<E> {

    private Node<E> root;

    /**
     * constructs an empty binary search tree
     */
    @SuppressWarnings("WeakerAccess")
    public BinarySearchTree() {
        root = null;
    }

    @Override
    public void insert(E element) {
        if(element == null) return;
        if(root == null) {
            root = new Node<>();
            root.setElement( element );
        }
        else {
            insertElement(root,element);
        }
    }

    @Override
    public Optional<E> remove(E element) {
        Node<E> nodeToRemove = findNode( root,element );
        if(nodeToRemove != null) {
            // leaf
            if(nodeToRemove.leftChild == null && nodeToRemove.rightChild == null) {
                if(nodeToRemove.parent.leftChild.equals( nodeToRemove )) {
                    nodeToRemove.parent.setLeftChild(null);
                    nodeToRemove.setParent(null);
                }
                else {
                    nodeToRemove.parent.setRightChild(null);
                    nodeToRemove.setParent(null);
                }
            }
            //  o
            //   \
            else if(nodeToRemove.leftChild == null ) {
                if(nodeToRemove.parent.leftChild.equals( nodeToRemove )) {
                    nodeToRemove.parent.setLeftChild( nodeToRemove.rightChild);
                }
                else {
                    nodeToRemove.parent.setRightChild( nodeToRemove.rightChild);
                }

            }
            //  o
            // /
            else if(nodeToRemove.rightChild == null ) {
                if(nodeToRemove.parent.leftChild.equals( nodeToRemove )) {
                    nodeToRemove.parent.setLeftChild( nodeToRemove.leftChild);
                }
                else {
                    nodeToRemove.parent.setRightChild( nodeToRemove.leftChild);
                }
            }
            //  o
            // / \
            else {
                Node<E> minimumRight = findMinimum( nodeToRemove.rightChild );
                minimumRight.parent.setLeftChild( minimumRight.rightChild);
                minimumRight.setLeftChild( nodeToRemove.leftChild );
                minimumRight.setRightChild( nodeToRemove.rightChild );
                if(nodeToRemove.parent.leftChild.equals( nodeToRemove )) {
                    nodeToRemove.parent.setLeftChild( minimumRight);
                }
                else {
                    nodeToRemove.parent.setRightChild( minimumRight);
                }
            }
            return Optional.ofNullable(element);
        }
        else return Optional.empty();
    }



    @Override
    public Optional<E> find(E element) {
        Node<E> foundNode = findNode( root,element );
        if(foundNode == null) return Optional.empty();
        else return Optional.ofNullable(foundNode.element);
    }


    @Override
    public void clear() {
        root = null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(  );
        toString( sb,root );
        return sb.toString();
    }




    /**
     * For unit testing -> get the root Node to check tree structure
     * @return the root node object
     */
    Node<E> getRoot() {
        return root;
    }


    /**
     * Recursively searches top down through the tree to find a Node where the contained E object.compareTo(element) is 0 and returns that node
     * if the search reaches a null child it returns null
     *
     * @param node the node from where the search should start
     * @param element the element to search for
     * @return null if the element is not in the tree or the Node which holds the element
     */
    private Node<E> findNode(Node<E> node,E element) {
        if(node.element.compareTo( element ) > 0) {
            if(node.leftChild == null) return null;
            else return findNode( node.leftChild,element );
        }
        else if(node.element.compareTo( element ) < 0) {
            if(node.rightChild == null) return null;
            else return findNode( node.rightChild,element );
        }
        else return node;
    }

    /**
     * inserts the given element as left or right child of the given node (if condition is met and child is null)
     * or recursively calls on the left or right child. Note that elements with compareTo <= 0 are going to the left part.
     * @param node the node to use as basis as for insertion
     * @param element the element to insert.
     */
    private void insertElement(Node<E> node,E element ) {
        if(node.element.compareTo( element ) >= 0) {
            if(node.leftChild == null) {
                node.setLeftChild( createNewNode(element) );
            }
            else {
                insertElement( node.leftChild,element );
            }
        }
        else {
            if(node.rightChild == null) {
                node.setRightChild( createNewNode(element) );
            }
            else {
                insertElement( node.rightChild,element );
            }
        }
    }

    /**
     * creates a new Node containing the given element
     * @param element the element the node should contain
     * @return the new Node object
     */
    private Node<E> createNewNode(E element) {
        Node<E> newNode = new Node<>();
        newNode.setElement( element );
        return newNode;
    }

    /**
     * go to the most "left" node from the input node (the subtree minimum)
     *
     * @param node node to start with
     * @return the minimal node in the subtree
     */
    private Node<E> findMinimum(Node<E> node) {
        Node<E> current = node;
        while(current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    /**
     * Recursive toString constructor
     * @param sb StringBuffer contains constructed String
     * @param n the node for which information should be appended
     */
    private void toString(StringBuffer sb, Node n) {
        if(n == null) return;
        sb.append( "Node:" ).append( n ).append( " left:" ).append( n.leftChild ).append( " right:" ).append( n.rightChild ).append( "\n" );
        toString(sb,n.leftChild);
        toString(sb,n.rightChild);
    }
    @SuppressWarnings("unused")
    public String dump() {
        int maxLevel = maxLevel(root);
        StringBuffer sb = new StringBuffer();
        dumpNode(sb, Collections.singletonList(root), 1, maxLevel);
        return sb.toString();
    }


    private void dumpNode(StringBuffer sb,List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        for (int k = 0; k < firstSpaces; k++) sb.append(" ");

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                sb.append(node.element);
                newNodes.add(node.leftChild);
                newNodes.add(node.rightChild);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                sb.append(" ");
            }
            for (int k = 0; k < betweenSpaces; k++) sb.append(" ");
        }
        sb.append("\n");

        for (int i = 1; i <= edgeLines; i++) {
            for (Node node : nodes) {
                for (int k = 0; k < firstSpaces - i; k++) sb.append(" ");
                if (node == null) {
                    for (int k = 0; k < edgeLines + edgeLines + i + 1; k++) sb.append(" ");
                    continue;
                }

                if (node.leftChild != null)
                    sb.append("/");
                else
                    sb.append(" ");

                for (int k = 0; k < i + i - 1; k++) sb.append(" ");

                if (node.rightChild != null)
                    sb.append("\\");
                else
                    sb.append(" ");
                for (int k = 0; k < edgeLines + edgeLines - i; k++) sb.append(" ");
            }
            sb.append("\n");
        }
        dumpNode(sb,newNodes, level + 1, maxLevel);
    }

    private int maxLevel(Node node) {
        if (node == null)
            return 0;
        return Math.max(maxLevel(node.leftChild), maxLevel(node.rightChild)) + 1;
    }

    private boolean isAllElementsNull(List list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

 }

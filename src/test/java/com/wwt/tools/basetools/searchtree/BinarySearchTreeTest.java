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

import com.wwt.tools.basetools.common.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author benw-at-wwt
 */
public class BinarySearchTreeTest extends BaseTest {

    @Test
    public void insertTest() {
        TestElement [] test = new TestElement[7];
        test[0] = new TestElement(4);
        test[1] = new TestElement(2);
        test[2] = new TestElement(6);
        test[3] = new TestElement(5);
        test[4] = new TestElement(3);
        test[5] = new TestElement(1);
        test[6] = new TestElement(7);
        BinarySearchTree<TestElement> sTree = new BinarySearchTree<>();
        for(TestElement e:test) {
            sTree.insert( e );
        }
        Node n = sTree.getRoot();
        assertTrue( parentChildCheck(n) );
        assertEquals(new TestElement(4),n.element);
        assertEquals(new TestElement(2),n.leftChild.element);
        assertEquals(new TestElement(6),n.rightChild.element);
        assertEquals(new TestElement(5),n.rightChild.leftChild.element);
        assertEquals(new TestElement(3),n.leftChild.rightChild.element);
        assertEquals(new TestElement(1),n.leftChild.leftChild.element);
        assertEquals(new TestElement(7),n.rightChild.rightChild.element);
        sTree.insert(new TestElement(4));
        assertEquals(new TestElement(4),n.leftChild.rightChild.rightChild.element);
        sTree.insert(new TestElement(4));
        assertEquals(new TestElement(4),n.leftChild.rightChild.rightChild.leftChild.element);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void findTest() {
        TestElement [] test = new TestElement[7];
        test[0] = new TestElement(4);
        test[1] = new TestElement(2);
        test[2] = new TestElement(6);
        test[3] = new TestElement(5);
        test[4] = new TestElement(3);
        test[5] = new TestElement(1);
        test[6] = new TestElement(7);
        SearchTree<TestElement> sTree = new BinarySearchTree<>();
        for(TestElement e:test) {
            sTree.insert( e );
        }
        assertEquals(new TestElement(4),sTree.find(new TestElement( 4 )).get());
        assertEquals(new TestElement(2),sTree.find(new TestElement( 2 )).get());
        assertEquals(new TestElement(6),sTree.find(new TestElement( 6 )).get());
        assertEquals(new TestElement(5),sTree.find(new TestElement( 5 )).get());
        assertEquals(new TestElement(3),sTree.find(new TestElement( 3 )).get());
        assertEquals(new TestElement(1),sTree.find(new TestElement( 1 )).get());
        assertFalse(sTree.find(new TestElement(8 )).isPresent());
        assertFalse(sTree.find(new TestElement(0 )).isPresent());
    }

    @Test
    public void removeTest() {
        TestElement [] test = new TestElement[7];
        test[0] = new TestElement(40);
        test[1] = new TestElement(20);
        test[2] = new TestElement(60);
        test[3] = new TestElement(50);
        test[4] = new TestElement(30);
        test[5] = new TestElement(10);
        test[6] = new TestElement(70);
        BinarySearchTree<TestElement> sTree = new BinarySearchTree<>();
        for(TestElement e:test) {
            sTree.insert( e );
        }
        Node n = sTree.getRoot();
        sTree.remove( new TestElement(10));
        assertTrue(parentChildCheck(n));
        //System.out.println(sTree.dump());
        assertEquals(new TestElement(40),n.element);
        assertEquals(new TestElement(20),n.leftChild.element);
        assertEquals(new TestElement(60),n.rightChild.element);
        assertEquals(new TestElement(50),n.rightChild.leftChild.element);
        assertEquals(new TestElement(30),n.leftChild.rightChild.element);
        assertNull(n.leftChild.leftChild);
        //noinspection OptionalGetWithoutIsPresent
        assertEquals(new TestElement(70),sTree.remove(new TestElement(70)).get());
        assertTrue(parentChildCheck(n));
        //System.out.println(((BinarySearchTree)sTree).dump());
        assertEquals(new TestElement(40),n.element);
        assertEquals(new TestElement(20),n.leftChild.element);
        assertEquals(new TestElement(60),n.rightChild.element);
        assertEquals(new TestElement(50),n.rightChild.leftChild.element);
        assertEquals(new TestElement(30),n.leftChild.rightChild.element);
        assertNull(n.rightChild.rightChild);
        //System.out.println(sTree.dump());
        sTree.insert(new TestElement(70));
        sTree.remove(new TestElement(20));
        assertTrue(parentChildCheck(n));
        assertEquals(new TestElement(40),n.element);
        assertEquals(new TestElement(30),n.leftChild.element);
        assertEquals(new TestElement(60),n.rightChild.element);
        assertEquals(new TestElement(50),n.rightChild.leftChild.element);
        assertNull(n.leftChild.rightChild);
        assertNull(n.leftChild.leftChild);
        sTree.insert(new TestElement(65));
        sTree.insert(new TestElement(62));
        sTree.insert(new TestElement(67));
        assertTrue(parentChildCheck(n));
        //System.out.println(((BinarySearchTree)sTree).dump());
        sTree.remove(new TestElement(60 ));
        assertTrue(parentChildCheck(n));
        assertEquals(new TestElement(40),n.element);
        assertEquals(new TestElement(30),n.leftChild.element);
        assertEquals(new TestElement(62),n.rightChild.element);
        assertEquals(new TestElement(70),n.rightChild.rightChild.element);
        assertEquals(new TestElement(50),n.rightChild.leftChild.element);
        assertNull(n.rightChild.rightChild.leftChild.leftChild);
        assertNull(n.leftChild.rightChild);
        assertNull(n.leftChild.leftChild);
    }

    @Test
    public void removeTest2() {
        TestElement [] test = new TestElement[7];
        test[0] = new TestElement(40);
        test[1] = new TestElement(20);
        test[2] = new TestElement(60);
        test[3] = new TestElement(50);
        test[4] = new TestElement(30);
        test[5] = new TestElement(10);
        test[6] = new TestElement(70);
        BinarySearchTree<TestElement> sTree = new BinarySearchTree<>();
        for(TestElement e:test) {
            sTree.insert( e );
        }
        Node n = sTree.getRoot();
        sTree.remove( new TestElement(30));
        sTree.remove( new TestElement(20));
        assertTrue(parentChildCheck(n));
        assertEquals(new TestElement(40),n.element);
        assertEquals(new TestElement(10),n.leftChild.element);
        assertEquals(new TestElement(60),n.rightChild.element);
        assertEquals(new TestElement(50),n.rightChild.leftChild.element);
        assertEquals(new TestElement(70),n.rightChild.rightChild.element);
        assertNull(n.leftChild.rightChild);
        assertNull(n.leftChild.leftChild);
        assertTrue(parentChildCheck(n));
        //System.out.println(sTree.dump());
    }

    @Test
    public void clearTest() {
        TestElement [] test = new TestElement[7];
        test[0] = new TestElement(40);
        test[1] = new TestElement(20);
        test[2] = new TestElement(60);
        test[3] = new TestElement(50);
        test[4] = new TestElement(30);
        test[5] = new TestElement(10);
        test[6] = new TestElement(70);
        SearchTree<TestElement> sTree = new BinarySearchTree<>();
        for(TestElement e:test) {
            sTree.insert( e );
        }
        sTree.clear();
        assertNull(((BinarySearchTree)sTree).getRoot());
    }


    private boolean parentChildCheck(Node node) {
        if(node.leftChild == null && node.rightChild == null) return true;
        if(node.leftChild == null) return node.rightChild.parent.equals(node) && parentChildCheck( node.rightChild );
        if(node.rightChild == null) return node.leftChild.parent.equals(node) && parentChildCheck( node.leftChild );
        return node.leftChild.parent.equals(node) && node.rightChild.parent.equals(node) && parentChildCheck( node.rightChild ) && parentChildCheck( node.leftChild );
    }
}

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


import com.wwt.tools.basetools.common.BaseTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author benw-at-wwt
 */

public class BinaryHeapTest extends BaseTest {


    /**
     * Check https://www.cs.usfca.edu/~galles/visualization/Heap.html for tests
     */
    @Test
    public void insertTest() {
        int initialCapacity = 10;
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        MinHeap<TestElement> binHeap = new BinaryHeap<>(initialCapacity);
        for(TestElement e:test) {
            binHeap.insert( e );
        }
        Comparable [] toCheck = ((BinaryHeap)binHeap).getArray();
        assertEquals(new TestElement( 1 ), toCheck[0] );
        assertEquals(new TestElement( 4 ), toCheck[1] );
        assertEquals(new TestElement( 2 ), toCheck[2] );
        assertEquals(new TestElement( 7 ), toCheck[3] );
        assertEquals(new TestElement( 5 ), toCheck[4] );
        assertEquals(new TestElement( 6 ), toCheck[5] );
        assertEquals(new TestElement( 3 ), toCheck[6] );
        assertNull(toCheck[7]);
        assertEquals( 7,binHeap.size() );
        assertEquals(initialCapacity, toCheck.length );
        for(int i = 0;i<testArraySize;i++) {
            TestElement e = binHeap.extractMin();
            assertEquals(new TestElement( i+1 ),  e);
        }
        assertEquals( 0,binHeap.size() );
        assertNull(binHeap.extractMin());
    }

    @Test
    public void decreaseKeyTest() {
        int initialCapacity = 10;
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        TestElement eDecrease = test[0];
        MinHeap<TestElement> binHeap = new BinaryHeap<>(initialCapacity);
        for(TestElement e:test) {
            binHeap.insert( e );
        }
        eDecrease.setInteger( Integer.MIN_VALUE );
        binHeap.decreaseKey( eDecrease );
        assertEquals( new TestElement( Integer.MIN_VALUE),binHeap.extractMin()  );
    }

    @Test
    public void removeTest() {
        int initialCapacity = 10;
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        TestElement eDecrease = test[0];
        MinHeap<TestElement> binHeap = new BinaryHeap<>(initialCapacity);
        for(TestElement e:test) {
            binHeap.insert( e );
        }
        binHeap.remove( eDecrease );
        Comparable [] toCheck = ((BinaryHeap)binHeap).getArray();
        assertEquals(new TestElement( 1 ), toCheck[0] );
        assertEquals(new TestElement( 3 ), toCheck[1] );
        assertEquals(new TestElement( 2 ), toCheck[2] );
        assertEquals(new TestElement( 4 ), toCheck[3] );
        assertEquals(new TestElement( 5 ), toCheck[4] );
        assertEquals(new TestElement( 6 ), toCheck[5] );
    }

    @Test
    public void collectionsConstructorTest() {
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        MinHeap<TestElement> binHeap = new BinaryHeap<>( Arrays.asList(test));
        Comparable [] toCheck = ((BinaryHeap)binHeap).getArray();
        assertEquals(new TestElement( 1 ), toCheck[0] );
        assertEquals(new TestElement( 3 ), toCheck[1] );
        assertEquals(new TestElement( 2 ), toCheck[2] );
        assertEquals(new TestElement( 4 ), toCheck[3] );
        assertEquals(new TestElement( 6 ), toCheck[4] );
        assertEquals(new TestElement( 7 ), toCheck[5] );
        assertEquals(new TestElement( 5 ), toCheck[6] );
        assertEquals(7,toCheck.length);
    }

    @Test
    public void getMinTest() {
        int testArraySize = 7;
        TestElement[] test = createDescTestElementArray( testArraySize );
        MinHeap<TestElement> binHeap = new BinaryHeap<>( Arrays.asList( test ) );
        assertEquals( new TestElement( 1 ), binHeap.getMin() );
        assertEquals( new TestElement( 1 ), binHeap.getMin() );
    }

    @SuppressWarnings("unused")
    @Test
    public void dumpTest() {
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        BinaryHeap<TestElement> binHeap = new BinaryHeap<>( Arrays.asList(test));
        String s = binHeap.dump();
        assertTrue( true );
    }
    @Test
    public void clearTest() {
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        MinHeap<TestElement> binHeap = new BinaryHeap<>( Arrays.asList(test));
        binHeap.clear();
        assertEquals( 0,binHeap.size() );
        assertNull( binHeap.extractMin());
    }

    @Test
    public void toCollectionTest() {
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        MinHeap<TestElement> binHeap = new BinaryHeap<>( Arrays.asList(test));
        Collection<TestElement> collection = binHeap.toCollection();
        assertEquals(binHeap.size(),collection.size());
        for(int i = 0;i<testArraySize;i++) {
            assertTrue(collection.contains(new TestElement( i+1 )));
        }
    }

    @Test
    public void initWithCollectionTest() {
        int testArraySize = 7;
        TestElement [] test = createDescTestElementArray( testArraySize );
        MinHeap<TestElement> binHeap = new BinaryHeap<>(1);
        binHeap.initWithCollection( Arrays.asList(test));
        Collection<TestElement> collection = binHeap.toCollection();
        assertEquals(binHeap.size(),collection.size());
        for(int i = 0;i<testArraySize;i++) {
            assertTrue(collection.contains(new TestElement( i+1 )));
        }
    }
}

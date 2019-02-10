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
package com.wwt.tools.basetools.graph;

import org.junit.Test;


import static org.junit.Assert.*;
import static com.wwt.tools.basetools.graph.GraphImplTestCaseGenerator.*;
import static com.wwt.tools.basetools.graph.BFSAlgorithm.newInstance;

/**
 * @author benw-at-wwt
 */
public class BFSAlgorithmTest {

    @Test(expected = IllegalArgumentException.class)
    public void notFoundTest1() {
        NodeSearchAlgorithm<Integer> searchAlgorithm = newInstance();
        assertFalse(searchAlgorithm.search(null,null, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void notFoundTest2() {
        DirectedGraph<Integer,Integer> g = createCompleteGraphWithWeightOne( 5);
        NodeSearchAlgorithm<Integer> searchAlgorithm = newInstance();
        assertFalse(searchAlgorithm.search(g,null, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void notFoundTest3() {
        DirectedGraph<Integer,Integer> g = createEmptyGraph();
        NodeSearchAlgorithm<Integer> searchAlgorithm = newInstance();
        assertFalse(searchAlgorithm.search(g,1,2));
    }
    @Test
    public void notFoundTest() {
        DirectedGraph<Integer,Integer> g = createOneNodeGraph();
        NodeSearchAlgorithm<Integer> searchAlgorithm = newInstance();
        assertFalse(searchAlgorithm.search(g,2,3));
    }

    @Test
    public void searchTest() {
        DirectedGraph<Integer,Integer> g = createCompleteGraphWithWeightOne( 5);
        NodeSearchAlgorithm<Integer> searchAlgorithm = newInstance();
        for(Integer n:g.getNodes()) {
            assertTrue(searchAlgorithm.search(g,0,n));
        }
    }

    @Test
    public void searchTest2() {
        DirectedGraph<Integer,Integer> g = createExample1UndirectedGraph();
        NodeSearchAlgorithm<Integer> searchAlgorithm = newInstance();
        assertTrue(searchAlgorithm.search(g,0,5));
    }

    @Test
    public void searchTest3() {
        DirectedGraph<Integer,Integer> g = createExample1UndirectedSplitGraph();
        NodeSearchAlgorithm<Integer> searchAlgorithm = newInstance();
        assertFalse(searchAlgorithm.search(g,0,5));
    }

}

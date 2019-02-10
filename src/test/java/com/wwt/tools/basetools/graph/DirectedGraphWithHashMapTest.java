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

import java.util.Optional;
import java.util.OptionalDouble;

import static com.wwt.tools.basetools.graph.GraphImplTestCaseGenerator.*;
import static org.junit.Assert.*;


/**
 * @author benw-at-wwt
 */
@SuppressWarnings( "unchecked" )
public class DirectedGraphWithHashMapTest {

    @Test
    public void getEdgeTest() {
        DirectedGraphWithHashMap<Integer,Integer> g = new DirectedGraphWithHashMap<>();
        Optional<Integer> i =  g.getEdge( 1,1 );
        assertFalse(i.isPresent());
        g = (DirectedGraphWithHashMap) createOneNodeGraph();
        i = g.getEdge( 1,2 );
        assertFalse(i.isPresent());
        g = (DirectedGraphWithHashMap) createCompleteGraphWithWeightOne( 5 );
        i = g.getEdge( 0,1 );
        assertTrue(i.isPresent());
        assertEquals( 0,i.get().intValue() );
        i = g.getEdge( 1,0 );
        assertTrue(i.isPresent());
        assertEquals( 4,i.get().intValue() );

    }

    @Test
    public void getWeightTest() {
        DirectedGraphWithHashMap<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        OptionalDouble d = g.getWeight( 1,2 );
        assertFalse(d.isPresent());
        g = (DirectedGraphWithHashMap) createOneNodeGraph();
        d = g.getWeight( 1,2 );
        assertFalse(d.isPresent());
        g = (DirectedGraphWithHashMap) createCompleteGraphWithWeightOne( 5 );
        for(int i =0;i<g.getNodeCount()-1;i++) {
            d = g.getWeight( i,i+1 );
            assertTrue(d.isPresent());
            assertEquals( 1,d.getAsDouble(),0.01 );
        }
    }


    @Test
    public void addEdgeTest() {
        DirectedGraphWithHashMap<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        g.addEdge( 1,2,12,0.1 );
        assertTrue(g.getEdges().containsKey( 1 ));
        assertTrue(g.getEdges().get( 1 ).containsKey( 2 ));
        assertEquals(12, g.getEdges().get( 1 ).get( 2 ).intValue() );
        assertEquals(0.1, g.getWeights().get( g.getEdges().get( 1 ).get( 2 )),0.01 );
        g.addEdge( 2,1,13,0.2 );
        assertTrue(g.getEdges().containsKey( 2 ));
        assertTrue(g.getEdges().get( 2 ).containsKey( 1 ));
        assertEquals(13, g.getEdges().get( 2 ).get( 1 ).intValue() );
        assertEquals(0.2, g.getWeights().get( g.getEdges().get( 2 ).get( 1 )),0.01 );
        assertEquals( 2,g.getNodeCount() );
        assertEquals( 2,g.getWeights().size() );
        g.addEdge( 1,2,14,0.4 );
        assertTrue(g.getEdges().containsKey( 1 ));
        assertTrue(g.getEdges().get( 1 ).containsKey( 2 ));
        assertEquals(14, g.getEdges().get( 1 ).get( 2 ).intValue() );
        assertEquals(0.4, g.getWeights().get( g.getEdges().get( 1 ).get( 2 )),0.01 );
        assertEquals( 2,g.getNodeCount() );
        assertEquals( 2,g.getWeights().size());
    }
    @Test(expected = IllegalArgumentException.class)
    public void addEdgeExceptionTest1() {
        DirectedGraphWithHashMap<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        g.addEdge( null,2,12,0.1 );
    }
    @Test(expected = IllegalArgumentException.class)
    public void addEdgeExceptionTest2() {
        DirectedGraphWithHashMap<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        g.addEdge( 1,null,12,0.1 );
    }
    @Test(expected = IllegalArgumentException.class)
    public void addEdgeExceptionTest3() {
        DirectedGraphWithHashMap<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        g.addEdge( 1,2,null,0.1 );
    }
    @Test(expected = IllegalArgumentException.class)
    public void addEdgeExceptionTest4() {
        DirectedGraphWithHashMap<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        g.addEdge( 1,2,1,0);
        g.addEdge( 1,3,1,0);
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void getSourceTest() {
        DirectedGraph<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        g.addEdge( 1,2,1,0);
        assertEquals(1,g.getSource(1).get().intValue());
    }

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void getTargetTest() {
        DirectedGraph<Integer,Integer> g =  new DirectedGraphWithHashMap<>();
        g.addEdge(1,2,1,0);
        assertEquals(2,g.getTarget(1 ).get().intValue());
    }
}

package com.wwt.tools.basetools.graph;

import com.wwt.tools.basetools.heap.BinaryHeap;
import org.junit.Test;
import java.util.List;

import static com.wwt.tools.basetools.graph.GraphImplTestCaseGenerator.*;
import static org.junit.Assert.*;

public class DijkstraAlgorithmTest {

    @Test
    public void getAllShortestPathCompleteGraphTest() {
        DirectedGraph<Integer,Integer> g = createCompleteGraphWithWeightOne(5);
        ShortestPathAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(5));
        List<Integer> result = algorithm.getShortestPathToAllOtherNodes(g,0);
        assertEquals(4,result.size());
        for(Integer i: result) {
            assertTrue(i<4);
        }
        result = algorithm.getShortestPathToAllOtherNodes(g,1);
        assertEquals(4,result.size());
        for(Integer i: result) {
            assertTrue(i>=4 && i<8);
        }
        result = algorithm.getShortestPathToAllOtherNodes(g,2);
        assertEquals(4,result.size());
        for(Integer i: result) {
            assertTrue(i>=8 && i<12);
        }
    }

    @Test
    public void getShortestPathBetweenTest() {
        DirectedGraph<Integer,Integer> g = createExample2Graph();
        ShortestPathAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(8));
        List<Integer> result = algorithm.getShortestPathBetween( g,0,6 );
        //System.out.println( ((DirectedGraphWithHashMap)g).edgesToString(result) );
        assertEquals(3,result.size());
        for(Integer i: result) {
            assertTrue(i==2 ||i==9||i==15);
        }
    }

    @Test
    public void getShortestPathAllTest() {
        DirectedGraph<Integer,Integer> g = createExample3Graph();
        DijkstraAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(8));
        List<Integer> result = algorithm.getShortestPathToAllOtherNodes( g,0);
        //System.out.println( ((DirectedGraphWithHashMap)g).edgesToString(result) );
        assertEquals(7,result.size());
        for(Integer i: result) {
            assertTrue(i==1 ||i==2||i==7||i==9 ||i==18||i==13||i==17);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyGraphTest() {
        DirectedGraph<Integer,Integer> g = createEmptyGraph();
        DijkstraAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(1));
        List<Integer> result = algorithm.getShortestPathToAllOtherNodes( g,0);
        assertEquals(0,result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullTest1() {
        DirectedGraph<Integer,Integer> g = createOneNodeGraph();
        DijkstraAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(1));
        List<Integer> result = algorithm.getShortestPathToAllOtherNodes( g,null);
        assertEquals(0,result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullTest2() {
        DijkstraAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(1));
        List<Integer> result = algorithm.getShortestPathToAllOtherNodes( null,0);
        assertEquals(0,result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullTest3() {
        DirectedGraph<Integer,Integer> g = createOneNodeGraph();
        DijkstraAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(1));
        List<Integer> result = algorithm.getShortestPathBetween( g,0,null);
        assertEquals(0,result.size());
    }

    @Test
    public void noPathTest() {
        DirectedGraph<Integer,Integer> g = createExample1UndirectedSplitGraph();
        DijkstraAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(1));
        List<Integer> result = algorithm.getShortestPathBetween( g,0,5);
        assertEquals(0,result.size());
    }

    @Test
    public void oneNodeTest() {
        DirectedGraph<Integer,Integer> g = createOneNodeGraph();
        DijkstraAlgorithm<Integer,Integer> algorithm = new DijkstraAlgorithm<>(new BinaryHeap(1));
        List<Integer> result = algorithm.getShortestPathToAllOtherNodes( g,1);
        assertEquals(0,result.size());
    }

}

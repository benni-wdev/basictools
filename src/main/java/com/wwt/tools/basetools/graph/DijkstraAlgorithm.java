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

import com.wwt.tools.basetools.heap.MinHeap;

import java.util.*;

/**
 * Implementation of Dijkstra's algorithm for finding a shortest path between two nodes in a graph or
 * find the shortest path tree from a node to all other nodes.
 * Needs a MinHeap implementation on construction which can dramatically influence the runtime of the algorithm.
 *
 * @author benw-at-wwt
 */
public class DijkstraAlgorithm<N,E> implements ShortestPathAlgorithm<N,E> {

    private final MinHeap<NodeWithPriority<N>> minHeap;

    @SuppressWarnings({"WeakerAccess","unchecked"})
    public DijkstraAlgorithm(MinHeap minHeap) {
        this.minHeap = minHeap;
    }


    @Override
    public List<E> getShortestPathBetween(DirectedGraph<N, E> directedGraph, N startNode, N endNode) {
        if(directedGraph == null || startNode == null || endNode == null) throw new IllegalArgumentException( "Input parameter null" );
        return calculateShortestPath( directedGraph,startNode,endNode);
    }

    @Override
    public List<E> getShortestPathToAllOtherNodes(DirectedGraph<N, E> directedGraph, N startNode) {
        if(directedGraph == null || startNode == null) throw new IllegalArgumentException( "Input parameter null" );
        return calculateShortestPath( directedGraph,startNode,null);
    }

    @Override
    public String toString() {
        return "DijkstraAlgorithm{}";
    }

    /**
     * Main logic of the algorithm.
     * @param directedGraph graph to search in
     * @param startNode the startNode
     * @param endNode the endNode, can be null for all shortest path
     * @return The shortest path (tree)
     */
    private List<E> calculateShortestPath(DirectedGraph<N, E> directedGraph, N startNode, N endNode) {
        if(minHeap.isEmpty()) minHeap.clear();
        Map<N,NodeWithPriority<N>> nodeToInternalNode = new HashMap<>();
        initInternalNodes(nodeToInternalNode, directedGraph.getNodes(),startNode);
        NodeWithPriority<N> internalStartNode= nodeToInternalNode.get(startNode);
        if(internalStartNode == null) throw new IllegalArgumentException("startNode not part of the directedGraph");
        minHeap.insert(internalStartNode);

        boolean done = false;
        while(!minHeap.isEmpty() && !done ) {
            NodeWithPriority<N> currentNode = minHeap.extractMin();
            currentNode.isVisited = true;
            done = endNode != null && endNode.equals(currentNode.node);
            if(!done) {
                for (N neighbour : directedGraph.getNeighbours(currentNode.node)) {
                    NodeWithPriority<N> internalNeighbour = nodeToInternalNode.get(neighbour);
                    if (!internalNeighbour.isVisited) {
                        double distanceCurrentNeighbour = directedGraph.getWeight(currentNode.node, neighbour).orElse(Double.MAX_VALUE);
                        if (currentNode.distance + distanceCurrentNeighbour < internalNeighbour.distance) {
                            internalNeighbour.distance = currentNode.distance + distanceCurrentNeighbour;
                            if(internalNeighbour.previousNode == null) minHeap.insert(internalNeighbour);
                            else minHeap.decreaseKey(internalNeighbour);
                            internalNeighbour.previousNode = currentNode;
                        }

                    }
                }
            }
        }
        if(done) {
            return calculateEdgesForEndNode( directedGraph,nodeToInternalNode.get(endNode));
        }
        else if(endNode != null) {
            return new LinkedList<>();
        }
        else  {
            return calculateEdgesForAllNodes( directedGraph,new HashSet<>(nodeToInternalNode.values()));
        }
    }

    /**
     * transforms the internal node data into the List of edges which are the shortest path between start and end node
     *
     * @param directedGraph the graph
     * @param endNode to start collecting the edges
     * @return the list of edges
     */
    private List<E> calculateEdgesForEndNode(DirectedGraph<N, E> directedGraph, NodeWithPriority<N> endNode) {
        List<E> resultList = new LinkedList<>();
        NodeWithPriority<N> currentNode = endNode;
        while(currentNode.previousNode != null) {
            Optional<E> edge = directedGraph.getEdge(currentNode.previousNode.node,currentNode.node);
            if(edge.isPresent()) {
                resultList.add(edge.get());
            }
            else throw new IllegalStateException("calculated Edge not present in directedGraph");
            currentNode = currentNode.previousNode;
        }
        return resultList;
    }

    /**
     * transforms the internal Node data into the List of edges which are building the shortest path tree
     *
     * @param directedGraph the graph
     * @param nodePathSet the internal node set for which the shortest path tree is calculated
     * @return the list of edges
     */
    private List<E> calculateEdgesForAllNodes(DirectedGraph<N, E> directedGraph, Set<NodeWithPriority<N>> nodePathSet) {
        List<E> resultList = new LinkedList<>();
        for(NodeWithPriority<N> node : nodePathSet) {
            if(node.previousNode != null) {
                Optional<E> edge = directedGraph.getEdge(node.previousNode.node,node.node);
                if(edge.isPresent()) {
                    resultList.add(edge.get());
                }
                else throw new IllegalStateException("calculated Edge not present in directedGraph");
            }
        }
        return resultList;
    }

    /**
     * Creates the corresponding NodeWithPriority objects for the node objects of the graph
     *
     * @param internalMap to store the mapping
     * @param nodes the set of nodes of the graph
     * @param startNode the start node needs special initialization as inner node
     */
    private void initInternalNodes(Map<N,NodeWithPriority<N>> internalMap, Set<N> nodes,N startNode) {
        for(N node:nodes) {
            if(node.equals(startNode)) {
                internalMap.put(node,new NodeWithPriority<>(node,0.));
            }
            else {
                internalMap.put(node,new NodeWithPriority<>(node,Double.MAX_VALUE));
            }
        }
    }

    /**
     * Inner class to enrich a node with algorithm data
     * @param <M> encapsulated node object
     */
    private class NodeWithPriority<M> implements Comparable<NodeWithPriority<M>> {
        final M node;
        NodeWithPriority<M> previousNode;
        boolean isVisited;
        double distance;

        private NodeWithPriority(M node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeWithPriority<M> nodeWithPriority) {
            return Double.compare( this.distance,nodeWithPriority.distance);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NodeWithPriority)) return false;
            NodeWithPriority that = (NodeWithPriority) o;
            return Objects.equals( node, that.node );
        }

        @Override
        public int hashCode() {
            return Objects.hash(node);
        }
    }

}

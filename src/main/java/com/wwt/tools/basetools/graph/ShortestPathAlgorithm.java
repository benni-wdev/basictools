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

import java.util.List;

/**
 * Interface Type represents a Shortest Path Algorithm for a directed Graph
 * @author benw-at-wwt
 */
public interface ShortestPathAlgorithm<N,E> {

    /**
     * Calculates the shortest path between the startNode and the endNode in the directed Graph
     *
     * @param directedGraph the graph
     * @param startNode the node to start with
     * @param endNode edges building the shortest path from startNode to endNode or an empty list if no path exists
     * @return List of edges forming the shortest path
     */
    List<E> getShortestPathBetween(DirectedGraph<N,E> directedGraph, N startNode, N endNode);

    /**
     * Calculates the shortest path tree from the startNode to all other connected nodes.
     *
     * @param directedGraph the graph
     * @param startNode the node to start with
     * @return Shortest path tree to all other connected nodes from the startNode
     */
    List<E> getShortestPathToAllOtherNodes(DirectedGraph<N,E> directedGraph, N startNode);

}

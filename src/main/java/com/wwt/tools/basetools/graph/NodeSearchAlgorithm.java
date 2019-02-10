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

/**
 * Interface type for node search algorithm
 *
 * @author benw-at-wwt
 */
public interface NodeSearchAlgorithm<N> {

    /**
     * searches for a connection from the start node to the end node in a given graph
     * @param directedGraph the graph to search in
     * @param endNode the node to search
     * @return true if the nodes are connected, otherwise false
     */
    boolean search(DirectedGraph<N,?> directedGraph,N startNode, N endNode);
}

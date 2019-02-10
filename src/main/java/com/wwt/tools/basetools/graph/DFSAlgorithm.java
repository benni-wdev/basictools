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

import java.util.*;

/**
 * Depth-first search implementation for searching for a node in a given directed graph
 *
 * @author benw-at-wwt
 */
public class DFSAlgorithm<N> implements NodeSearchAlgorithm<N> {


    private  DFSAlgorithm() {}

    /**
     * Returns a new Depth-first search algorithm instance
     * @return the algorithm instance
     */
    @SuppressWarnings("WeakerAccess")
    public static <E> DFSAlgorithm<E> newInstance() { return new DFSAlgorithm<>(); }

    @Override
    public boolean search(DirectedGraph<N,?> directedGraph, N startNode, N endNode) {
        if(directedGraph == null || directedGraph.getNodeCount() == 0 || startNode == null
                || endNode == null ) throw new IllegalArgumentException("input null or graph empty");
        Stack<N> nodeStack = new Stack<>();
        Set<N> alreadyVisited = new HashSet<>();
        nodeStack.push(startNode);


        while(!nodeStack.isEmpty()) {
            N current = nodeStack.pop();
            alreadyVisited.add(current);
            if(current.equals( endNode )) {
                return true;
            }
            else {
                Set<N> neighbours = directedGraph.getNeighbours( current );
                for(N node: neighbours) {
                    if (!alreadyVisited.contains(node)) {
                        nodeStack.push(node);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "DFSAlgorithm{}";
    }
}

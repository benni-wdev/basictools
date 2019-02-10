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
 * Breadth-first search algorithm for searching for a node in a directed Graph.
 *
 * @author benw-at-wwt
 */
public class BFSAlgorithm<N> implements NodeSearchAlgorithm<N>  {


    private  BFSAlgorithm() {}

    /**
     * Returns a new Breadth-first search algorithm instance
     * @return the algorithm instance
     */
    @SuppressWarnings("WeakerAccess")
    public static <N> BFSAlgorithm<N> newInstance() { return new BFSAlgorithm<>(); }


    @Override
    public boolean search(DirectedGraph<N,?> directedGraph,N startNode, N endNode) {
        if(directedGraph == null || directedGraph.getNodeCount() == 0 ||
                endNode == null || startNode == null) throw new IllegalArgumentException("input null or graph empty");
        Queue<N> nodeQueue = new LinkedList<>();
        Set<N> alreadyVisited = new HashSet<>();
        nodeQueue.add(startNode);

        while(!nodeQueue.isEmpty()) {
            N current = nodeQueue.poll();
            alreadyVisited.add(current);
            if(current.equals( endNode )) {
                return true;
            }
            else {
                Set<N> neighbours = directedGraph.getNeighbours( current );
                for(N node: neighbours) {
                    if (!alreadyVisited.contains(node)) {
                        nodeQueue.add(node);
                    }
                }
            }
        }
        return false;
    }



    @Override
    public String toString() {
        return "BFSAlgorithm{}";
    }
}

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

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;

/**
 * Directed Graph interface with double weighted edges. Implementations should allow efficient construction of graphs by adding edges
 * (with implicitly adds also the corresponding nodes).
 * Please note that the interface allows only one edge between two nodes.
 *
 * @author benw-at-wwt
 */
public interface DirectedGraph<N,E> {

    /**
     * Adds an edge to the graph including the source and target node (if not yet added).
     * Additionally a double weight can be associated with the edge.
     * @param source source node of the edge
     * @param target target node of the edge
     * @param edge the edge object
     * @param weight the associated weight
     */
    void addEdge(N source,N target,E edge,double weight);

    /**
     * returns a node set. Depending on implementation this can be a copy set of the internal nodes set
     * (to guarantee DirectedGraph object consistency)
     *
     * @return The set of Nodes the graph currently contains
     */
    Set<N> getNodes();

    /**
     * Returns the set of nodes classified as neighbours of the input node.
     * A neighbour is one edge away from the input node.
     *
     * @param source the node for which the neighbours should be returned
     * @return the set of neighbour nodes, can be empty
     */
    Set<N> getNeighbours(N source);

    /**
     * Returns the edge which connects the source and the target node if any. Otherwise the Optional is empty.
     * @param source source node
     * @param target target node
     * @return Optional contains either the associated edge or is empty if no edge is present between source and target
     */
    Optional<E> getEdge(N source, N target);

    /**
     * Returns the source node for the given edge if any. Otherwise the Optional is empty.
     * @param edge the edge
     * @return Optional contains either the associated source node or is empty if edge is not known
     */
    Optional<N> getSource(E edge);

    /**
     * Returns the target node for the given edge if any. Otherwise the Optional is empty.
     * @param edge the edge
     * @return Optional contains either the associated target node or is empty if edge is not known
     */
    Optional<N> getTarget(E edge);

    /**
     * Returns the weight of the edge between the source and the target node or the optional is empty if no edge
     * exists between source and target
     * @param source source node
     * @param target target node
     * @return Optional contains either the associated weight or is empty if no edge exists between source and target
     */
    OptionalDouble getWeight(N source, N target);

    /**
     * Returns the weight of the edge or the optional is empty the edge does not exist
     * @param edge edge to get the weight
     * @return Optional contains either the associated weight or is empty if the edge does not exist
     */
    @SuppressWarnings("unused")
    OptionalDouble getWeight(E edge);

    /**
     * returns the number of nodes in the graph
     * @return number of nodes
     */
    int getNodeCount();

}

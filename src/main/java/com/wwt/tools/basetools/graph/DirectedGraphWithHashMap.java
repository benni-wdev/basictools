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
 * Implementation of a DirectedGraph based on HashMap to achieve asymptotic O(1) operations for changeable graphs
 *
 *
 * @author benw-at-wwt
 */
public class DirectedGraphWithHashMap<N,E> implements DirectedGraph<N,E> {

    private final Map<N,Map<N,E>> edges;
    private final Map<E,N> edgeSources;
    private final Map<E,N> edgeTargets;
    private final Map<E,Double> weights;
    private final Set<N> nodes;

    @SuppressWarnings("WeakerAccess")
    public DirectedGraphWithHashMap() {
        edges         = new HashMap<>();
        edgeSources   = new HashMap<>();
        edgeTargets   = new HashMap<>();
        weights       = new HashMap<>();
        nodes         = new HashSet<>();
    }


    @Override
    public void addEdge(N source, N target, E edge, double weight) {
        if(source == null || target == null || edge == null) throw new IllegalArgumentException("one of the input params is null");
        if(weights.containsKey( edge )) throw new IllegalArgumentException("edge already part of the graph");
        nodes.add(source);
        nodes.add(target);
        if(edges.containsKey(source)) {
            Map<N,E> map = edges.get(source);
            if(map.containsKey(target)) {
                E edgeToDelete = map.get( target );
                weights.remove(edgeToDelete);
            }
            edges.get(source).put(target,edge);
        }
        else {
            Map<N,E> targets = new HashMap<>();
            targets.put(target,edge);
            edges.put(source,targets);
        }
        edgeSources.put(edge,source);
        edgeTargets.put(edge,target);
        weights.put(edge,weight);
    }

    @Override
    public Set<N> getNodes() {
        return new HashSet<>(nodes);
    }

    @Override
    public Set<N> getNeighbours(N source) {
        if(!edges.containsKey( source )) return new HashSet<>();
        return edges.get(source).keySet();
    }

    @Override
    public Optional<E> getEdge(N source, N target) {
        if(!edges.containsKey( source )) return Optional.empty();
        return Optional.ofNullable(edges.get(source).get(target));
    }

    @Override
    public Optional<N> getSource(E edge) {
        return Optional.ofNullable(edgeSources.get(edge));
    }

    @Override
    public Optional<N> getTarget(E edge) {
        return Optional.ofNullable(edgeTargets.get(edge));
    }

    @Override
    public OptionalDouble getWeight(N source, N target) {
        if(!edges.containsKey( source )) return OptionalDouble.empty();
        E edge = edges.get(source).get(target);
        return edge == null ? OptionalDouble.empty():OptionalDouble.of(weights.get(edge));
    }

    @Override
    public OptionalDouble getWeight(E edge) {
        return edge == null ? OptionalDouble.empty():OptionalDouble.of(weights.get(edge));
    }

    @Override
    public int getNodeCount() {
        return nodes.size();
    }

    @Override
    public String toString() {
        return "DirectedGraphWithHashMap{" +
                "edges=" + edges +
                ", weights=" + weights +
                ", nodes=" + nodes +
                '}';
    }

    /**
     * for testing only
     * @return the inner map map for edges
     */
    Map<N,Map<N,E>> getEdges() {return edges;}

    /**
     * for testing only
     * @return the inner map for weights
     */
    Map<E,Double> getWeights() {return weights;}


    /**
     * for printing
     * @param edges list of edges
     * @return String of edges to print
     */
    @SuppressWarnings("unused")
    public String edgesToString(List<E> edges) {
        StringBuilder s = new StringBuilder();
        for(E edge:edges) {
            s.append(edge).append( ":" ).append( this.getSource( edge ) ).append( "->" ).append( this.getTarget( edge ) ).append( "\n" );
        }
        return s.toString();
    }
}

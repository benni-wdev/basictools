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
 * @author benw-at-wwt
 */
@SuppressWarnings("WeakerAccess")
public class GraphImplTestCaseGenerator {


    private GraphImplTestCaseGenerator() {}


    /**
     *  @return    empty graph
     *
     */
    public static DirectedGraph<Integer,Integer> createEmptyGraph() {
        return new DirectedGraphWithHashMap<>();
    }

    /**
     *  @return    graph with only one node
     *
     */
    public static DirectedGraph<Integer,Integer> createOneNodeGraph() {
        DirectedGraph<Integer,Integer> g = new DirectedGraphWithHashMap<>();
        Integer node1 = 1;
        g.addEdge(node1,node1,1,1);
        return g;
    }

    /**
     *  @return undirected   0
     *                      / \
     *                     1  2
     *                     | / \
     *                     |/  4
     *                     3
     *                     |
     *                     5
     *
     */
    public static DirectedGraph<Integer,Integer> createExample1UndirectedGraph() {
        Integer [] nodes = new Integer[6];
        fillNodes(nodes);
        DirectedGraph<Integer,Integer> g = new DirectedGraphWithHashMap<>();
        g.addEdge(nodes[0],nodes[1],1,1);
        g.addEdge(nodes[0],nodes[2],2,1);
        g.addEdge(nodes[1],nodes[0],3,1);
        g.addEdge(nodes[2],nodes[0],4,1);
        g.addEdge(nodes[1],nodes[3],5,1);
        g.addEdge(nodes[3],nodes[1],6,1);
        g.addEdge(nodes[2],nodes[3],7,1);
        g.addEdge(nodes[3],nodes[2],8,1);
        g.addEdge(nodes[2],nodes[4],9,1);
        g.addEdge(nodes[4],nodes[2],10,1);
        g.addEdge(nodes[5],nodes[3],11,1);
        g.addEdge(nodes[3],nodes[5],12,1);
        return g;
    }

    /**
     *  @return undirected   0
     *                      / \
     *                     1  2
     *                     | / \
     *                     |/  4
     *                     3
     *
     *                     5
     *
     */
    public static DirectedGraph<Integer,Integer> createExample1UndirectedSplitGraph() {
        Integer [] nodes = new Integer[6];
        fillNodes(nodes);
        DirectedGraph<Integer,Integer> g = new DirectedGraphWithHashMap<>();
        g.addEdge(nodes[0],nodes[1],1,1);
        g.addEdge(nodes[0],nodes[2],2,1);
        g.addEdge(nodes[1],nodes[0],3,1);
        g.addEdge(nodes[2],nodes[0],4,1);
        g.addEdge(nodes[1],nodes[3],5,1);
        g.addEdge(nodes[3],nodes[1],6,1);
        g.addEdge(nodes[2],nodes[3],7,1);
        g.addEdge(nodes[3],nodes[2],8,1);
        g.addEdge(nodes[2],nodes[4],9,1);
        g.addEdge(nodes[4],nodes[2],10,1);
        return g;
    }

    /**
     *
     * @param nodeSize number of nodes in the complete graph
     * @return a complete graph with the number of nodes
     */
    public static DirectedGraph<Integer,Integer> createCompleteGraphWithWeightOne(int nodeSize) {
        Integer [] nodes = new Integer[nodeSize];
        fillNodes(nodes);
        DirectedGraph<Integer,Integer> g = new DirectedGraphWithHashMap<>();
        int k = 0;
        for(int i = 0;i<nodeSize;i++) {
            for(int j = 0;j<nodeSize;j++) {
                if(i!=j) {
                    g.addEdge(nodes[i],nodes[j],k,1);
                    k++;
                }
            }
        }
        return g;
    }


    /**
     *  @return directed     0------
     *                      / \(1)  \(6)
     *                7    1  2      \
     *                ^    | / \(3)   \
     *                 \   |/  4-------6
     *                  \  3       (1)
     *                   \ |
     *                     5
     *
     */
    public static DirectedGraph<Integer,Integer> createExample2Graph() {
        Integer [] nodes = new Integer[8];
        fillNodes(nodes);
        DirectedGraph<Integer,Integer> g = new DirectedGraphWithHashMap<>();
        g.addEdge(nodes[0],nodes[1],1,1);
        g.addEdge(nodes[0],nodes[2],2,1);
        g.addEdge(nodes[1],nodes[0],3,1);
        g.addEdge(nodes[2],nodes[0],4,1);
        g.addEdge(nodes[1],nodes[3],5,1);
        g.addEdge(nodes[3],nodes[1],6,1);
        g.addEdge(nodes[2],nodes[3],7,1);
        g.addEdge(nodes[3],nodes[2],8,1);
        g.addEdge(nodes[2],nodes[4],9,3);
        g.addEdge(nodes[4],nodes[2],10,3);
        g.addEdge(nodes[5],nodes[3],11,7);
        g.addEdge(nodes[3],nodes[5],12,7);
        g.addEdge(nodes[0],nodes[6],13,6);
        g.addEdge(nodes[6],nodes[0],14,6);
        g.addEdge(nodes[4],nodes[6],15,1);
        g.addEdge(nodes[6],nodes[4],16,1);
        g.addEdge(nodes[5],nodes[7],17,100);
        return g;
    }

    /**
     *  @return directed     0------
     *                      / \(1)  \(6)
     *                7    1  2      \
     *                ^    | / \(3)   v
     *                 \   v/  4<------6
     *           (100)  \  3       (1)/
     *                   \ |(7)      /
     *                     5<--------(1)
     *
     *
     *
     *
     *
     */
    public static DirectedGraph<Integer,Integer> createExample3Graph() {
        Integer [] nodes = new Integer[8];
        fillNodes(nodes);
        DirectedGraph<Integer,Integer> g = new DirectedGraphWithHashMap<>();
        g.addEdge(nodes[0],nodes[1],1,1);
        g.addEdge(nodes[0],nodes[2],2,1);
        g.addEdge(nodes[1],nodes[0],3,1);
        g.addEdge(nodes[2],nodes[0],4,1);
        g.addEdge(nodes[1],nodes[3],5,5);
        g.addEdge(nodes[2],nodes[3],7,1);
        g.addEdge(nodes[3],nodes[2],8,1);
        g.addEdge(nodes[2],nodes[4],9,3);
        g.addEdge(nodes[4],nodes[2],10,3);
        g.addEdge(nodes[5],nodes[3],11,7);
        g.addEdge(nodes[3],nodes[5],12,7);
        g.addEdge(nodes[0],nodes[6],13,6);
        g.addEdge(nodes[6],nodes[4],16,1);
        g.addEdge(nodes[5],nodes[7],17,100);
        g.addEdge(nodes[6],nodes[5],18,1);
        return g;
    }

    private static void fillNodes(Integer [] nodes) {
        for(int i = 0;i<nodes.length;i++) {
            nodes[i] = i;
        }
    }


}

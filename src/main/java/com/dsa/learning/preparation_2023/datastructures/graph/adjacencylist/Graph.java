package com.dsa.learning.preparation_2023.datastructures.graph.adjacencylist;

import java.util.*;

/**
 * References:- https://www.baeldung.com/java-graphs
 */
public class Graph {

    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph(){
        adjVertices = new HashMap<>();
    }

    void addVertex(Vertex label) {
        List<Vertex> list = new ArrayList<>();
        adjVertices.put(label, list); // It creates a new Key,Value mapping for every vertex in a graph ....
    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v)); // List se removal hai ....
        adjVertices.remove(new Vertex(label)); // key removal hai Map se ....
    }

    /**
     * When V1 & V2 are stored as Edges, then V1 gets added to List of Vertices of V2 and V2 gets added to the list of Vertices of V1 ....
     */
    void addEdgeForUndirectedGraph(Vertex label1, Vertex label2) {
        adjVertices.get(label1).add(label2);
        adjVertices.get(label2).add(label1);
    }

    /**
     * When V1 & V2 are stored as Edges, then ONLY V1 gets added to List of Vertices of V2 and NOT V2 gets added to the list of Vertices of V1 ....
     */
    void addEdgeForDirectedGraph(Vertex label1, Vertex label2) {
        adjVertices.get(label1).add(label2);
    }

    void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    List<Vertex> getAdjVertices(Vertex vertex) {
        return adjVertices.get(vertex);
    }

    /**
     * Breadth-first traversal is a method for visiting all the vertices in a graph in a systematic way. Starting at a given vertex,
     * it explores all the vertices that are reachable from that vertex by visiting all the vertices at the same level before moving on to the next level.
     *
     * Queue data structure is used in this algo, as it allows us to fetch FIFO object. Therefore objects added for the same level are visited first and
     * then the objects at next level are visited one by one for each level.
     *
     * Here's how it works:
     *
     * 01. Choose a starting vertex.
     * 02. Add the starting vertex to a queue.
     * 03. Mark the starting vertex as visited.
     * 04. While the queue is not empty, do the following:
     *  a. Dequeue a vertex from the queue.
     *  b. For each unvisited neighbor of the dequeued vertex, do the following:
     *      i. Add the neighbor to the queue.
     *      ii. Mark the neighbor as visited.
     * 05. Repeat steps 4a-4b until the queue is empty.
     */
    Set<Vertex> breadthFirstTraversal(Graph graph, Vertex root) {
        Set<Vertex> visited = new LinkedHashSet<>();
        Queue<Vertex> queue = new LinkedList<>();

        queue.add(root);
        visited.add(root);

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            System.out.println("Vertex: " + vertex.getLabel());

            List<Vertex> list = graph.getAdjVertices(vertex);
            for (Vertex v : list) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }

        return visited;
    }


    /**
     * Depth-first search (DFS) is a graph traversal algorithm that starts at a given vertex and explores as far as possible along each branch before backtracking. In other words, it explores the graph by visiting all the vertices in a path as far as possible before backtracking to visit the remaining vertices. The algorithm can be implemented using recursion or a stack data structure.
     *
     * Stack data structure is used in this algo, as it allows us to fetch LIFO object. Therefore objects added for the same path/branch are visited first and
     * then the objects at next path/branch are visited one by one for each depth.
     *
     * Here are the general steps for the DFS algorithm:
     *
     * Mark the starting vertex as visited.
     * For each adjacent vertex that has not been visited, perform DFS recursively.
     * If all adjacent vertices have been visited or there are no adjacent vertices, backtrack to the previous vertex and continue the traversal.
     * Repeat steps 2-3 until all vertices have been visited.
     */

    Set<Vertex> depthFirstTraversal(Graph graph, Vertex root) {
        Set<Vertex> visited = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            System.out.println("Vertex: " + vertex.getLabel());

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                List<Vertex> list = graph.getAdjVertices(vertex);

                for (Vertex v : list) {
                    stack.push(v);
                }
            }
        }

        return visited;
    }


}

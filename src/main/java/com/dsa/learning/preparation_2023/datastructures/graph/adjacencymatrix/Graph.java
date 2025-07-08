package com.dsa.learning.preparation_2023.datastructures.graph.adjacencymatrix;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * References:- https://www.baeldung.com/java-graphs
 */
public class Graph {

    private int[][] adjacencyMatrix;
    private int numVertices;
    private Vertex[] vertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyMatrix = new int[numVertices][numVertices];
        this.vertices = new Vertex[numVertices];
    }


    void addVertex(Vertex vertex) {
        vertices[vertex.getId()] = vertex;
    }


    /**
     * When V1 & V2 are stored as Edges, then V1 gets added to List of Vertices of V2 and V2 gets added to the list of Vertices of V1 ....
     */
    void addEdgeForUndirectedGraph(Vertex v1, Vertex v2, int weight) {
        int i = v1.getId();
        int j = v2.getId();
        adjacencyMatrix[i][j] = weight;
        adjacencyMatrix[j][i] = weight;
    }

    void addEdgeForDirectedGraph(Vertex v1, Vertex v2, int weight) {
        int i = v1.getId();
        int j = v2.getId();
        adjacencyMatrix[i][j] = weight;
    }

    public void removeEdge(Vertex v1, Vertex v2) {
        int i = v1.getId();
        int j = v2.getId();
        adjacencyMatrix[i][j] = 0;
        adjacencyMatrix[j][i] = 0;
    }

    public int getWeight(Vertex v1, Vertex v2) {
        int i = v1.getId();
        int j = v2.getId();
        return adjacencyMatrix[i][j];
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void print() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i].getLabel() + ": ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }



    public int[] getAdjVertices(Vertex vertex) {
        return adjacencyMatrix[vertex.getId()];
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
    public void breadthFirstTraversal(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.println("Vertex: " + vertices[currentVertex].getLabel());

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentVertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }


    public void depthFirstTraversal(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            System.out.println("Vertex: " + vertices[currentVertex].getLabel());

            visited[startVertex] = true;

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentVertex][i] == 1 && !visited[i]) {
                    stack.push(i);
                }
            }
        }
    }

/**
 * Not working correctly ....
 */

}

package com.dsa.learning.preparation_2023.datastructures.graph.adjacencylist;

public class GraphMainClass {

    public static void main(String[] args) {

        Graph graphUndirected = new Graph();
        Graph graphDirected = new Graph();

        createUndirectedGraph(graphUndirected);
        createDirectedGraph(graphDirected);

    }

    private static void createUndirectedGraph(Graph graph) {
        Vertex v1 = new Vertex("Rahul");
        Vertex v2 = new Vertex("Akash");
        Vertex v3 = new Vertex("Mubarik");
        Vertex v4 = new Vertex("Manoj");
        Vertex v5 = new Vertex("Sagar");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdgeForUndirectedGraph(v1, v2);
        graph.addEdgeForUndirectedGraph(v1, v4);
        graph.addEdgeForUndirectedGraph(v2, v3);
        graph.addEdgeForUndirectedGraph(v4, v2);
        graph.addEdgeForUndirectedGraph(v2, v5);
        graph.addEdgeForUndirectedGraph(v4, v5);

        System.out.println("############### UNIDIRECTED GRAPH BFS ##################");
        graph.breadthFirstTraversal(graph, v1);

        System.out.println("############### UNIDIRECTED GRAPH DFS ##################");
        graph.depthFirstTraversal(graph, v1);

    }


    private static void createDirectedGraph(Graph graph) {
        Vertex v1 = new Vertex("Rahul");
        Vertex v2 = new Vertex("Akash");
        Vertex v3 = new Vertex("Mubarik");
        Vertex v4 = new Vertex("Manoj");
        Vertex v5 = new Vertex("Sagar");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdgeForDirectedGraph(v1, v2);
        graph.addEdgeForDirectedGraph(v1, v4);
        graph.addEdgeForDirectedGraph(v2, v3);
        graph.addEdgeForDirectedGraph(v4, v2);
        graph.addEdgeForDirectedGraph(v2, v5);
        graph.addEdgeForDirectedGraph(v4, v5);

        System.out.println("############### DIRECTED GRAPH BFS ##################");
        graph.breadthFirstTraversal(graph, v1);

        System.out.println("############### DIRECTED GRAPH DFS ##################");
        graph.depthFirstTraversal(graph, v1);
    }


}

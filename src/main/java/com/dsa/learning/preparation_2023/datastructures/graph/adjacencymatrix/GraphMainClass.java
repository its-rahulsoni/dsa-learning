package com.dsa.learning.preparation_2023.datastructures.graph.adjacencymatrix;

public class GraphMainClass {

    public static void main(String[] args) {

        Graph graphUndirected = new Graph(5);
        Graph graphDirected = new Graph(5);

        createUndirectedGraph(graphUndirected);
        createDirectedGraph(graphDirected);

    }

    private static void createUndirectedGraph(Graph graph) {
        Vertex v0 = new Vertex(0, "Rahul");
        Vertex v1 = new Vertex(1, "Akash");
        Vertex v2 = new Vertex(2, "Mubarik");
        Vertex v3 = new Vertex(3, "Manoj");
        Vertex v4 = new Vertex(4, "Sagar");

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdgeForUndirectedGraph(v0, v1, 1);
        graph.addEdgeForUndirectedGraph(v0, v3, 1);
        graph.addEdgeForUndirectedGraph(v1, v2, 1);
        graph.addEdgeForUndirectedGraph(v3, v1, 1);
        graph.addEdgeForUndirectedGraph(v1, v4, 1);
        graph.addEdgeForUndirectedGraph(v3, v4, 1);

        System.out.println("############### UNIDIRECTED GRAPH BFS ##################");
        graph.breadthFirstTraversal(0);

        System.out.println("############### UNIDIRECTED GRAPH DFS ##################");
        graph.depthFirstTraversal(0);

    }


    private static void createDirectedGraph(Graph graph) {
        Vertex v0 = new Vertex(0, "Rahul");
        Vertex v1 = new Vertex(1, "Akash");
        Vertex v2 = new Vertex(2, "Mubarik");
        Vertex v3 = new Vertex(3, "Manoj");
        Vertex v4 = new Vertex(4, "Sagar");

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdgeForDirectedGraph(v0, v1, 1);
        graph.addEdgeForDirectedGraph(v0, v3, 1);
        graph.addEdgeForDirectedGraph(v1, v2, 1);
        graph.addEdgeForDirectedGraph(v3, v1, 1);
        graph.addEdgeForDirectedGraph(v1, v4, 1);
        graph.addEdgeForDirectedGraph(v3, v4, 1);


        System.out.println("############### DIRECTED GRAPH BFS ##################");
        graph.breadthFirstTraversal(0);

        System.out.println("############### DIRECTED GRAPH DFS ##################");
        graph.depthFirstTraversal(0);
    }


}

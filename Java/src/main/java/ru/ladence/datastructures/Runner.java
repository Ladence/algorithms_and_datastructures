package ru.ladence.datastructures;


/**
 * @// TODO: 20.01.2018 implement graph path algorithms
 */


public class Runner{
    public static void main(String[] args) {
        Graph weightedGraph = new Graph();

        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);

        weightedGraph.addVertex(v0);
        weightedGraph.addVertex(v1);
        weightedGraph.addVertex(v2);

        weightedGraph.addEdge(new Edge(v0, v1, 2));
        weightedGraph.addEdge(new Edge(v1, v2, 10));

    }
}

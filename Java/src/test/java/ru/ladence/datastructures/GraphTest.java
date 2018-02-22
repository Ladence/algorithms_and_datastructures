package ru.ladence.datastructures;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class GraphTest {
    @Test
    public void addEdgeTest() {
        Graph<Integer> integerGraph = new Graph<>();
        integerGraph.addVertex(new Vertex(0));
        integerGraph.addVertex(new Vertex(1));

        integerGraph.addEdge(0, 1);
        integerGraph.addEdge(0, 3);
        integerGraph.addEdge(3, 1);

        Assert.assertTrue(integerGraph.getAdjVertices(new Vertex(0)).contains(new Vertex(1)));
    }


    @Test
    public void dfsTest() {
        Graph<Integer> graph = new Graph<>();
        Vertex<Integer> startVertex = new Vertex<>(0);
        graph.addEdge(startVertex.getData(), 1);
        graph.addEdge(startVertex.getData(), 2);
        graph.addEdge(startVertex.getData(), 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        Assert.assertEquals(Arrays.asList(0, 3, 2, 1), graph.dfs(startVertex));
    }

    @Test
    public void bfsTest() {
        Graph<Integer> graph = new Graph<>();
        Vertex<Integer> startVertex = new Vertex<>(0);
        graph.addEdge(startVertex.getData(), 1);
        graph.addEdge(startVertex.getData(), 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);

        Assert.assertEquals(Arrays.asList(0, 2, 1, 3), graph.bfs(startVertex));
    }


    @Test
    public void findCycleTest() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        Assert.assertFalse(graph.haveCycle(graph.getVertices().size()));
    }

    @Test
    public void findMstTest() {
        Graph<Integer> weightedGraph = new Graph<>();
        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);

        weightedGraph.addVertex(v0);
        weightedGraph.addVertex(v1);
        weightedGraph.addVertex(v2);
        weightedGraph.addVertex(v3);

        weightedGraph.addEdge(new Edge(v0, v1, 10));
        weightedGraph.addEdge(new Edge(v0, v2, 6));
        weightedGraph.addEdge(new Edge(v0, v3, 5));
        weightedGraph.addEdge(new Edge(v1, v3, 15));
        weightedGraph.addEdge(new Edge(v3, v2, 4));

        System.out.println(Graph.findKruskalMst(weightedGraph));
    }
}

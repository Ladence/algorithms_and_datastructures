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
}

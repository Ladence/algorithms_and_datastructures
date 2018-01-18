package ru.ladence.datastructures;

import com.sun.istack.internal.NotNull;

import java.util.*;

class Graph<T> {
    private Map<Vertex, Set<Edge>> adjList;
    private Set<Vertex> vertices;
    private Set<Edge> edges;

    // for DFS
    private boolean[] visited;


    Graph() {
        vertices = new HashSet<>();
        edges = new HashSet<>();
        adjList = new HashMap<>();

        visited = new boolean[100];
    }


    boolean addVertex(T data) {
        return vertices.add(new Vertex(data));
    }

    boolean addVertex(Vertex v) {
        return vertices.add(v);
    }

    boolean removeVertex(T data) {
        return vertices.remove(new Vertex(data));
    }

    boolean removeVertex(Vertex v) {
        return vertices.remove(v);
    }

    boolean addEdge(Edge e) {
        if (!edges.add(e)) {
            return  false;
        }

        adjList.putIfAbsent(e.getSource(), new HashSet<>());

        adjList.get(e.getSource()).add(e);

        return true;
    }

    boolean addEdge(T dataSource, T dataDestination) {
        return addEdge(new Edge(new Vertex(dataSource), new Vertex(dataDestination)));
    }

    boolean removeEdge(T dataSource, T dataDestination) {
        return removeEdge(new Edge(new Vertex(dataSource), new Vertex(dataDestination)));
    }

    boolean removeEdge(Edge e) {
        if (!edges.remove(e)) {
            return false;
        }

        Set<Edge> edgesOfSource = adjList.get(e.getSource());
        Set<Edge> edgesOfDestination = adjList.get(e.getDestination());

        if (edgesOfSource != null) {
            edgesOfSource.remove(e);
        }
        if (edgesOfDestination != null) {
            edgesOfDestination.remove(e);
        }
        return true;
    }

    boolean addVertices(Collection<Vertex> vertices) {
        return this.vertices.addAll(vertices);
    }

    Set<Vertex> getAdjVertices(Vertex v) {
        Set<Vertex> set = new HashSet<>();

        if (adjList.get(v) == null) {
            return Collections.emptySet();
        }
        for (Edge edge : adjList.get(v)) {
            if (!edge.getDestination().equals(v)) {
                set.add(edge.getDestination());
            }
        }
        return set;
    }


    public Map<Vertex, Set<Edge>> getAdjList() {
        return adjList;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }


    void dfs(Vertex vertex) {
        System.out.println(vertex);
        Set<Vertex> adjVertices = getAdjVertices(vertex);
        visited[(int)vertex.getData()] = true;
        for (Vertex adjVertex : adjVertices) {
            if (!visited[(int)adjVertex.getData()]) {
                dfs(adjVertex);
            }
        }
    }

    private void topologicalSortUtil(Vertex vertex, boolean visited[], Stack stack) {
        visited[(int)vertex.getData()] = true;

        for (Vertex adjVertex : getAdjVertices(vertex)) {
            if (!visited[(int)adjVertex.getData()]) {
                topologicalSortUtil(adjVertex, visited, stack);
            }
        }

        stack.push(vertex);
    }

    void topologicalSort() {
        Stack<Vertex> stack = new Stack<>();
        boolean visited[] = new boolean[100];

        for (Vertex vertex : getVertices()) {
            topologicalSortUtil(vertex, visited, stack);
        }

        System.out.println("Result of topological sort : ");
        while (stack.size() != 0) {
            System.out.println(stack.pop());
        }
    }

    void bfs(Vertex vertex) {
        Queue<Vertex> queue = new Queue<>();
        queue.push(vertex);

        while (!queue.isEmpty()) {
            Vertex current = queue.pull();
            System.out.println(current);
            visited[(int)current.getData()] = true;

            for (Vertex adjVertex : getAdjVertices(current)) {
                if (!visited[(int)adjVertex.getData()]) {
                    visited[(int)adjVertex.getData()] = true;
                    queue.push(adjVertex);
                }
            }
        }
    }
}

class Vertex<T> {
    @NotNull
    private T data;
    private boolean visited = false;

    Vertex(T data) {
        this.data = data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        if (visited != vertex.visited) return false;
        return data.equals(vertex.data);
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + (visited ? 1 : 0);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                '}';
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

class Edge {
    private static final int DEFAULT_WIDTH = 1;

    @NotNull
    private Vertex source, destination;

    private int weight;

    public Edge(Vertex source, Vertex v2, int weight) {
        this.source = source;
        this.destination = v2;
        this.weight = weight;
    }

    public Edge(Vertex source, Vertex v2) {
        this(source, v2, DEFAULT_WIDTH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (weight != edge.weight) return false;
        if (!source.equals(edge.source)) return false;
        return destination.equals(edge.destination);
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + weight;
        return result;
    }


    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }
}


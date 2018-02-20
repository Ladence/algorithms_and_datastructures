package ru.ladence.datastructures;

import com.sun.istack.internal.NotNull;

import javax.jnlp.IntegrationService;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.*;

class Graph<T> {
    private Map<Vertex, Set<Edge>> adjList;
    private Set<Vertex> vertices;
    private Set<Edge> edges;

    // for DFS
    private BitSet visited;


    Graph() {
        vertices = new HashSet<>();
        edges = new HashSet<>();
        adjList = new HashMap<>();

        visited = new BitSet(100);
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


    Map<Vertex, Set<Edge>> getAdjList() {
        return adjList;
    }

    Set<Vertex> getVertices() {
        return vertices;
    }

    Set<Edge> getEdges() {
        return edges;
    }


    private void dfsUtil(Vertex vertex, ArrayList<Integer> resultList) {
        if (vertex.getData() instanceof Integer) {
            resultList.add((Integer)vertex.getData());
            Set<Vertex> adjVertices = getAdjVertices(vertex);
            visited.set((int) vertex.getData(), true);
            for (Vertex adjVertex : adjVertices) {
                if (!visited.get((int) adjVertex.getData())) {
                    dfsUtil(adjVertex, resultList);
                }
            }
        }
    }


    List<Integer> dfs(Vertex vertex) {
        if (vertex.getData() instanceof Integer) {
            ArrayList<Integer> resultArrayList = new ArrayList<>();
            dfsUtil(vertex, resultArrayList);
            return resultArrayList;
        } else {
            throw new GenericSignatureFormatError("Generics of this type is not supported. Only Integer!");
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

    List<Integer> bfs(Vertex vertex) {
        if (vertex.getData() instanceof Integer) {
            ArrayList<Integer> result = new ArrayList<>();
            Queue<Vertex> queue = new Queue<>();
            queue.push(vertex);

            while (!queue.isEmpty()) {
                Vertex current = queue.pull();
                visited.set((int) current.getData(), true);
                result.add((Integer) current.getData());

                for (Vertex adjVertex : getAdjVertices(current)) {
                    if (!visited.get((int) adjVertex.getData())) {
                        queue.push(adjVertex);
                    }
                }
            }
            return result;
        } else {
            throw new GenericSignatureFormatError("Generics of this type doesn't support. Only Integer!");
        }
    }

    List<Edge> findKruskalMst() {
        List<Edge> result = new ArrayList<>();

        throw new UnsupportedOperationException();
    }

    /**
     * Disjoint Set Data Structure approach!
     * @return true if current graph have cycle, else false
     */
    boolean haveCycle() {
        int v = vertices.size();
        int parent[] = new int[v];

        for (int i = 0; i < v; i++) {
            parent[i] = -1;
        }

        for (Edge edge : edges) {
            int firstSet = findSubset(parent, (Integer)edge.getSource().getData());
            int secondSet = findSubset(parent, (Integer)edge.getDestination().getData());

            if (firstSet == secondSet) {
                return true;
            } else {
                unionSubsets(parent, firstSet, secondSet);
            }
        }

        return false;
    }

    /**
     * Util find function for disjoint data structure
     * @param parent array of subsets
     * @param i element
     * @return num of subset
     */
    private int findSubset(int parent[], int i) {
        if (parent[i] == -1) {
            return i;
        }
        return findSubset(parent, parent[i]);
    }

    /**
     * Util union function for disjoint data structure
     * @param parent array of subsets
     * @param firstSet ind of first subset
     * @param secondSet ind of sec subset
     */
    private void unionSubsets(int parent[], int firstSet, int secondSet) {
        int xSet = findSubset(parent, firstSet);
        int ySet = findSubset(parent, secondSet);
        parent[xSet] = ySet;
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


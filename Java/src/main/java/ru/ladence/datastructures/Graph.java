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

    Graph(Graph graph) {
        vertices = new HashSet<>(graph.vertices);
        edges = new HashSet<>(graph.edges);
        adjList = new HashMap<>(graph.adjList);

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
        resultList.add((Integer)vertex.getData());
        Set<Vertex> adjVertices = getAdjVertices(vertex);
        visited.set((int) vertex.getData(), true);
        for (Vertex adjVertex : adjVertices) {
            if (!visited.get((int) adjVertex.getData())) {
                dfsUtil(adjVertex, resultList);
            }
        }
    }


    List<Integer> dfs(Vertex vertex) {
        if (vertex.getData() instanceof Integer) {
            ArrayList<Integer> resultArrayList = new ArrayList<>();
            dfsUtil(vertex, resultArrayList);
            return resultArrayList;
        } else {
            throw new UnsupportedOperationException("Generics of this type is not supported. Only Integer!");
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
            throw new UnsupportedOperationException("Generics of this type doesn't support. Only Integer!");
        }
    }

    /**
     * Utility method for Dijkstra's algorithm
     * To find the vertex with minimum distance value, from set of vertices which not yet included in SPT-set
     * @param distances list of distances from defined root
     * @param sptSet SPT - Set
     * @return index of vertex with minimal distance
     */
    private static int minDistance(List<Integer> distances, BitSet sptSet) {
        int min = Edge.INFINITE;
        int minIndex = -1;

        for (int i = 0; i < distances.size(); i++) {
            if (!sptSet.get(i) && distances.get(i) <= min) {
                min = distances.get(i);
                minIndex = i;
            }
        }

        return minIndex;
    }

    /**
     * Dijkstra's algorithm to find shortest path from one vertex to all
     * @param graph source graph
     * @param root source vertex from which we have to find shortest paths
     */
    static void oneToAllVerticesShortPath(Graph graph, Vertex root) {
        if (!(root.getData() instanceof Integer)) {
            throw new UnsupportedOperationException("Generics of this type doesn't support. Only Integer!");
        }

        int V = graph.vertices.size();

        // output set, contains distances from root to i
        List<Integer> distances = new ArrayList<>();

        // sptSet[i] if i vertex is included uin shortest path tree
        BitSet sptSet = new BitSet(V);

        for (int i = 0; i < V; i++) {
            distances.add(Edge.INFINITE);
            sptSet.set(i, false);
        }

        distances.set((int) root.getData(), 0);

        for (int i = 0; i < V - 1; i++) {
            int u = minDistance(distances, sptSet);

            sptSet.set(u, true);

            Integer uVer = u;
            for (int v  = 0; v < V; v++) {
                int curEdgeWeight = 0;
                ArrayList<Vertex> vertices = new ArrayList<>(graph.vertices);
                for (Object obj : graph.edges) {
                    Edge edge = (Edge) obj;
                    if (edge.getSource().equals(vertices.get(u))) {
                        curEdgeWeight = edge.getWeight();
                        break;
                    }
                }
                if (!sptSet.get(v) && !(graph.vertices.stream().filter((uVer::equals)).findAny().orElse(null) == null)
                        && distances.get(u) != Edge.INFINITE && (distances.get(u) + curEdgeWeight) < distances.get(v)) {
                    distances.set(v, distances.get(u) + curEdgeWeight);
                }
            }
        }

        for (Integer distance : distances) {
            System.out.println(distance);
        }
    }

    /**
     * Find MST using Kruskal's algorithm
     * @return list contains elements in MST
     */
    static Graph findKruskalMst(Graph graph) {
        Graph subGraph = new Graph();
        List<Edge> result = new ArrayList<>(graph.edges);
        Collections.sort(result);

        for (Edge edge : result) {
            subGraph.addVertex(edge.getSource());
            subGraph.addVertex(edge.getDestination());
            subGraph.addEdge(edge);

            if (subGraph.haveCycle(graph.vertices.size())) {
                subGraph.removeEdge(edge);
            }
        }

        return subGraph;
    }

    /**
     * To check cycle in graph.
     * Disjoint Set Data Structure approach
     * @param v vertex number
     * @return true if current graph have cycle, else false
     */
    boolean haveCycle(int v) {

        Subset subsets[] = new Subset[v];

        for (int i = 0; i < v; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        for (Edge edge : edges) {
            int firstSet = findSubset(subsets, (Integer)edge.getSource().getData());
            int secondSet = findSubset(subsets, (Integer)edge.getDestination().getData());

            if (firstSet == secondSet) {
                return true;
            } else {
                unionSubsets(subsets, firstSet, secondSet);
            }
        }

        return false;
    }

    /**
     * Util function to find set of element i
     * uses path compression approach
     * @param subsets array of subsets
     * @param i element
     * @return num of subset
     */
    private int findSubset(Subset subsets[], int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = findSubset(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    /**
     * Util union function for disjoint data structure (Union by rank! for achieve O(log N))
     * @param subsets array of subsets
     * @param firstSet ind of first subset
     * @param secondSet ind of sec subset
     */
    private void unionSubsets(Subset subsets[], int firstSet, int secondSet) {
        int xSetRoot = findSubset(subsets, firstSet);
        int ySetRoot = findSubset(subsets, secondSet);


        /**
         * @// TODO: 21.02.2018 Union control flow!
         */
        // attach smaller rank tree under root of high rank tree
        if (subsets[xSetRoot].rank < subsets[ySetRoot].rank) {
            subsets[xSetRoot].parent = ySetRoot;
        } else if (subsets[xSetRoot].rank > subsets[ySetRoot].rank) {
                subsets[ySetRoot].parent = xSetRoot;
        } else {
            subsets[ySetRoot].parent = xSetRoot;
            subsets[ySetRoot].rank++;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Edge edge : edges) {
            stringBuilder.append(edge).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Union-Find Data Structure
     */
    private static class Subset {
       private int parent;
       private int rank;
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

class Edge implements Comparable<Edge> {
    private static final int DEFAULT_WIDTH = 1;
    static final int INFINITE = Integer.MAX_VALUE;

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

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
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


#pragma once
#include "arrays_algs.h"

template <class T> class Edge;
template <class T> class Graph;

template<class T>
class Vertex {
	T val;
	std::vector<Edge<T>> adj;
	
	// field for algorithms (BF, Dijsktra's, BFS/DFS recursive)
	bool visited;

	void addEdge(Vertex<T> *destination, double w);
	bool removeEdgeTo(Vertex<T> *destination);

public :
	Vertex(T value);
	Vertex(const Vertex<T> &vertex);
	
	T getVal();
	friend class Graph<T>;
};

template<class T>
class Edge {
	Vertex<T> *destination;
	double weight;
	bool visited;
public :
	Edge(Vertex<T> *destination, double weight);
	friend class Graph<T>;
	friend class Vertex<T>;
};

template<class T>
class Graph {
	std::vector<Vertex<T> *> vertexList;

public :
	bool addVertex(const T &vertexInfo);
	bool addEdge(const T &source, const T &destination, double w);
	bool removeVertex(const T &vertex);
	bool removeEdge(const T &source, const T &destination);

	// Realised algorithms :
	std::vector<T> dfs();
	std::vector<T> bfs();
};

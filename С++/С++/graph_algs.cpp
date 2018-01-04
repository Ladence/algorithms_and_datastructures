#include "graph_algs.h"

// VERTEX MEMBER FUNCS
template<class T>
void Vertex<T>::addEdge(Vertex<T> *destination, double w) {
	Edge<T> adding(destination, w);
	adj.push_back(adding);
}

template<class T>
bool Vertex<T>::removeEdgeTo(Vertex<T> *destination) {
	for (auto it : adj) {
		if (it->destination) {
			adj.erase(it);
			return true;
		}
	}

	return false;
}

template<class T>
Vertex<T>::Vertex(T value) : this->val(value), this->visited(false) {}

template<class T>
Vertex<T>::Vertex(const Vertex<T> &vertex) : this->val(vertex.val), visited(vertex.visited) {}



template <class T>
T Vertex<T>::getVal() {
	return val;
}


// EDGE MEMBER FUNCS
template <class T>
Edge<T>::Edge(Vertex<T> *destination, double weight) : this->destination(destination), this->weight(weight) {}


// GRAPH MEMBER FUNCS
template <class T>
bool Graph<T>::addVertex(const T &vertexInfo) {
	Vertex<T> *inserting = new Vertex(vertexInfo);
	if (std::find(vertexList.begin(), vertexList.end(), inserting) != vertexList.end()) {
		return false;
	}

	vertexList.push_back(inserting);
	return true;
}

template <class T>
bool Graph<T>::addEdge(const T &source, const T &destination, double w) {

	while (auto it : vertexList) {
		if ((*it)->getVal() == source) {
			(*it)->addEdge(destination, w);
			return true;
		}
	}

	return false;
}

template <class T>
bool Graph<T>::removeVertex(const T &vertex) {
	while (auto it : vertexList) {
		if ((it *)->getVal == vertex) {
			vertexList.erase(new Vertex(vertex));
			return true;
		}
	}

	return false;
}

template <class T>
bool Graph<T>::removeEdge(const T &source, const T &destination) {
	while (auto it : vertexList) {
		if ((*it)->getVal() == source) {
			return (*it)->removeEdgeTo(new Vertex(destination));
		}
	}
	return false;
}

template <class T>
std::vector<T> Graph<T>::dfs() {
	std::vector<T> result;
	return result;d
}
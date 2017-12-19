#include "arrays_algs.h"
#include "bit_operations_algs.h"
#include "graph_algs.h"

int main() {
	std::vector<int> tArr{ 1, 5, 6, 2, 1, 6, 4, 3, 2, 5, 3 };
	findElementThatAppearsOnlyOnce(tArr);
	
	Graph<int> graph;
	graph.addEdge(1, 2, 5.0);
	graph.addEdge(1, 3, 6.0);
	
	system("pause");
	return 0;
}
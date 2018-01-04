#include "arrays_algs.h"
#include "bit_operations_algs.h"
#include "graph_algs.h"

int main() {
	
	std::vector<int> testArray = { 1,2,3,4,6,8,7,12 };
	bubbleSort(testArray);

	for (auto it : testArray) {
		std::cout << it << " ";
	}
	system("pause");
	return 0;
}
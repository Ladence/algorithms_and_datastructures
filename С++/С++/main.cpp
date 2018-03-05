#include "arrays_algs.h"
#include "bit_operations_algs.h"
#include "graph_algs.h"

int main() {
	
	std::vector<int> testArray = { 0,-2,6,1,7,-1,2,3 };
	quickSort(testArray, 0, testArray.size() - 1);

	for (auto it : testArray) {
		std::cout << it << " ";
	}
	system("pause");
	return 0;
}
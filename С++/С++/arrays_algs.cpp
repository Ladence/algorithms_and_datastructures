#include "arrays_algs.h"

void findElementThatAppearsOnlyOnce(std::vector<int> arr) {
	if (arr.size() == 0) {
		return;
	}

	int x = arr[0];

	for (auto it : arr) {
		x ^= it;
	} 
	
	std::cout << "Element appears only one in array : " << x << std::endl;
}
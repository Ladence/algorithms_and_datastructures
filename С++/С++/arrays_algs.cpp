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

int findLocalMinimumMaximumByConstantComplexety(std::vector<int> arr) {
	int firstEl = arr[0];
	int lastEl = arr[arr.size() - 1];

	int lastShouldBe = firstEl + (arr.size() - 1);

	if (firstEl < arr[1]) {
		// Find local max
		int localMax = (lastShouldBe + lastEl) / 2;
		return localMax;
	}
	else {
		// Find local min
		int localMin = (lastShouldBe + lastEl) / 2;
		return localMin;
	}
}

void separateOddAndEvenIntegers(std::vector<int> &arr) {
	int leftInd = 0;
	int rightInd = arr.size() - 1;

	while (leftInd < rightInd) {
		if (arr[leftInd] % 2 == 0) {
			leftInd++;
			continue;
		}
		if (arr[rightInd] % 2 != 0) {
			rightInd--;
			continue;
		}

		std::swap(arr[leftInd], arr[rightInd]);
	}
}

bool binarySearch(std::vector<int> arr, int val) {
	int curInd = (arr.size() - 1) / 2;

	if (arr[curInd] == val) {
		return true;
	}


	if (arr[curInd] > val) {
		while (curInd > 0) {
			curInd--;
			if (arr[curInd] == val) {
				return true;
			}
		}
	}

	if (arr[curInd] < val) {
		while (curInd < arr.size() - 1) {
			curInd++;
			if (arr[curInd] == val) {
				return true;
			}
		}
	}

	return false;

	return false;
}

void bubbleSort(std::vector<int> &arr) {
	for (int i = 0; i < arr.size(); i++) {
		for (int j = i + 1; j < arr.size(); j++) {
			if (arr[i] > arr[j]) {
				std::swap(arr[i], arr[j]);
			}
		}
	}
}

void insertSort(std::vector<int> &arr) {

}

void mergeSort(std::vector<int> &arr) {

}
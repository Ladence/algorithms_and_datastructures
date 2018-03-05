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

void insertionSort(std::vector<int> &arr) {
	int key;

	for (int i = 1; i < arr.size(); i++) {
		int j = i - 1;
		key = arr[i];

		while (j >= 0 && arr[j] > key) {
			arr[j + 1] = arr[j];
			j -= 1;
		}
		arr[j + 1] = key;
	}
}

void mergeSort(std::vector<int> &arr, int l, int r) {
	if (l < r) {
		int middle = l + (r - l) / 2;
		mergeSort(arr, l, middle);
		mergeSort(arr, middle + 1, r);

		merge(arr, l, middle, r);
	}
}

void merge(std::vector<int> &arr, int l, int m, int r) {
	int boundFirst = m - l + 1;
	int boundSecond = r - m;

	std::vector<int> leftHalf, rightHalf;

	for (int i = 0; i < boundFirst; i++) {
		leftHalf.push_back(arr[i + l]);
	}

	for (int i = 0; i < boundSecond; i++) {
		rightHalf.push_back(arr[i + m + 1]);
	}

	int k = l;
	int i = 0;
	int j = 0;
	while (i < boundFirst && j < boundSecond) {
		if (leftHalf[i] <= rightHalf[i]) {
			arr[k] = leftHalf[i];
			i++;
		}
		else {
			arr[k] = rightHalf[j];
			j++;
		}
		k++;
	}

	// Copy remainings
	while (i < boundFirst) {
		arr[k] = leftHalf[i];
		i++;
		k++;
	}

	while (j < boundSecond) {
		arr[k] = rightHalf[j];
		j++;
		k++;
	}
}


void quickSort(std::vector<int> &arr, int low, int high) {
	int i = low;
	int j = high;
	int pivot = arr[(low + high) / 2];
	 
	while (i <= j) {
		while (arr[i] < pivot) {
			i++;
		}
		while (arr[j] > pivot) {
			j--;
		}
		if (i <= j) {
			std::swap(arr[i], arr[j]);
			i++;
			j--;
		}
	}

	if (low < j) {
		quickSort(arr, low, j);
	}
	if (i < high) {
		quickSort(arr, i, high);
	}
	
}

#pragma once
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <stack>

/*
	Find element that appears only once in array

	Using XOR.
	If we XOR all the ele­ments in array, all the ele­ments which are repeated twice will become 0 and remain­ing will the ele­ment which is appear­ing only once.
*/
void findElementThatAppearsOnlyOnce(std::vector<int> arr);

/*
	 Given an array such that every next ele­ment dif­fers from the pre­vi­ous by +/- 1. (i.e. a[i+1] = a[i] +/-1 ) Find the local max OR min in O(1) time
*/
int findLocalMinimumMaximumByConstantComplexety(std::vector<int> arr);

/*
	Separate odd and even integer nums in array
	Approach : Swapping Indexes
	Complexety : O(n);
*/
void separateOddAndEvenIntegers(std::vector<int> &arr);


/*
	Supposed, that array is sorted (else don't work!);

	Complexety : O(log n);
*/
bool binarySearch(std::vector<int> arr, int val);


/*
	Bubble sort
	
	Complexety : O(n^2)
*/
void bubbleSort(std::vector<int> &arr);

/*
	Insertion sort.
	Good works with small number of elements
	
	Complexety : O(n^2)
*/
void insertionSort(std::vector<int> &arr);

/*
	Merge sort

	Complexety : Theta(n * log(n));
*/
void mergeSort(std::vector<int> &arr, int l, int r);

/*
	Util function for merge sorting.
	Merges two sorted halfs of array.

	Complexety : O(n)
*/
void merge(std::vector<int> &arr, int l, int m, int r);
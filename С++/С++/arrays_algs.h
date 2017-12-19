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
#include "bit_operations_algs.h"

int numberOfBitsToBeFlippedForEquals(int x, int y) {
	int mask = x ^ y;
	int count = 0;

	while (mask > 0) {
		count += mask & 1;
		mask >>= 1;
	}

	return count;
}
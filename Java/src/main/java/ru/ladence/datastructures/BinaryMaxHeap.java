package ru.ladence.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryMaxHeap <T extends Comparable> {
    private List<T> list;

    BinaryMaxHeap(T []source) {
        list = Arrays.asList(source);
        for (int i = list.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    T getFront() {
        return list.get(0);
    }

    /**
     * Complexety : O(log2 N)
     * @param element insertable element
     */
    void add(T element) {
        list.add(element);
        int i = list.size() - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && (list.get(parent).compareTo(list.get(i)) < 0)) {
            T temp = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent, temp);

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    /**
     * Restores heap to save general property
     * Complexety : O (log2N)
     */
    void heapify(int i) {
        int left;
        int right;
        int largest;

        while (true) {
            left = 2 * i + 1;
            right = 2 * i + 2;
            largest = i;

            if (left < list.size() && list.get(left).compareTo(list.get(largest)) > 0) {
                largest = left;
            }

            if (right < list.size() && list.get(right).compareTo(list.get(largest)) > 0) {
                largest = right;
            }

            if (list.get(largest).equals(list.get(i))) {
                break;
            }

            T temp = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, temp);
            i = largest;
        }
    }

    BinaryMaxHeap() {
        list = new ArrayList<>();
    }

    int size() {
        return list.size();
    }
}

package ru.ladence.datastructures;

import java.lang.reflect.Array;

/**
 * Data structure : Priority Queue
 * Based on array
 * Using generics
 */
class PriorityQueue<T extends Comparable> {
    T data[];
    int actualSize;


    PriorityQueue(Class<T[]> genClass, int maxSize) {
        this.actualSize = 0;
        this.data = genClass.cast(Array.newInstance(genClass.getComponentType(), maxSize));
    }

    /**
     * Insert element to queue
     * High priority : max value
     * Low priority : min value
     *
     * @param val pushable value
     */
    void push(T val) {
        if (!isFull()) {
            if (actualSize == 0) {
                data[actualSize++] = val;
            } else {
                int i = 0;
                // from top element (front) to bottom (rear)
                for (i = actualSize - 1; i >= 0; i--) {
                    if (val.compareTo(data[i]) > 0) {
                        data[i + 1] = data[i];
                    } else {
                        break;
                    }
                }

                data[i + 1] = val;
                actualSize++;
            }
        }
    }

    /**
     * Get front element of priority queue
     * @return front element of queue
     */
    T remove() {
        return data[--actualSize];
    }

    /**
     * Check fullness of queue
     * @return true if full, else false
     */
    boolean isFull() {
        return actualSize == data.length;
    }

    /**
     * Check emptnes of queue
     * @return true if empty, else false
     */
    boolean isEmpty() {return actualSize == 0;}
}

package ru.ladence.datastructures;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinaryMaxHeapTest {

    private BinaryMaxHeap<Integer> heap;

    public BinaryMaxHeapTest() {
        heap = new BinaryMaxHeap<>(new Integer[]{2, 4, 5, 3, 7, -1, 2});
    }

    @Test
    public void getFrontTest() {
        Assert.assertEquals((Integer)7, heap.peek());
    }

    @Test
    public void heapSortTest() {
        List result = heap.heapSort();
        Assert.assertArrayEquals(new Integer[]{7, 5, 4, 3, 2, 2, -1}, result.toArray());
    }
}

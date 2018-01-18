package ru.ladence.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class BinaryMaxHeapTest {

    @Test
    public void getFrontTest() {
        BinaryMaxHeap<Integer> integerBinaryMaxHeap = new BinaryMaxHeap<>(new Integer[]{2, 4, 5, 3, 7, -1, 2});

        Assert.assertEquals((Integer)7, integerBinaryMaxHeap.getFront());
    }
}

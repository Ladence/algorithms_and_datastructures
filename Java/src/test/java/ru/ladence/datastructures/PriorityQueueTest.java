package ru.ladence.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class PriorityQueueTest {
    @Test
    public void priorityQueueTest() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Integer[].class, 5);
        queue.push(1);
        queue.push(2);
        queue.push(0);
        queue.push(-10);
        queue.push(-1);

        Assert.assertEquals(Integer.valueOf(-10), queue.remove());
    }
}

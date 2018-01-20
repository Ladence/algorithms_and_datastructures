package ru.ladence.datastructures;

import org.junit.Assert;
import org.junit.Test;
import sun.awt.image.ImageWatched;

public class LinkedListTest {

    @Test
    public void addInTheMiddleTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("foo");
        list.add("bar");
        list.add("baz");

        Assert.assertNotNull(list.search("bar"));
    }

    @Test
    public void removeFromTheEndTest() {
        LinkedList<String> list = new LinkedList<>();

        list.add("1");
        list.add("2");
        list.add("foo");
        list.add("bar");

        list.remove("bar");

        Assert.assertTrue(list.getTail().getData().equals("foo"));
    }

    @Test
    public void removeFromTheMiddleTest() {
        final int BOUND = 25;

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= BOUND; i++) {
            list.add(i);
        }
        list.remove(BOUND / 2);
        Assert.assertNull(list.search(BOUND/2));
    }

    @Test
    public void removeFromTheBeginTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("foo");
        list.add("bar");
        list.add("baz");

        list.remove("foo");
        Assert.assertTrue(list.getHead().getData().equals("bar"));
    }
}

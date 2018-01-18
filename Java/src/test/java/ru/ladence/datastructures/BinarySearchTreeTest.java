package ru.ladence.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {
    @Test
    public void searchTest() {
        BinarySearchTree<String> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert("foo");
        binarySearchTree.insert("bar");
        binarySearchTree.insert("baz");

        try {
            Assert.assertTrue(binarySearchTree.search("bar"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

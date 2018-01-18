package ru.ladence.datastructures;

import org.junit.Assert;
import org.junit.Test;


public class HashTableTest {

    @Test
    public void insertionTest() {
        HashTable<Integer, String> hashTable = new HashTable<>();
        try {
            hashTable.add(1, "foo");
            hashTable.add(2, "bar");
            hashTable.add(3, "baz");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Assert.assertEquals("baz", hashTable.getValue(3));
    }

    @Test
    public void removeTest() {
        HashTable<Integer, String> hashTable = new HashTable<>();
        try {
            hashTable.add(1, "foo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        hashTable.remove(1, "foo");
        Assert.assertEquals(null, hashTable.getValue(1));
    }
}

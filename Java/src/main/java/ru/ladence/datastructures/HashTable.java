package ru.ladence.datastructures;

import java.util.ArrayList;

public class HashTable<K, V> {
    // actual number of elements
    private int size;
    // array of chains
    private ArrayList<LinkedList<Pair<K, V>>> bucket;

    private static final int CAPACITY = 16;

    private int hash(K key) {
        return key.hashCode() % CAPACITY;
    }

    HashTable() {
        bucket = new ArrayList<>(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            bucket.add(new LinkedList<>());
        }
        size = 0;
    }

    public V getValue(K key) {
        int bucketIndex = hash(key);
        LinkedList<Pair<K, V>> chain = bucket.get(bucketIndex);
        ListNode<Pair<K, V>> chainCur = chain.getHead();

        while (chainCur != null) {
            if (chainCur.getData().key.equals(key)) {
                return chainCur.getData().value;
            }
            chainCur = chainCur.getNext();
        }

        return null;
    }

    public void add(K key, V value) throws Exception {
        int bucketIndex = hash(key);
        LinkedList<Pair<K, V>> chain = bucket.get(bucketIndex);
        Pair<K, V> insertable = new Pair<>(key, value);

        // if already exists, change value
        if (chain != null) {
            ListNode<Pair<K, V>> node = chain.search(insertable);
            if (node != null) {
                node.getData().value = insertable.value;
                size++;
                return;
            }
        }

        if (chain == null) {
            chain = new LinkedList<>();
        }
        size++;
        if (size > CAPACITY) {
            throw new Exception("Too low capacity!");
        }

        chain.add(insertable);
    }

    public void remove(K key, V value) {
        int bucketIndex = hash(key);
        LinkedList<Pair<K, V>> chain = bucket.get(bucketIndex);

        chain.remove(new Pair<>(key, value));
        size--;
    }

    int size() {
        return size;
    }
}

class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
        return value != null ? value.equals(pair.value) : pair.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}

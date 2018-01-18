package ru.ladence.datastructures;

public class BinarySearchTree<T extends Comparable> {
    Node<T> root;


    /**
     * Insert new node to BST
     * Complexety : O(logN)
     * @param value key of new node
     */
    void insert(T value) {
        Node insertable = new Node(value);

        if (root == null) {
            root = insertable;
            return;
        }

        Node current = root;
        Node parent;

        while (true) {
            parent = current;
            if (value.compareTo(current.getVal()) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = insertable;
                    return;
                }
            }

            if (value.compareTo(current.getVal()) > 0) {
                current = current.right;
                if (current == null) {
                    parent.right = insertable;
                    return;
                }
            }
        }
    }


    boolean search(T value) throws Exception {
        if (root == null) {
            throw new Exception("Binary tree is empty!");
        }

        Node current = root;

        while (current != null) {
            if (value.compareTo(current.getVal()) < 0) {
                current = current.left;
            }
            if (value.compareTo(current.getVal()) > 0) {
                current = current.right;
            }

            if (value.equals(current.getVal())) {
                return true;
            }
        }

        return false;
    }


}

class Node<T extends Comparable> {
    private T val;
    Node left, right;

    Node(T val) {
        this.val = val;
    }

    T getVal() {
        return val;
    }
}
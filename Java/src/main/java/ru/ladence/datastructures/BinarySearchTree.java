package ru.ladence.datastructures;

class BinarySearchTree<T extends Comparable> {
    Node<T> root;


    /**
     * Insert new node to BST
     * Avg Complexity : O(logN)
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

            parent = current;
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

    void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.getVal() +  " ");
            inorderTraversal(root.right);
        }
    }

    void deleteKey(T key) {
        root = delete(root, key);
    }

    private Node delete(Node root, T key) {
        if (root == null) return null;

        if (key.compareTo(root.getVal()) < 0) {
            root.left = delete(root.left, key);
        }

        if (key.compareTo(root.getVal()) > 0) {
            root.right = delete(root.right, key);
        }

        // founded key
        if (key.compareTo(root.getVal()) == 0) {
            // node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // node with two children
            root.setVal(getMinVal(root.right));

            root.right = delete(root.right, (T)root.getVal());
        }

        return root;
    }

    T getMinVal(Node p) {
        Node<T> temp = p;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.getVal();
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

    public void setVal(T val) {
        this.val = val;
    }
}
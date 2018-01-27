package ru.ladence.datastructures;

/**
 * 2-3 Tree data structure
 *
 * @author Zakhar Karlin
 * @since 24.01.18
 */
public class Tree23 {
    private static class Node {
        int actualSize;
        int key[];
        Node first, second, third, fourth;
        Node parent;

        Node(int k) {
            key = new int[]{k, 0, 0};
            actualSize = 1;
        }

        Node(int k, Node first, Node second, Node third, Node fourth, Node parent) {
            this.key = new int[]{k, 0, 0};
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
            this.parent = parent;
        }

        boolean find(int k) {
            for (int v : key) {
                if (k == v) {
                    return true;
                }
            }
            return false;
        }

        void insertKey(int k) {
            key[actualSize++] = k;
            sort();
        }

        void sort() {
            if (actualSize == 1) return;
            if (actualSize == 2) {
                if (key[0] > key[1]) {
                    int temp = key[0];
                    key[0] = key[1];
                    key[1] = temp;
                }
            }

            if (actualSize == 3) {

            }
        }

        void removeKey(int k) {
            if (actualSize >= 1 && key[0] == k) {
                key[0] = key[1];
                key[1] = key[2];
            } else if (actualSize == 2 && key[1] == k){
                key[1] = key[2];
            }
            actualSize--;
        }


        boolean isLeaf() {
            return first == null && second == null && third == null;
        }

        void become2Node(int k, Node first, Node second) {
            key[0] = k;
            this.first = first;
            this.second = second;
            this.third = null;
            this.fourth = null;
            this.parent = null;
            actualSize = 1;
        }
    }

    Node root;

    Node insert(Node p, int key) {
        if (p == null) {
            p = new Node(key);
        } else {
            if (p.isLeaf()) {
                p.insertKey(key);
            } else if (key <= root.key[0]) {
                insert(p.first, key);
            } else if ((p.actualSize == 1) || ((p.actualSize == 2) && key <= p.key[1])) {
                insert(p.second, key);
            } else {
                insert(p.third, key);
            }
        }

        return split(p);
    }

    Node split(Node item) {
        if (item.actualSize < 3) {
            return item;
        }

        Node x = new Node(item.key[0], item.first, item.second, null, null, item.parent);
        Node y = new Node(item.key[2], item.third, item.fourth, null, null, item.parent);
        if (x.first != null) {
            x.first.parent = x;
        }
        if (x.second != null) {
            x.second.parent = x;
        }
        if (y.first != null) {
            y.first.parent = y;
        }
        if (y.second != null) {
            y.second.parent = y;
        }

        if (item.parent != null) {
            item.parent.insertKey(item.key[1]);

            if (item.parent.first == item) {
                item.parent.first = null;
            } else if (item.parent.second == item) {
                item.parent.second = null;
            } else if (item.parent.third == item) {
                item.parent.third = null;
            }

            if (item.parent.first == null) {
                item.parent.fourth = item.parent.third;
                item.parent.third = item.parent.second;
                item.parent.second = y;
                item.parent.first = x;
            } else if (item.parent.second == null) {
                item.parent.fourth = item.parent.third;
                item.parent.third = y;
                item.parent.second = x;
            } else {
                item.parent.fourth = y;
                item.parent.third = x;
            }

            return item.parent;
        } else {
            x.parent = item;
            y.parent = item;

            item.become2Node(item.key[1], x, y);
            return item;
        }
    }

    Node searchKey(Node p, int k) {
        if (p == null) return null;

        if (p.find(k)) {
            return p;
        } else if (k < p.key[0]) {
            return searchKey(p.first, k);
        } else if ((p.actualSize == 2) && (k < p.key[1]) || (p.actualSize == 1)) {
            return searchKey(p.second, k);
        } else if (p.actualSize == 2) {
            return searchKey(p.third, k);
        }

        return null;
    }
}

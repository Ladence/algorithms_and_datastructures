package ru.ladence.datastructures;

;

/**
 * @// TODO: 20.01.2018 implement graph path algorithms
 */


public class Runner{


    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);



        tree.inorderTraversal(tree.root);

        System.out.println();
        tree.deleteKey(30);
        tree.inorderTraversal(tree.root);
    }
}

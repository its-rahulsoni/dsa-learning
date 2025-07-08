package com.dsa.learning.preparation_2023.datastructures.trees.avltree;

public class AVLTreeMainClass {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, 8);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 7);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 22);

        System.out.println("Inorder traversal (LNR) of AVL Tree.");

        tree.inorder(tree.root);

        System.out.println("\nSites to visit for further details:");
        System.out.println("Video Explanation: https://www.youtube.com/watch?v=_8qqlVH5NC0");
        System.out.println("Text Explanation: https://www.javatpoint.com/avl-tree-program-in-java");
        System.out.println("Visualization of creation process: https://www.cs.usfca.edu/~galles/visualization/AVLtree.html");
        System.out.println("Code created by ChatGPT.");

        System.out.println("\nProperties of AVl Tree:-");
        System.out.println("01. Pro - It guarantees data retrieval at O(log n) time.");
        System.out.println("02. Con - It requires multiple rotations during insertions that makes insertion a little time consuming. That's why Red-Black Tress were invented.");

    }
}

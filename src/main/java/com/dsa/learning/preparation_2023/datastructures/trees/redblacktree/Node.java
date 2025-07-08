package com.dsa.learning.preparation_2023.datastructures.trees.redblacktree;

public class Node {

    int key;
    String color;
    Node left, right, parent;

    Node(int key, String color) {
        this.key = key;
        this.color = color;
        this.parent = null;
    }

}

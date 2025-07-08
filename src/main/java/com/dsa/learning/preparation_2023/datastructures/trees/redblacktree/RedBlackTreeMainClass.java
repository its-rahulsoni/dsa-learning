package com.dsa.learning.preparation_2023.datastructures.trees.redblacktree;

public class RedBlackTreeMainClass {

    public static void main(String[] args) {

       RedBlackTree tree = new RedBlackTree();

       tree.root = tree.insert(tree.root , tree.root, 10);
       tree.root = tree.insert(tree.root , tree.root,18);
       tree.root = tree.insert(tree.root , tree.root,7);
       tree.root = tree.insert(tree.root , tree.root,15);

//       System.out.println("Inorder traversal (LNR) of Red-Black Tree.");
//       tree.inorder(tree.root);

       tree.root = tree.insert(tree.root , tree.root,16);
       tree.root = tree.insert(tree.root , tree.root, 30);
       tree.root = tree.insert(tree.root , tree.root, 25); // Working fine till here ....
       tree.root = tree.insert(tree.root , tree.root, 40); // Rotation resulting in the overall change in structure of tree from root is working incorrectly ....
//       tree.root = tree.insert(tree.root , tree.root, 60);
//       tree.root = tree.insert(tree.root , tree.root, 2);
//       tree.root = tree.insert(tree.root , tree.root, 1);
//       tree.root = tree.insert(tree.root , tree.root, 70);

        System.out.println("Inorder traversal (LNR) of Red-Black Tree.");

        tree.inorder(tree.root);
    }


}

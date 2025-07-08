package com.dsa.learning.preparation_2023.leetcode.linkedlist.utils;

import com.leetcode.bst.pojos.Node;

public class TreeTraversals {

    public void inOrderTraversal(Node root){
        if(root == null){
            return;
        }

        inOrderTraversal(root.getLeft());
        System.out.println(root.getValue());
        inOrderTraversal(root.getRight());
    }


    public void preOrderTraversal(Node root){
        if(root == null){
            return;
        }

        System.out.println(root.getValue());
        preOrderTraversal(root.getLeft());
        preOrderTraversal(root.getRight());
    }

    public void postOrderTraversal(Node root){
        if(root == null){
            return;
        }

        postOrderTraversal(root.getLeft());
        postOrderTraversal(root.getRight());
        System.out.println(root.getValue());
    }

    public void printAll3TreeTraversals(Node root){

        System.out.println("--------------- INORDER -----------------");
        inOrderTraversal(root);

        System.out.println("--------------- PREORDER -----------------");
        preOrderTraversal(root);

        System.out.println("--------------- POSTORDER -----------------");
        postOrderTraversal(root);
    }

}

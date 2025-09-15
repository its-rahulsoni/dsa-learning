package com.dsa.learning.tree.bst.utils;

public class TreeInsertion {

    public Node insertNodeInBST(Node root, int data){

        if(root == null){
            Node newNode = new Node(data);
            return newNode;
        }

        if(data < root.data){
            root.left = insertNodeInBST(root.left,data);
        }

        if(data > root.data){
            root.right = insertNodeInBST(root.right,data);
        }

        return root;
    }

}

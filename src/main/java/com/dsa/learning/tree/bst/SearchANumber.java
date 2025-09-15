package com.dsa.learning.tree.bst;

import com.dsa.learning.tree.bst.utils.Node;

public class SearchANumber {

    public static void main(String[] args) {
        TreeCreationWithNumbers treeCreationWithNumbers = new TreeCreationWithNumbers();

        // Create a Tree ....
        Node root = treeCreationWithNumbers.createTreeWithIntegers();

        int value = 50;

        Node searchnode = findRequiredNumberInTheTree(root, value);

        if(searchnode != null){
            System.out.println("Value found in tree for Node: " + searchnode.data);
        } else{
            System.out.println("Value not found in tree.");
        }

    }

    public static Node findRequiredNumberInTheTree(Node root, int value){

        if(root == null){
            return root;
        }

        if(value == root.data){
            return root;
        }

        if(value < root.data){
           return findRequiredNumberInTheTree(root.left, value);
        } else{
            return findRequiredNumberInTheTree(root.right, value);
        }
    }

}

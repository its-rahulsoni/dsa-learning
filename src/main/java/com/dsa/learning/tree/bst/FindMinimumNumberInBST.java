package com.dsa.learning.tree.bst;

import com.dsa.learning.tree.bst.utils.Node;

public class FindMinimumNumberInBST {

    public static void main(String[] args) {
        TreeCreationWithNumbers treeCreationWithNumbers = new TreeCreationWithNumbers();

        // Create a Tree ....
        Node root = treeCreationWithNumbers.createTreeWithIntegers();

        Node searchnode = findMinimumNodeUsingInOrderTraversal(root, Integer.MAX_VALUE);

        if(searchnode != null){
            System.out.println("Min Value found in tree for Node: " + searchnode.data);
        } else{
            System.out.println("Min Value not found in tree.");
        }
    }

    /**
     * Here, we are Traversing the tree ONLY to its LEFT. As we know that the left most node of the tree is the smallest data value node.
     * Even the right child node of this node (if it exists) will be greater than this node's value ....
     */
    public static Node findMinimumNodeUsingInOrderTraversal(Node root, int min){

        if(root == null){
            return root;
        }

        // This means that this is the left most node of the tree ....
        if(root.left == null){
            return root;
        }

        min = root.data;
        return findMinimumNodeUsingInOrderTraversal(root.left, min);
    }

}

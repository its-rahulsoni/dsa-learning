package com.dsa.learning.tree.bst;

import com.dsa.learning.tree.bst.utils.Node;

public class FindMaximumNumberInBST {

    public static void main(String[] args) {
        TreeCreationWithNumbers treeCreationWithNumbers = new TreeCreationWithNumbers();

        // Create a Tree ....
        Node root = treeCreationWithNumbers.createTreeWithIntegers();

        Node searchnode = findMaximumNodeUsingInOrderTraversal(root, Integer.MIN_VALUE);

        if(searchnode != null){
            System.out.println("Max Value found in tree for Node: " + searchnode.data);
        } else{
            System.out.println("Max Value not found in tree.");
        }
    }

    /**
     * Here, we are Traversing the tree ONLY to its RIGHT. As we know that the right most node of the tree is the largest data value node.
     * Even the right child node of this node (if it exists) will be greater than this node's value ....
     */
    public static Node findMaximumNodeUsingInOrderTraversal(Node root, int max){
        if(root == null){
            return root;
        }

        if(root.right == null){
            return root;
        }

        max = root.data;
        return findMaximumNodeUsingInOrderTraversal(root.right, max);
    }

}

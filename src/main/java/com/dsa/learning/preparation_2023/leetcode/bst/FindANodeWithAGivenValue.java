package com.dsa.learning.preparation_2023.leetcode.bst;

import com.dsa.learning.preparation_2023.leetcode.bst.pojos.Node;
import com.dsa.learning.preparation_2023.leetcode.bst.utils.CreateBST;
import com.dsa.learning.preparation_2023.leetcode.linkedlist.utils.TreeTraversals;

/**
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
 *
 * https://leetcode.com/problems/search-in-a-binary-search-tree/solutions/3604704/java-0-ms-recursion-explained/
 */
public class FindANodeWithAGivenValue {

    public static void main(String[] args) {

        CreateBST createBST = new CreateBST();
        TreeTraversals treeTraversals = new TreeTraversals();

        Node root = createBST.createIntValueTree();
        treeTraversals.printAll3TreeTraversals(root);

        int targetValue = 2;
        Node targetNode = getTargetNode(root, targetValue);

        if(targetNode == null){
            System.out.println("Target value: " + targetValue + " NOT found.");
        } else {
            System.out.println("Target value: " + targetValue + " found.");
        }

    }

    private static Node getTargetNode(Node root, int target){

        if(root == null || root.getValue() == target) {
            return root;
        } else if (target < root.getValue()){
            return getTargetNode(root.getLeft(), target);
        } else {
            return getTargetNode(root.getRight(), target);
        }
    }

}

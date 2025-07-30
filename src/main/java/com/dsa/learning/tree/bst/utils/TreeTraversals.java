package com.dsa.learning.tree.bst.utils;

public class TreeTraversals {

    /**
     * Inorder Traversal (LNR):
     * Order: Left Subtree -> Root Node -> Right Subtree
     * Explanation: In an Inorder traversal, you first recursively visit all nodes in the left subtree, then visit the current (root) node,
     *              and finally recursively visit all nodes in the right subtree.
     */
    public void inOrderTraversal(Node root){

        if(root == null){
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }

    /**
     * Preorder Traversal (NLR):
     * Order: Root Node -> Left Subtree -> Right Subtree
     * Explanation: In a Preorder traversal, you first visit the current (root) node, then recursively visit all nodes in the left subtree,
     *              and finally recursively visit all nodes in the right subtree.
     */
    public void preOrderTraversal(Node root){

        if(root == null){
            return;
        }

        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * Postorder Traversal (LRN):
     * Order: Left Subtree -> Right Subtree -> Root Node
     * Explanation: In a Postorder traversal, you first recursively visit all nodes in the left subtree, then recursively visit all nodes in the right subtree,
     *              and finally visit the current (root) node.
     */
    public void postOrderTraversal(Node root){

        if(root == null){
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

}

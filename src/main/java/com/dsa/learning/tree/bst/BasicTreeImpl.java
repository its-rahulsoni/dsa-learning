package com.dsa.learning.tree.bst;

import com.dsa.learning.tree.bst.utils.Node;
import com.dsa.learning.tree.bst.utils.TreeInsertion;
import com.dsa.learning.tree.bst.utils.TreeTraversals;

public class BasicTreeImpl {

    public static void main(String[] args) {

        Node root = null;

        TreeInsertion insert = new TreeInsertion();
        root = insert.insertNodeInBST(root, 50);
        root = insert.insertNodeInBST(root, 30);
        root = insert.insertNodeInBST(root, 20);
        root = insert.insertNodeInBST(root, 10);
        root = insert.insertNodeInBST(root, 15);
        root = insert.insertNodeInBST(root, 8);
        root = insert.insertNodeInBST(root, 70);
        root = insert.insertNodeInBST(root, 60);
        root = insert.insertNodeInBST(root, 80);
        root = insert.insertNodeInBST(root, 90);

        TreeTraversals traversal = new TreeTraversals();

        System.out.println("\n***** Inorder *****");
        traversal.inOrderTraversal(root);

        System.out.println("\n\n***** Preorder *****");
        traversal.preOrderTraversal(root);

        System.out.println("\n\n***** Postorder *****");
        traversal.postOrderTraversal(root);
    }

}

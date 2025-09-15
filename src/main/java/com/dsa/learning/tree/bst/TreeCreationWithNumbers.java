package com.dsa.learning.tree.bst;

import com.dsa.learning.tree.bst.utils.Node;
import com.dsa.learning.tree.bst.utils.TreeInsertion;

public class TreeCreationWithNumbers {

    public Node createTreeWithIntegers(){
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

        return root;
    }

}

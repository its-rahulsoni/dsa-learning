package com.dsa.learning.preparation_2023.leetcode.bst.utils;


import com.dsa.learning.preparation_2023.leetcode.bst.pojos.Node;

public class CreateBST {

    public Node createIntValueTree(){
        Node node1 = new Node();
        node1.setValue(4);

        Node node2 = new Node();
        node2.setValue(7);

        Node node3 = new Node();
        node3.setValue(2);

        Node node4 = new Node();
        node4.setValue(1);

        Node node5 = new Node();
        node5.setValue(3);

        node1.setLeft(node3);
        node1.setRight(node2);
        node3.setLeft(node4);
        node3.setRight(node5);

        return node1;
    }

}

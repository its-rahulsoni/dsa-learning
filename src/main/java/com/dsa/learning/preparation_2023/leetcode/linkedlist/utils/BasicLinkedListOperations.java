package com.dsa.learning.preparation_2023.leetcode.linkedlist.utils;

import com.leetcode.linkedlist.pojos.Node;

public class BasicLinkedListOperations {

    public Node createIntValueList(){
        Node node1 = new Node();
        node1.setValue(1);

        Node node2 = new Node();
        node2.setValue(2);

        Node node3 = new Node();
        node3.setValue(3);

        Node node4 = new Node();
        node4.setValue(4);

        Node node5 = new Node();
        node5.setValue(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        return node1;
    }

    public void displayLinkedList(Node node){
        Node temp = node;
        while(temp != null){
            System.out.println("Node value: " + temp.getValue());
            temp = temp.getNext();
        }
    }
}

package com.dsa.learning.preparation_2023.leetcode.linkedlist;

import com.leetcode.linkedlist.pojos.Node;
import com.leetcode.linkedlist.utils.BasicLinkedListOperations;

public class ReverseLinkedList {

    public static void main(String[] args) {

        BasicLinkedListOperations basicLinkedListOperations = new BasicLinkedListOperations();

        Node head1 = basicLinkedListOperations.createIntValueList();
        Node head2 = basicLinkedListOperations.createIntValueList();

        System.out.println("------------- Initial List -----------------");
        basicLinkedListOperations.displayLinkedList(head1);

        Node reversedListHead = inPlaceReversalOfLinkedListUsingRecursion(head1);
        System.out.println("------------- In-Place Reversed List -----------------");
        basicLinkedListOperations.displayLinkedList(reversedListHead);

        // NOT WORKING ....
//        reversalOfLinkedListUsingTwoParams(head2, null);
//        System.out.println("------------- 2-params Reversed List -----------------");
//        basicLinkedListOperations.displayLinkedList(head2);

    }

    private static Node inPlaceReversalOfLinkedListUsingRecursion(Node head){

        // Base Case ....
        if(head == null || head.getNext() == null){
            return head;
        }

        // Recurrence Relation ....
        Node resultNode = inPlaceReversalOfLinkedListUsingRecursion(head.getNext());

        head.getNext().setNext(head);
        head.setNext(null);

        return resultNode;
    }

//    private static void reversalOfLinkedListUsingTwoParams(Node currentNode, Node previousNode){
//
//        // Base Case ....
//        if(currentNode == null){
//            return;
//        }
//
//        // Recurrence Relation ....
//        reversalOfLinkedListUsingTwoParams(currentNode.getNext(), currentNode);
//
//        currentNode.setNext(previousNode);
//    }

}

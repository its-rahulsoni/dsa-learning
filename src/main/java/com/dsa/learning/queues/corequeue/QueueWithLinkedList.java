package com.dsa.learning.queues.corequeue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueWithLinkedList {

    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();

        queue.add("rahul");
        queue.add("akash");
        queue.add("mubarik");

        System.out.println("Initial Queue: " + queue);

        System.out.println("\n****************************************\n");

        String elementFromPoll = queue.poll();
        System.out.println("Element fetched using Poll: " + elementFromPoll);
        System.out.println("\nqueue after Polling.");
        System.out.println("queue: " + queue);


        System.out.println("\n****************************************\n");

        queue.add("reena");
        System.out.println("Updated queue: " + queue);

        System.out.println("\n****************************************\n");

        String elementFromPeek = queue.peek();
        System.out.println("Element fetched using Peek: " + elementFromPeek);
        System.out.println("\nqueue after Peeking.");
        System.out.println("queue: " + queue);

        System.out.println("\n****************************************\n");
        System.out.println("Iterating Queue!");

        System.out.println("\nApproach #01: Using for-loop");
        for(String queueItem: queue){
            System.out.println("Item:" + queueItem);
        }

        System.out.println("\nApproach #02: Using Stream Api");
        queue.stream().forEach(queueItem -> System.out.println("Item:" + queueItem));

    }



}

/**
 * Common Methods
 * The Queue interface provides several methods for adding, removing, and inspecting elements in the queue.
 * Here are some of the most commonly used methods:
 *
 * add(element):    Adds an element to the rear of the queue. If the queue is full, it throws an exception.
 * offer(element):  Adds an element to the rear of the queue. If the queue is full, it returns false.
 * remove():        Removes and returns the element at the front of the queue. If the queue is empty, it throws an exception.
 * poll():          Removes and returns the element at the front of the queue. If the queue is empty, it returns null.
 * element():       Returns the element at the front of the queue without removing it. If the queue is empty, it throws an exception.
 * peek():          Returns the element at the front of the queue without removing it. If the queue is empty, it returns null.
 */

package com.dsa.learning.queues.priorityqueue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A PriorityQueue in Java is a special type of queue where elements are processed based on their priority,
 * instead of the order in which they were added (FIFO). By default, the PriorityQueue uses natural ordering
 * (the elements must implement the Comparable interface) or a custom Comparator to determine the priority.
 */
public class QueueWithPriorityQueue {

    public static void main(String[] args) {

        /**
         * What: This creates a PriorityQueue of type Integer. By default, it uses natural order (ascending order for numbers).
         * Why natural order: Java applies Comparable for elements (integers implement Comparable), so numbers are ordered
         *                    in ascending order naturally.
         * Internal behavior: Internally, it uses a binary heap which maintains a partial order of the elements.
         */
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        /**
         * When elements are added, they are arranged in a way that ensures the smallest element is always at the root
         * of the binary heap (priority).
         *
         * The queue now looks like (internally, this is heap-ordered, not insertion-ordered):
         * PriorityQueue: [5, 10, 15, 20]
         *
         * Ordering: The smallest element (5) comes first because it has the highest priority.
         */
        priorityQueue.add(20); // Adds 20 to the queue
        priorityQueue.add(5);  // Adds 5 to the queue
        priorityQueue.add(15); // Adds 15 to the queue
        priorityQueue.add(10); // Adds 10 to the queue

        System.out.println("Initial Queue: " + priorityQueue);

        System.out.println("\n****************************************\n");

        /**
         * What: Retrieves the smallest element (the element with the highest priority) without removing it.
         * Complexity: O(1), as the smallest element is stored at the heap's root.
         * Why useful: You use peek() when you need to check the priority without modifying the queue.
         */
        System.out.println("Peek element: " + priorityQueue.peek());

        System.out.println("\n****************************************\n");

        /**
         * What: poll() retrieves and removes the smallest element from the queue.
         * Behavior: After removing the smallest element, the remaining elements are reorganized internally to ensure the
         *           next smallest element is now the new root.
         * Why recursion in heap: After removal, the heap property is restored using a technique called "heapify".
         * Complexity: O(log N) for each remove, as the heap must rearrange itself.
         */
        while(!priorityQueue.isEmpty()){
            System.out.println("Item Polled: " + priorityQueue.poll());
        }

        System.out.println("Queue after polling all elements: " + priorityQueue);
    }
}

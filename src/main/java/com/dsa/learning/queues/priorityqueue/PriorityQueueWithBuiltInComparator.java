package com.dsa.learning.queues.priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueWithBuiltInComparator {

    public static void main(String[] args) {

        /**
         * What: We pass a custom comparator, Comparator.reverseOrder(), that sorts elements in descending order.
         * Why: This redefines "priority" for the queue, so the largest element is treated as the highest priority.
         *
         * Real-World Use Cases:
         * Task Scheduling:         Where tasks with higher priority need to be executed before lower-priority tasks.
         * Pathfinding Algorithms:  Dijkstra's or A* algorithms use priority queues to ensure nodes with lower path costs are processed first.
         * Merging Sorted Arrays:   Efficiently merge multiple arrays in sorted order.
         */
        Queue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        priorityQueue.add(20); // Adds 20 to the queue
        priorityQueue.add(5);  // Adds 5 to the queue
        priorityQueue.add(15); // Adds 15 to the queue
        priorityQueue.add(10); // Adds 10 to the queue

        System.out.println("Priority Queue: " + priorityQueue);

    }

}

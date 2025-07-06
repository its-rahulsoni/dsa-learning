package com.dsa.learning.queues.customimpl;

public class CustomQueueUsingLinkedList {

    public static void main(String[] args) {

        QueueWithLinkedList queue = new QueueWithLinkedList();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        for(int i=0;i<5;i++){
            System.out.println("item " + i + " : " + queue.dequeue());
        }

        System.out.println("dequeue: " + queue.dequeue());

        queue.enqueue(60);
        queue.enqueue(70);
        System.out.println("Peek: " + queue.peek());

    }
}

package com.dsa.learning.queues.customimpl;

public class QueueWithLinkedList {

    QueueNode front;
    QueueNode rear;
    int capacity;
    int size;

    public QueueWithLinkedList(){
        front = rear = null;
        capacity = 5;
        size = 0;
    }

    public void enqueue(int item){
        if(size == capacity){
            System.out.println("Queue is full!");
        } else {
            QueueNode node = new QueueNode();
            node.item = item;

            if(front == null){
                front = rear = node;
            } else {
                front.next = node;
                front = node;
            }

            size++;
        }
    }

    public int dequeue(){
        int item = -1;

        if(size == 0){
            System.out.println("Queue is Empty!");
        } else {
            item = rear.item;
            size--;

            if(size == 0){
                front = rear = null;
            } else {
                rear = rear.next;
            }
        }
        return item;
    }

    public int peek(){
        return rear.item;
    }

}

package com.dsa.learning.queues.customimpl;

public class QueueWithArray {

    int front;
    int rear;
    int size;
    int capacity;
    int[] arr;

    public QueueWithArray(){
        front = 0;
        rear = 0;
        size = 0;
        capacity = 5;
        arr = new int[capacity];
    }

    public void enqueue(int item){
        if(size < capacity){
            arr[front++] = item;
            size++;
        } else {
            System.out.println("Queue is full!");
        }
    }

    public int dequeue(){
        int item = -1;

        if(size == 0){
            System.out.println("Queue is empty!");
        } else {
            item = arr[rear];
            arr[rear++] = 0;
            size--;

            if(rear == capacity){
                front = rear = 0;
            }
        }

        return item;
    }

    public int peek(){
      return arr[rear];
    }

}

package com.dsa.learning.design_patterns;

public class SingletonPattern {

    // volatile prevents other threads from seeing a partially constructed instance ....
    private static volatile SingletonPattern instance;

    private int counter;

    // Private constructor blocks instantiation from outside ....
    private SingletonPattern() {
        System.out.println("SingletonPattern instance created.");
    }

    // Thread-safe lazy initialization using double-checked locking ....
    public static SingletonPattern getInstance() {
        if (instance == null) {
            synchronized (SingletonPattern.class) {
                if (instance == null) {
                    instance = new SingletonPattern();
                }
            }
        }
        return instance;
    }

    public synchronized int incrementAndGet() {
        return ++counter;
    }

    public static void main(String[] args) throws InterruptedException {

        SingletonPattern first = SingletonPattern.getInstance();
        SingletonPattern second = SingletonPattern.getInstance();

        System.out.println("Same instance? " + (first == second));

        first.incrementAndGet();
        System.out.println("Counter seen via second reference: " + second.incrementAndGet());

        // Many threads still end up with one instance ....
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() ->
                    System.out.println(Thread.currentThread().getName() + " -> " + System.identityHashCode(SingletonPattern.getInstance())));
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}

package com.dsa.learning.preparation_2023.codingpatterns.twoheaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class TwoHeaps {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public TwoHeaps() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void insert(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // Balance the heaps
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    /**
     * Notice that we're calling these methods by creating an object of this class, that's why we didn't need to make these
     * methods as static.
     *
     * If we were to call them directly in this method, then the above methods had to be made static.
     */
    public static void main(String[] args) {
        TwoHeaps twoHeaps = new TwoHeaps();
        twoHeaps.insert(3);
        twoHeaps.insert(1);
        System.out.println("Median: " + twoHeaps.getMedian()); // Output: 2.0

        twoHeaps.insert(5);
        System.out.println("Median: " + twoHeaps.getMedian()); // Output: 3.0

        twoHeaps.insert(4);
        System.out.println("Median: " + twoHeaps.getMedian()); // Output: 3.5
    }

}

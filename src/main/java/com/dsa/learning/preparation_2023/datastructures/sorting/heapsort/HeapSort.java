package com.dsa.learning.preparation_2023.datastructures.sorting.heapsort;

import java.util.Arrays;


/**
 * WHAT IS HEAP?
 *
 * Heap is a tree-based data structure in which all the nodes of the tree are in a specific order. Heap is always a complete binary tree.
 * While converting a Heap to an array we must keep in mind the following:
 *
 *
 * The root element of the heap should be at index 0 in the array.
 * For the node at index i:
 * At index (i-1)/2 is the parent node
 * At index (2*i)+1 is the left child node
 * At index (2*i)+2 is the right child node
 * This method of achieving array representation is called Level Order. Hence, it satisfies the Ordering Property.
 *
 * PROPERTIES of HEAP:
 * 01. In-place: Heaps can be implemented in-place, without the need for additional memory, making them efficient for memory-constrained applications.
 * 02. Stable: Heaps are a stable data structure, meaning that elements are processed in order of priority, making them suitable for applications where order matters.
 *
 *
 * MAX HEAP
 * In this type of heap, the value of the parent node is always larger than the child nodes. Hence, the root node is occupied by the largest value in the whole heap.
 *
 * MIN HEAP
 * In this type of heap, the value of the parent will always be less than both of the children. Hence, the least value in the whole heap is at the root of the heap.
 *
 * HEAPIFY PROCESS
 * It is a process of creating a min or max heap from a binary tree.
 *
 * DELETION PROCESS
 * Deleting an element is very easy. All we have to do is swap the element to be deleted to the last leaf element and delete that last leaf node. Then heapify the tree.
 *
 * HEAP SORT COMPLEXITY
 * The time complexity of heap sort for worst, average, and worst case is the same that is O(nlog n).
 *
 * Resources for reference:-
 * https://www.geeksforgeeks.org/applications-of-heap-data-structure/
 * https://medium.com/basecs/heapify-all-the-things-with-heap-sort-55ee1c93af82
 *
 * Video
 * https://www.youtube.com/watch?v=Q_eia3jC9Ts
 *
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{8, 5, 2, 9, 5, 6, 3};

        System.out.println("Initial Array: " + Arrays.toString(arr));

        heapSort(arr);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        int arraySize = arr.length;
        int lastIndexOfNonLeafyNodeInHeap = arraySize/2 - 1;

        /**
         * STEPS FOR HEAP SORT:
         *
         * 01. Build a Max Heap: Starting from the last non-leaf node (n/2 - 1) to the root (0), perform the heapify operation on each node.
         * Heapify ensures that the subtree rooted at each node follows the max heap property, where the value of each node is greater than or equal to its children.
         *
         * Compare the node with its left and right children to find the largest element.
         * If the largest element is not the current node, swap the node with the largest child.
         * Repeat this process recursively for the affected sub-tree until the entire array forms a max heap.
         */
        for(int i=lastIndexOfNonLeafyNodeInHeap ;i>=0 ;i--){
            heapify(arr, arraySize, i);
        }

        /**
         * 02. Extract the Maximum Element: Starting from the end of the array, swap the first element (root of the max heap) with the last element in the array.
         * Reduce the size of the heap by one.
         *
         * 03. Restore the Max Heap Property: Perform the heapify operation on the root element to restore the max heap property. This ensures that the largest
         * element is at the root of the heap.
         *
         * 04. Repeat Steps 02 and 03: Repeat steps 2 and 3 until all elements are extracted from the heap. This will result in the array being sorted in ascending order.
         */
        for(int i=arraySize-1 ;i>=0 ;i--){
            swap(arr, i, 0);

            // Call max heapify on the reduced heap ....
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int arraySize, int heapifyParentNodeIndex){
        int largestNodeIndex = heapifyParentNodeIndex;
        int leftChildOfThisNodeIndex = (heapifyParentNodeIndex * 2) + 1;
        int rightChildOfThisNodeIndex = (heapifyParentNodeIndex * 2) + 2;

        // If left child is larger than root ....
        if(leftChildOfThisNodeIndex < arraySize && arr[leftChildOfThisNodeIndex] > arr[largestNodeIndex]){
            largestNodeIndex = leftChildOfThisNodeIndex;
        }

        // If right child is larger than largest so far ....
        if(rightChildOfThisNodeIndex < arraySize && arr[rightChildOfThisNodeIndex] > arr[largestNodeIndex]){
            largestNodeIndex = rightChildOfThisNodeIndex;
        }

        // If largest is not root ....
        if(largestNodeIndex != heapifyParentNodeIndex){
            swap(arr, heapifyParentNodeIndex, largestNodeIndex);

            // Recursively heapify the affected sub-tree ....
            heapify(arr, arraySize, largestNodeIndex); // After swapping the element at 'largestNodeIndex' will be replaced by the previous largest element and we need to heapify this subtree starting from this index ....
        }

    }

    public static void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}

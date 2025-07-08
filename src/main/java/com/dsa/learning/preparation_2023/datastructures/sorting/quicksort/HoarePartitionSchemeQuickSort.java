package com.dsa.learning.preparation_2023.datastructures.sorting.quicksort;

import java.util.Arrays;

/**
 * Hoare's partition scheme is another partitioning scheme used in the QuickSort algorithm. It is a bit more efficient than Lomuto's partition scheme
 * as it makes fewer swaps. In Hoare's scheme, two pointers, i and j, start at the two ends of the array and move towards each other until they meet.
 *
 * Here are the steps for Hoare's scheme QuickSort algorithm:
 *
 * 01. Choose a pivot: Select any element from the array as the pivot. In this example, we'll choose the element at index low as the pivot.
 *
 * 02. Partitioning: Initialize two pointers, i and j, where i starts at low and j starts at high. Increment i until an element greater than or equal
 * to the pivot is found, and decrement j until an element smaller than or equal to the pivot is found. If i is less than or equal to j, swap arr[i] with arr[j].
 * Continue this process until i becomes greater than j.
 *
 * 03. Once i becomes greater than j, swap the pivot element (arr[low]) with the element at index j. This places the pivot in its final sorted position.
 *
 * 04. Recursion: Recursively apply the above steps to the sub-arrays formed by the partitioning step. This means performing the partitioning step on the sub-array
 * of elements smaller than the pivot (from low to j-1) and the sub-array of elements greater than the pivot (from j+1 to high).
 * 
 * IMP NOTE:
 *
 * Quicksort is not inherently stable, meaning it does not guarantee the preservation of the relative order of equal elements.
 *
 * Quicksort works by partitioning the list based on a chosen pivot element and recursively sorting the two resulting sublists. The pivot element is placed in its correct position
 * in the final sorted list, but the relative order of equal elements with respect to the pivot is not necessarily preserved.
 * Depending on the implementation, equal elements might end up on either side of the pivot, causing their order to change.
 *
 * The stability of a sorting algorithm becomes significant when you need to sort objects with multiple keys or when the relative
 * order of equal elements is important. Stable sorting algorithms ensure that the original order of equal elements is preserved,
 * which can be crucial in certain applications.
 *
 * There are variations of quicksort, such as the "three-way partition" or "dual-pivot" quicksort, which attempt to improve stability by
 * handling equal elements differently. However, in its basic form, quicksort is not guaranteed to be stable.
 *  
 *  
 * Resources for reference:-
 *
 * https://medium.com/innovies-club/sorting-algorithms-quick-sort-ed0fbbdd0d40
 * https://mariam-jaludi.medium.com/sorting-algorithms-quick-sort-3e8f7bce8fc
 *
 * Video:-
 * https://www.youtube.com/watch?v=QN9hnmAgmOc&t=24s
 */
public class HoarePartitionSchemeQuickSort {

    public static void main(String[] args) {

        int[] arr = new int[]{8, 5, 2, 9, 5, 6, 3};

        System.out.println("Initial Array: " + Arrays.toString(arr));

        quickSortWithHoarePartition(arr, 0, arr.length-1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    public static void quickSortWithHoarePartition(int[] arr, int start, int end){

        int pivotIndex = performPartition(arr, start, end);

        if(start < pivotIndex-1){
            quickSortWithHoarePartition(arr, start, pivotIndex-1);
        }

        if(end > pivotIndex){
            quickSortWithHoarePartition(arr, pivotIndex+1, end);
        }

    }

    public static int performPartition(int[] arr, int start, int end){

        int pivot = arr[start];
        int left = start +1;
        int right = end;

        while(true){

            while(left <= end){
                if(arr[left] > pivot){
                    break;
                }
                else{
                    left++;
                }
            }

            while(right > start){
                if(arr[right] <= pivot){
                    break;
                }
                else{
                    right--;
                }
            }

            if(left >= right){
                swap(start, right, arr);
                return right;
            }

            swap(left, right, arr);

        }

    }

    public static void swap(int indexFrom, int indexTo, int[] arr){
        int temp = arr[indexFrom];
        arr[indexFrom] = arr[indexTo];
        arr[indexTo] = temp;
    }
}

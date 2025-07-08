package com.dsa.learning.preparation_2023.datastructures.sorting.quicksort;

import java.util.Arrays;

/**
 * Steps of this algorithm:-
 *
 * 01. Choose a pivot: Select the rightmost element of the array as the pivot.
 *
 * 02. Partitioning: Iterate through the array from left to right using two pointers, i and j. Initially, set i to the leftmost index (low) and j to low - 1.
 * Compare each element at index i with the pivot:
 *
 * 03. If arr[i] is less than or equal to the pivot, increment j and swap arr[i] with arr[j].
 * If arr[i] is greater than the pivot, do nothing.
 * After the iteration, swap the pivot element (arr[high]) with the element at index j + 1. This places the pivot in its final sorted position.
 *
 * 04. Recursion: Recursively apply the above steps to the sub-arrays formed by the partitioning step. This means performing the partitioning step on the
 * sub-array of elements smaller than the pivot (from low to j) and the sub-array of elements greater than the pivot (from j + 2 to high).
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
 * Partitioning Logic Video:-
 * https://www.youtube.com/watch?v=MZaf_9IZCrc
 */

public class LomutoPartitionSchemeQuickSort {

    public static void main(String[] args) {

//       int[] arr = new int[]{7, 2, 1, 8, 6, 3, 5, 4};
        int[] arr = new int[]{8, 5, 2, 9, 5, 6, 3};

       System.out.println("Initial Array: " + Arrays.toString(arr));

       quickSortWithLomutoPartition(arr, 0, arr.length-1);

       System.out.println("Sorted Array: " + Arrays.toString(arr));

    }


    public static void quickSortWithLomutoPartition(int[] arr, int low, int high){
        if(low < high){
            int pivotIndex = performPartition(arr, 0, high); // The pivotIndex here gives the correct index where the selected pivot element is now placed ....

            if(low < pivotIndex-1){
                quickSortWithLomutoPartition(arr, 0, pivotIndex-1);
            }

            if(high > pivotIndex){
                quickSortWithLomutoPartition(arr, pivotIndex+1, high);
            }

        }
    }

    public static int performPartition(int[] arr, int low, int high){
        int pivot = arr[high];
        int j = low -1;

        for(int i=low; i<high; i++){
            if(arr[i] <= pivot){
                j++;
                swap(j, i, arr); // This swap moves the smaller elements to the left side of the array and the larger ones to the right side ....
            }
        }
        j++;
        swap(j, high, arr); // This final swap places the pivot element at the correct place and now the future partitions will be performed on the 2 sub-arrays to the left & right of this pivot element ....
        return j;
    }

    public static void swap(int indexFrom, int indexTo, int[] arr){
        int temp = arr[indexFrom];
        arr[indexFrom] = arr[indexTo];
        arr[indexTo] = temp;
    }

}

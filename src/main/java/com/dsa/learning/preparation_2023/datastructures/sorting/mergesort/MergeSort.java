package com.dsa.learning.preparation_2023.datastructures.sorting.mergesort;

import java.util.Arrays;

/**
 * It is an example of a divide-and-conquer method. This method solves a problem recursively, applying the three steps of recursion:
 *
 * Divide the problem into a number of small subproblems.
 * Conquer the subproblems by solving them recursively.
 * Combine the solution of each subproblem into the solution for the original problem.
 *
 *
 * Here’s how merge sort works:
 *
 * (Base case) If the sequence length is either 0 and 1, it’s ordered, indeed an array with just one element or zero element is ordered.
 *
 * (Divide) Else we divide the sequence into two half subsequences. (Until the size of the subsequences are either zero or one → base case)
 *
 * (Conquer) Each of these subsequences will be ordered, applying the same algorithm recursively.
 *
 * (Combine) Once we have sorted two adjacent subsequences, we combine them together to get a sorted subsequence with a greater length,
 * until we get the entire array sorted. In order to do that, we compare the first element of the two subsequences we are combining,
 * the smaller one becomes the first element of the new output sequence (removing it from its subsequence). We repeat the procedure until both the
 * subsequences are empty and the new output sequence covers all the elements of both the previous subsequences.
 *
 * IMP NOTE:
 *
 * Merge sort is considered a stable sorting algorithm because it preserves the relative order of equal elements during the sorting process.
 *
 * During the merging step, when two sublists are combined, if there are equal elements in both sublists, merge sort always places the element
 * from the first sublist before the element from the second sublist. This ensures that the relative order of equal elements is maintained.
 *
 * The stability of a sorting algorithm becomes significant when you need to sort objects with multiple keys or when the relative order of equal
 * elements is important. Stable sorting algorithms ensure that the original order of equal elements is preserved, which can be crucial in certain applications.
 *
 * Resources for reference:-
 *
 * https://www.geeksforgeeks.org/merge-sort/
 *
 * Video
 * https://www.youtube.com/watch?v=cAv-4ltj1go
 *
 *
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] arr = new int[]{8, 5, 2, 9, 5, 6, 3};

        System.out.println("Initial Array: " + Arrays.toString(arr));

        mergeSort(arr, 0, arr.length-1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int start, int end){

        if(start >= end){
            return;
        }

        int mid = (start + end) / 2;

        // Dividing the array from starting to middle element of array passed ....
        mergeSort(arr, start, mid);

        // Dividing the array from next to middle element to last element of array passed ....
        mergeSort(arr, mid+1, end);

        // Merging the 2 arrays (01. left -> mid & 02. mid+1 -> end) ....
        mergeArrays(arr, start, mid, end);
    }

    public static void mergeArrays(int[] arr, int start, int mid, int end){

        int[] temp = new int[arr.length];

        for(int i=0;i<arr.length;i++){
            temp[i] = arr[i];
        }

        int index1 = start;
        int index2 = mid+1;
        int mergedArrayIndex = start;

        while(index1 <= mid && index2 <= end){
            if(temp[index1] >= temp[index2]){
                arr[mergedArrayIndex++] = temp[index2];
                index2++;
            } else {
                arr[mergedArrayIndex++] = temp[index1];
                index1++;
            }
        }

        while(index1 <= mid){
            arr[mergedArrayIndex++] = temp[index1++];
        }

        while(index2 <= end){
            arr[mergedArrayIndex++] = temp[index2++];
        }
    }
}

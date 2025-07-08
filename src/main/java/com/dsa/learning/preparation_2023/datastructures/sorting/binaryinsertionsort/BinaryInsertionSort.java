package com.dsa.learning.preparation_2023.datastructures.sorting.binaryinsertionsort;

import java.util.Arrays;

public class BinaryInsertionSort {

    public static void main(String[] args) {

        int[] arr = new int[]{11,2,3,0,1,3,4};

        System.out.println("Initial Array: " + Arrays.toString(arr));

        binaryInsertionSort(arr, 0, arr.length-1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void binaryInsertionSort(int[] arr, int left, int right) {

        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int correctIndexOfKeyInArray = binarySearch(arr, key, i-1);

            for (int j = i; j > correctIndexOfKeyInArray; j--) {
                arr[j] = arr[j-1];
            }

            arr[correctIndexOfKeyInArray] = key;
        }
    }


    private static int binarySearch(int[] arr, int target, int right) {
        int left = 0;

        while (left <= right) {
            int mid = (right + left) / 2;

            // Check if target is present at the middle position
            if (arr[mid] == target) {
                return mid;
            }

            // If target greater, ignore the left half
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, ignore the right half
            else {
                right = mid - 1;
            }
        }

        return left;
    }

}

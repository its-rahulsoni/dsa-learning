package com.dsa.learning.preparation_2023.datastructures.sorting.insertionsort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

        int[] arr = new int[]{11,2,3,0,1,3,4};

        System.out.println("Initial Array: " + Arrays.toString(arr));

        insertionSort(arr, 0, arr.length-1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));

    }

    private static void insertionSort(int[] arr, int left, int right) {

        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }


}

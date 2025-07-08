package com.dsa.learning.preparation_2023.interviews.arrays;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
 * <p>
 * Coding Pattern - Pointers approach
 * <p>
 * low for 0,
 * mid for 1,
 * high for 2.
 * <p>
 * The idea is to traverse array from left to right till mid crosses high (high is set to last index of array initially).
 * Swapping is carried out between mid and low/high along the way.
 * <p>
 * DOubt - Not sure why mid is incremented after swap(mid,low) and not after swap(mid,high).
 */
public class SortAnArrayOf0sAnd1sAnd2s {

    public static void main(String[] args) {

        int[] inputArray = {0, 1, 2, 0, 1, 2};

        sortArray(inputArray);

        System.out.println("Sorted array: ");
        Arrays.stream(inputArray).forEach(System.out::println);
    }

    private static void sortArray(int[] arr) {

        int x, y, z;

        x = y = 0;
        z = arr.length - 1;

        while (y <= z) {
            if (arr[y] == 0) {
                swap(arr, x, y);
                x++;
                y++;
            } else if (arr[y] == 1) {
                y++;
            } else if (arr[y] == 2) {
                swap(arr, y, z);
                z--;
            }
        }

    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}

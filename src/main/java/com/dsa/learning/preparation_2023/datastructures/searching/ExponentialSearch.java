package com.dsa.learning.preparation_2023.datastructures.searching;

/**
 * https://www.youtube.com/watch?v=BDVYtuWXgXE
 * https://www.geeksforgeeks.org/exponential-search/
 */
public class ExponentialSearch {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int targetElement = 11;

        int targetElementIndex = exponentialSearch(arr, targetElement);

        System.out.println("Target element: " + targetElement + " is at index: " + targetElementIndex);
    }

    private static int exponentialSearch(int[] arr, int targetElement) {
        int targetElementIndex = -1;

        int indexToSearch = 0;

        if (arr[indexToSearch] == targetElement) {
            targetElementIndex = indexToSearch;
        } else {
            indexToSearch = 1;
            while (indexToSearch < arr.length && arr[indexToSearch] <= targetElement) {
                indexToSearch = indexToSearch * 2;
            }
        }

        targetElementIndex = binarySearch(arr, indexToSearch / 2,
                Math.min(indexToSearch, arr.length - 1), targetElement);

        return targetElementIndex;
    }

    private static int binarySearch(int[] arr, int left, int right, int target) {

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

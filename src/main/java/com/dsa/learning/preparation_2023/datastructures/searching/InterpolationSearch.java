package com.dsa.learning.preparation_2023.datastructures.searching;

public class InterpolationSearch {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int targetElement = 7;

        int targetElementIndex = interpolationSearch(arr, targetElement);

        System.out.println("Target element: " + targetElement + " is at index: " + targetElementIndex);
    }


    private static int interpolationSearch(int[] arr, int targetElement) {
        int targetElementIndex = -1;

        int low = 0;
        int high = arr.length - 1;

        while (targetElement >= arr[low] && targetElement <= arr[high] && low <= high) {

            int probe = low + ((high - low) * (targetElement - arr[low]) / (arr[high] - arr[low]));

            if (arr[probe] == targetElement) {
                targetElementIndex = probe;
                break;
            } else if (arr[probe] > targetElement) {
                high = probe - 1;
            } else if (arr[probe] < targetElement) {
                low = probe + 1;
            }
        }

        return targetElementIndex;
    }


}

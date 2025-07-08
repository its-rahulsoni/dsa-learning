package com.dsa.learning.preparation_2023.datastructures.searching;


/**
 * https://www.geeksforgeeks.org/jump-search/
 */
public class JumpSearch {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int targetElement = 5;

        int targetElementIndex = jumpSearch(arr, targetElement);

        System.out.println("Target element: " + targetElement + " is at index: " + targetElementIndex);
    }

    private static int jumpSearch(int[] arr, int targetElement) {
        int targetElementIndex = -1;
        int arrayLength = arr.length;
        int previousStepIndex = 0;
        int jumpStep = (int) Math.floor(Math.sqrt(arrayLength));

        for (int i = 0; i < arrayLength; i += jumpStep) {
            if (arr[i] > targetElement) {
                break;
            }
            previousStepIndex = i;
        }

        for (int j = previousStepIndex; j < previousStepIndex + jumpStep - 1; j++) {
            if (arr[j] == targetElement) {
                targetElementIndex = j;
                break;
            }
        }

        return targetElementIndex;
    }


}

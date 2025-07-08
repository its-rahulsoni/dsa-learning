package com.dsa.learning.preparation_2023.datastructures.sorting.timsort;

import java.util.Arrays;

public class TimSort {

    private static final int MIN_RUN = 10;
    private static final int GALLOP_THRESHOLD = 3;

    public static void main(String[] args) {

        int[] arr = new int[]{-15,-7,-8,-5,-2,-10,-1,22,0,21,16,20,12,1,5,2,10,3,15,16};

        System.out.println("Initial Array: " + Arrays.toString(arr));

        timSort(arr, arr.length);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    private static void timSort(int[] arr, int arrayLength){

        // Step 1: Divide the array into runs and sort each run using insertion sort
        for (int i = 0; i < arrayLength; i += MIN_RUN) {
            insertionSort(arr, i, Math.min(i + MIN_RUN - 1, arrayLength - 1));
        }


        // Step 2: Merge the runs ....
        /*
         1st loop array size merge krte tie manage krti hai. Pehle MIN_RUN size se 2-2 arrays merge ho, next level pe MIN_RUN*2 size se 2-2 arrays and so on ....
           Ex:- MIN_RUN = 5,
           Iter#1:- (1-10) -> 5,5 k 2 arrays merge hongi and so on for bachi hui arrays ....
           Iter#2:- (11-20) -> 10,10 k 2 arrays merge hongi and so on for bachi hui arrays ....
           Iter#3:- (21-40) -> 20,20 k 2 arrays merge hongi, 40 size k array k liye ye final merge hoga ....
         */
        for(int size = MIN_RUN; size < arrayLength; size = size * 2){
            /*
                2nd loop, 2-2 arrays ko merge krti hai. In arrays ka size upar wali array se decide hota hai.
                Ex:- MIN_RUN = 5,
                Iter#1:- (1,5)-(6,10), (11-15)-(16-20), (21-25)-(26-30), (31-35)-(36-40) ....
                Iter#2:- (1,10)-(11-20), (21-30)-(31-40) ....
             */
            for(int left = 0; left < arrayLength; left += size * 2){
                int mid = left + size -1;
                int right = Math.min((left + (size * 2) - 1), (arrayLength - 1));

                if(mid < right){
                    mergeArrays(arr, left, mid, right);
                }
            }
        }
    }

    public static void mergeArrays(int[] arr, int left, int mid, int right){
        int[] leftArray = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArray = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        int gallopLeftCount = 0;
        int gallopRightCount = 0;

        int len1 = mid - left + 1;
        int len2 = right - mid;

        while(i < len1 && j < len2){

            if(leftArray[i] <= rightArray[j]){
                arr[k++] = leftArray[i++];
                gallopLeftCount++;

                if(gallopLeftCount == GALLOP_THRESHOLD){
                    gallopLeftCount = 0;

                    gallop(arr, leftArray, rightArray[j], i, k);
                    System.out.println("i: " + i + " j: " + j + " k: " + k);
                }

            } else {
                arr[k++] = rightArray[j++];
                gallopRightCount++;

                if(gallopRightCount == GALLOP_THRESHOLD){
                    gallopRightCount = 0;

                    gallop(arr, rightArray, leftArray[i], j, k);
                    System.out.println("i: " + i + " j: " + j + " k: " + k);
                }
            }
        }

        while (i < len1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < len2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }


    }

   private static void gallop(int[] arr, int[] sourceArray, int key, int sourceArrayIndex, int destArrayIndex){

        int indexOfKeyInSourceArray = binarySearch(sourceArray, key, sourceArrayIndex);

        while(sourceArrayIndex < indexOfKeyInSourceArray){
            arr[destArrayIndex++]= sourceArray[sourceArrayIndex++];
        }

       System.out.println("indexOfKeyInSourceArray: " + indexOfKeyInSourceArray + " destArrayIndex: " + destArrayIndex);
       System.out.println("Array sorted till now: " + Arrays.toString(arr));
   }

    private static int binarySearch(int[] arr, int target, int sourceArrayIndex) {
        int left = sourceArrayIndex;
        int right = arr.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

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

        // Target element is not present in the array
        return left;
    }

    private static void insertionSort(int[] arr, int left, int right) {

        int[] subArrayToBeSorted = Arrays.copyOfRange(arr, left, right+1);
        System.out.println("Insertion sort started on array: " + Arrays.toString(subArrayToBeSorted));

        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        int[] subArrayToSorted = Arrays.copyOfRange(arr, left, right+1);
        System.out.println("Insertion sort done on array: " + Arrays.toString(subArrayToSorted));
        System.out.println("");
    }

}

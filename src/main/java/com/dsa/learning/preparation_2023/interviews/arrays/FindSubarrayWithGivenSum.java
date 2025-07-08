package com.dsa.learning.preparation_2023.interviews.arrays;


/**
 * https://www.geeksforgeeks.org/find-subarray-with-given-sum/
 */
public class FindSubarrayWithGivenSum {

    public static void main(String[] args) {

        int[] inputArray = {1, 4, 20, 3, 10, 5};
        int sum = 33;


        findSubArray(inputArray, inputArray.length, sum);
    }


    /**
     * Coding Pattern - Sliding Window (slightly modified)
     *
     * Since this ques asks us to find a sub-array, it means that the numbers will be in continuation inside the
     * input array.
     * <p>
     * So, we can keep adding the numbers from the array into our sum till we reach a value that is greater than
     * the required sum. Bcoz at this point, it makes no sense to add further elements to our current sum.
     * <p>
     * Then, since the subarray will be composed of continuous values, so can start removing the values from the
     * begining of the array by keeping a pointer to the start of the array and check if current sum value now
     * is equal to the desired sum value, if no then we keep subtracting elements from the start and if yes, then we
     * stop there and print the sub array from the start pointer to the i-1th index of array till where we've already
     * looped the array.
     *
     * @param arr
     * @param length
     * @param sum
     */
    private static void findSubArray(int[] arr, int length, int sum) {

        int start = 0;
        int currentSum = arr[0];

        for (int i = 1; i < length; i++) {

            while (currentSum > sum && start < i - 1) {
                currentSum = currentSum - arr[start];
                start++;
            }

            if (currentSum == sum) {
                System.out.println("sub-array found b/w indexes: " + start + " - " + (i - 1));
                return;
            }

            currentSum += arr[i];

        }


    }

}

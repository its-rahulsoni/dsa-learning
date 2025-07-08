package com.dsa.learning.string.striver.easy;

import java.util.Arrays;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        //String[] strArray = {"flowers","fly","flow","flight"};
        //String[] strArray = {"Prefix", "Pretext", "Presentation"};
        //String[] strArray = {"a", "ab", "abc", "abcd"};
        String[] strArray = {"abcdefghij", "abcdefxyz", "abcdefuvw"};
        //String[] strArray = {"abcde", "abxyz", "abcxy"}; // Prefix Is Not Continuous ....

        //String commonLargestPrefix = findCommonLargestPrefix(strArray);

        approach2(strArray);
    }

    /**
     * The idea is to compare each character at a specific INDEX in all the array elements one by one.
     * If the character matches, then we know that this is where the prefix starts and we continue doing this till it ends.
     * This start and end index gives us the necessary sub-string i.e. prefix.
     *
     * Sorting the array of strings leverages the fact that after sorting, the strings with the most differences will naturally be the first and last strings in
     * dictionary order. The common prefix between the first and last strings is guaranteed to be the common prefix for all strings in the array,
     * because all intermediate strings would also share it.
     */
    public static String findCommonLargestPrefix(String[] arr){
        int length = arr.length;


        char[][] charArr = new char[length][];

        for(int i=0;i<length;i++){
            charArr[i] = arr[i].toCharArray();
        }

        int minStringlength = 10000;
        for(String s: arr){
            if(s.length() < minStringlength){
                minStringlength = s.length();
            }
        }

        int start;int end;
        start = end = -1;
        boolean flag = true;
        boolean isStartIndex = true;
        for(int i=0; i<minStringlength; i++){
            flag = true;
            for(int j=0;j<length-1;j++){
                if(flag){
                    char a = charArr[j][i];
                    char b = charArr[j+1][i];
                    //if(charArr[j][i] != charArr[j+1][i]){
                    if(a != b){
                        flag = false;
                        break;
                    }
                }
            }
            if(flag){
                if(isStartIndex){
                    start = i;
                    end = i;
                    isStartIndex = false;
                } else {
                    end = i;
                }
            }

        }

        String prefix = arr[0].substring(start, end + 1);
        System.out.println("Longest prefix: " + prefix);

        return prefix;
    }

    /**
     * The concept here is to SORT the strings inside the array. Then we can compare the first and the last strings.
     *
     */
    public static String approach2(String[] arr){
        System.out.println("UnSorted arr: " + Arrays.toString(arr)); // NOTE: To print an array in sout ....
        Arrays.sort(arr);
        System.out.println("Sorted arr: " + Arrays.toString(arr)); // NOTE: To print an array in sout ....

        int length = arr.length;

        char[] c1 = arr[0].toCharArray();
        char[] c2 = arr[length-1].toCharArray();

        int i = 0;
        int start = -1;
        int end = -1;
        boolean isStart = true;
        try{
            while(i < c1.length && i < c2.length){
                if(c1[i] == c2[i]){
                    if(isStart){
                        start = i;
                        end = i;
                        isStart = false;
                    } else {
                        end = i;
                    }
                } else {
                    break;
                }
                i++;
            }
        } catch (StringIndexOutOfBoundsException e){
        }


        String prefix = arr[0].substring(start, end + 1);
        System.out.println("Longest prefix: " + prefix);

        return prefix;
    }


}

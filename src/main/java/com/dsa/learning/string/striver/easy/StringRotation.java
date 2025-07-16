package com.dsa.learning.string.striver.easy;

/**
 * THOUGHT PROCESS:
 *
 * Approach #01: We take the first character of the string in each iteration in a separate character var.
 *               Then, we create a sub-string of the remaining string and append it to the String Builder.
 *               Finally, we append the initial character to the end of the String Builder.
 *
 * Approach #02: We create a Sub-String ONLY ONCE. We start this string from index after the rotation times value
 *               and continue it till the end of string. Finally we append the initial portion of the string from index
 *               0 till times index.
 *
 * Approach #03: We append this string to itself. This will help us to slice the required string by starting from the
 *               index times (of rotation) till the length + times index location. The logic here is that the rotation
 *               basically requires the front part of the string appended behind the string. By doing this, we have the
 *               second half of the new string with front part of the core string.
 */
public class StringRotation {

    public static void main(String[] args) {
        String str = "abcde";

        System.out.println("Rotated String Approach #01: " + rotateStringApproach1(str,2));
        //System.out.println("Rotated String (WithoutStringBuilder) Approach #01: " +
          //      rotateStringApproach1WithoutStringBuilder(str,2));

        rotateStringApproach2(str, 2);
        rotateStringApproach3(str, 2);
    }

    public static String rotateStringApproach1(String str, int times){
        String output = str;
        int length = str.length();

        for(int i=0; i< times; i++){
            char ch = output.charAt(0);
            StringBuilder sb = new StringBuilder();
            sb.append(output.substring(1, length));
            sb.append(ch);
            output = sb.toString();
            System.out.println("String after " + (i+1) + " rotation: " + output);
        }

        return output;
    }

    public static String rotateStringApproach1WithoutStringBuilder(String str, int times){
        String output = str;
        int length = str.length();

        for(int i=0; i< times; i++){  // Time Complexity: O(n)
            char ch = output.charAt(0);
            String temp = output.substring(1, length); // Time Complexity: O(n)
            output = temp + ch;
            System.out.println("String after " + (i+1) + " rotation: " + output);
        }  // Total Time Complexity: O(n) * O(n) -> O(n^2)

        return output;
    }


    // Time Complexity: O(N) ....
    public static String rotateStringApproach2(String str, int times){
        int length = str.length();

        /**
         * If times is greater than the length of the string, it results in unnecessary full rotations
         * (e.g., rotating "abc" 3 times results in the same string). Using modulo ensures we effectively rotate the string only for the remainder of the necessary rotations:
         * Example: Rotating "abcde" 7 times is the same as rotating it 2 times because 7 % 5 = 2.
         */
        times = times % length;

        String output = str.substring(times, length) + str.substring(0, times);
        System.out.println("Rotated string: " + output);
        return output;
    }

    // Time Complexity: O(N) ....
    public static String rotateStringApproach3(String str, int times){
        int length = str.length();

        /**
         * If times is greater than the length of the string, it results in unnecessary full rotations
         * (e.g., rotating "abc" 3 times results in the same string). Using modulo ensures we effectively rotate the string only for the remainder of the necessary rotations:
         * Example: Rotating "abcde" 7 times is the same as rotating it 2 times because 7 % 5 = 2.
         */
        times = times % length;
        str = str + str;

        String output = str.substring(times, length + times);
        System.out.println("Rotated string: " + output);
        return output;
    }



}

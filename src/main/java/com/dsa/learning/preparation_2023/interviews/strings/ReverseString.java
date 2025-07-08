package com.dsa.learning.preparation_2023.interviews.strings;

import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Source -
 * https://www.geeksforgeeks.org/reverse-words-in-a-given-string/
 * https://www.geeksforgeeks.org/convert-character-array-to-string-in-java/
 */
public class ReverseString {

    public static void main(String[] args) {

        String inputString = "I am Rahul Soni";

        char[] inputCharArray = inputString.toCharArray();

        String outputString = reverseString(inputCharArray);

        System.out.println("outputString: " + outputString);

    }


    private static String reverseString(char[] inputCharArray) {
        String outputString;
        char[] tempCharArray = new char[inputCharArray.length];
        int j = 0;

        for (int i = inputCharArray.length - 1; i >= 0; i--) {
            tempCharArray[j++] = inputCharArray[i];
        }

        outputString = convertCharArrayToString(tempCharArray);

        return outputString;
    }


    /**
     * Source - https://www.geeksforgeeks.org/convert-character-array-to-string-in-java/
     *
     * @param inputCharArray
     * @return
     */
    private static String convertCharArrayToString(char[] inputCharArray) {

        System.out.println("--------------------------------------------------------");

        /**
         *  Method 1: Using valueOf() method of String class
         *
         *  Another way to convert a character array to a string is to use the valueOf() method present in the String class.
         *  This method inherently converts the character array to a format where the entire value of the characters present
         *  in the array is displayed. This method generally converts int, float, double, char, boolean, and even object to a string.
         */
        String convertedStringUsingValueOf;
        convertedStringUsingValueOf = String.valueOf(inputCharArray);
        System.out.println("convertedStringUsingValueOf: " + convertedStringUsingValueOf);

        /**
         *  Method 2: Using StringBuilder class.
         *
         *  Since a StringBuilder is a mutable class, therefore, the idea is to iterate through the character array
         *  and append each character at the end of the string. Finally, the string contains the string form of the characters.
         */
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < inputCharArray.length; i++) {
            stringBuilder.append(inputCharArray[i]);
        }
        String convertedStringUsingStringBuilder = stringBuilder.toString();
        System.out.println("convertedStringUsingStringBuilder: " + convertedStringUsingStringBuilder);


        /**
         * Method 3: Using copyOf() method of Array class
         *
         * The given character can be passed into the String constructor. By default, the character array contents
         * are copied using the Arrays.copyOf() method present in the Arrays class.
         */
        String convertedStringUsingNewStringObject = new String(inputCharArray);
        System.out.println("convertedStringUsingNewStringObject: " + convertedStringUsingNewStringObject);

        /**
         * Method 4: Using Collectors in Streams
         *
         * With the introduction of streams in java8, we straight away use Collectors in streams to modify our character
         * input array elements and later uses joining() method and return a single string and print it.
         */
        String convertedStringUsingStream =
                Stream.of(inputCharArray)
                        .map(arr -> new String(arr))
                        .collect(Collectors.joining());
        System.out.println("convertedStringUsingStream: " + convertedStringUsingStream);

        return convertedStringUsingStream;
    }
}

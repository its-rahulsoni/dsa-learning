package com.dsa.learning.string.dial_epam.easy;

public class ConvertStringIntoInteger {

    public static void main(String[] args) {
        // Example usage
        try {
            System.out.println(stringToIntConversion("12345"));           // Output: 12345
//            System.out.println(stringToInt("   -9876  "));      // Output: -9876
//            System.out.println(stringToInt("+42"));             // Output: 42
//            System.out.println(stringToInt("0"));               // Output: 0
            System.out.println(stringToIntConversion("-2147483648"));     // Output: -2147483648
//            System.out.println(stringToInt("2147483647"));      // Output: 2147483647
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int stringToIntConversion(String input){
        int output = 0;
        int temp = 0;
        boolean isSignedNumber = false;
        char signedChar = '-';

        for(char ch : input.toCharArray()){
            if(ch == '-' || ch == '+'){
                isSignedNumber = true;
                signedChar = ch;
            } else{
                temp = ch - '0';
                output = (output * 10) + temp;
            }

        }

        if(isSignedNumber){
            if(signedChar == '-'){
                output = output * -1;
            }
        }
        System.out.println("output: " + output);

        return output;
    }

    public static int stringToInt(String input) {
        // Step 1: Handle null or empty strings
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        // Step 2: Trim leading and trailing spaces
        input = input.trim(); // Remove spaces around the string

        // Step 3: Handle optional '+' or '-' sign
        int sign = 1; // Default is positive
        int startIndex = 0; // Index from where digits start
        if (input.charAt(0) == '-') {
            sign = -1; // Mark as negative
            startIndex = 1; // Skip the '-' sign
        } else if (input.charAt(0) == '+') {
            startIndex = 1; // Skip the '+' sign
        }

        // Step 4: Convert each digit to its numeric value
        int result = 0;
        for (int i = startIndex; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Step 4.1: Check if the character is a valid digit
            if (currentChar < '0' || currentChar > '9') {
                throw new IllegalArgumentException("Invalid character found: " + currentChar);
            }

            // Step 4.2: Convert the character to its numeric value
            int digitValue = currentChar - '0'; // ASCII conversion

            // Step 4.3: Check for integer overflow/underflow
             if (result > (Integer.MAX_VALUE - digitValue) / 10) {
                throw new ArithmeticException("Integer overflow occurred.");
            }

            // Step 4.4: Build the result
            result = result * 10 + digitValue;
        }

        // Step 5: Apply the sign at the end
        return result * sign;
    }


}

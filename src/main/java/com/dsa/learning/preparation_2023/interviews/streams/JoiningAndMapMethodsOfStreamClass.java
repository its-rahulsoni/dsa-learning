package com.dsa.learning.preparation_2023.interviews.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoiningAndMapMethodsOfStreamClass {

    public static void main(String[] args) {

        // Creating a custom character array
        char[] inputCharArray = {'G', 'e', 'e', 'k', 's', 'f', 'o',
                'r', 'G', 'e', 'e', 'k', 's'};

        /**
         * joining() Method -
         *
         * The joining() method of Collectors Class, in Java, is used to join various elements of a character or
         * string array into a single string object. This method uses the stream to do so.
         *
         * java.util.stream.Collectors.joining() is the most simple joining method which does not take any parameter.
         * It returns a Collector that joins or concatenates the input streams into String in the order of their appearance.
         *
         *
         * map() Method -
         *
         * Stream map(Function mapper) returns a stream consisting of the results of applying the given function to the elements
         * of this stream.
         *
         * Java 8 Stream.map() converts Stream<X> to Stream<Y>. For each object of type X, a new object of type Y is created
         * and put in the new Stream.
         *
         * Stream map(Function mapper) is an intermediate operation. These operations are always lazy. Intermediate operations
         * are invoked on a Stream instance and after they finish their processing, they give a Stream instance as output.
         *
         * Description -
         *
         * The map() is an intermediate operation. It returns a new Stream as return value.
         * The map() operation takes a Function, which is called for each value in the input stream and produces one result value sent to the output stream.
         * The mapper function used for transformation is a stateless function (does not store the information of previously processed objects) and returns only a single value.
         * The map() method is used when we want to convert a Stream of X to Stream of Y.
         * The mapped stream is closed after its contents have been placed into the new output stream.
         * map() operation does not flatten the stream as flatMap() operation does.
         */
        System.out.println("Example 1 - Converting an array of characters into a string list.");

        String outputString1 =
                Stream.of(inputCharArray)
                        // This converts each char elem into a String & returns a stream of such strings ....
                        .map(eachCharElementInArray -> new String(eachCharElementInArray))
                        // This basically collects the stream of strings passed from above intermediate function map() and joins them into a single string object ....
                        .collect(Collectors.joining());

        String outputString2 =
                Stream.of(inputCharArray)
                        // This converts each char elem into a String & returns a stream of such strings ....
                        .map(String::valueOf)
                        // This one also works in the same way as above ....
                        //.map(eachCharElementInArray -> String.valueOf(eachCharElementInArray))
                        // This basically collects the stream of strings passed from above intermediate function map() and joins them into a single string object ....
                        .collect(Collectors.joining());

        System.out.println("outputString1: " + outputString1);
        System.out.println("outputString2: " + outputString2);

        System.out.println("--------------------------------------------------------");

        System.out.println("Example 2 - Converting an array of integers by multiplying each element by 3 into an integer list.");
        // Creating a list of Integers ....
        List<Integer> listOfIntegers = Arrays.asList(3, 6, 9, 12, 15);

        // Multiplying each list element by 3 and displaying the modified value ....
        listOfIntegers.stream()
                .map(eachIndividualListValue -> eachIndividualListValue * 3)
                // Approach #1 - This will just print each modified value ....
                //.forEach(System.out::println)

                // Approach #2 - This will print each modified value along with some other string value ....
                .forEach(modifiedListElement -> System.out.println("Modified List Element: " + modifiedListElement));

        List<Integer> modifiedListByMultiplyingEachNumber = listOfIntegers.stream()
                .map(eachListElement -> eachListElement * 3)
                .collect(Collectors.toList());

        System.out.println("New updated list ....");
        modifiedListByMultiplyingEachNumber.stream().forEach(System.out::println);

        System.out.println("Existing list remains intact ....");
        listOfIntegers.stream().forEach(System.out::println);


        System.out.println("--------------------------------------------------------");

        System.out.println("Example 3 - Converting an array of integers stored as strings into an integer list.");
        List<String> listOfStrings = Arrays.asList("1", "2", "3", "4", "5");

        List<Integer> listOfConvertedIntegers = listOfStrings.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        System.out.println(listOfConvertedIntegers);


    }
}

package com.dsa.learning.preparation_2023.interviews.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sources -
 * https://howtodoinjava.com/java8/stream-map-vs-flatmap/
 */
public class MapAndFlatMapOfStreamClass {

    public static void main(String[] args) {

        List<String> listOfStrings22 = Arrays.asList("1", "2", "1", "4", "5");

        System.out.println("index of: " + listOfStrings22.indexOf("1"));

        /**
         * In Java, Stream interface has >map() and flatMap() methods and both are intermediate stream operations and return
         * another stream as method output. The primary difference between map() vs flatMap() is the return type of both methods.
         *
         * map() is used for transformation only, but flatMap() is used for both transformation and flattening.
         *
         * flatMap() = map() + Flattening
         *
         * Usage of map() vs flatMap()
         *
         * We can use map() operation when we have a stream of objects, and we need to get some unique value for each element
         * in the stream. There is one-to-one mapping between input and output element. For example,
         * we can write a program to find the date of birth of all employees in a stream of employees.
         *
         * In case of flatMap(), a one-to-many mapping is created where for each input element/stream,
         * we first get a multiple values and then we flatten the values from all such input streams into a single output stream.
         * For example, we may write program to find all district words from all lines in a text file.
         */

        /**
         * The map() method produces one output value for each input value in the stream.
         * So if there are n elements in the stream, map() operation will produce a stream of n output elements.
         */
        List<String> listOfStrings = Arrays.asList("1", "2", "3", "4", "5");

        List<Integer> listOfIntegers =
                listOfStrings.stream()
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());

        System.out.println("listOfIntegers : " + listOfIntegers);

        /**
         * flatMap()
         *
         * It is two step process i.e. map() + Flattening. It helps in converting Collection<Collection<T>> to Collection<T>.
         */
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        List<Integer> flattenedListOfIntegers =
                listOfLists.stream()
                        // This firstly converts a list of list into an individual list and pass it further as a stream of list
                        // (only ONE list at a time) ....
                        .flatMap(individualList -> individualList.stream())
                        // This secondly receives a stream of ONE list and multiplies each element by 2 of this list and ends to next method....
                        .map(eachElementOfIndividualList -> eachElementOfIndividualList * 2)
                        // This method thirdly, collects each individual modified element returned from above map() and stores it into a list ....
                        .collect(Collectors.toList());

        System.out.println("flattenedListOfIntegers: " + flattenedListOfIntegers);
    }

}

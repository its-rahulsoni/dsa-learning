package com.dsa.learning.java8.streams.intermediate.flatMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * flatMap:
 * This method basically returns a Stream. So all we need to do is to create a stream out of the sub-list/object of the original List.
 */
public class FlatMapUsages {

    public static void main(String[] args) {

        // 01. Flatten a List of Lists ....
        flattenAListOfLists();

        // 02. Flatten a Stream of Strings into Words ....
        flattenAStreamOfStringsIntoWords();

        // 03. Flatten a Stream of Optional Values ....
        flattenAStreamOfOptionalValues();

        // 04. Process Nested JSON-like Structures ....
        processNestedJSONLikeStructures();

        // 05. Combination with map, filter, and distinct ....
        combineFlatMapWithOtherOperations();

    }

    /**
     * 01. Flatten a List of Lists
     * Suppose you have a List<List<Integer>> and want to convert it into a single List<Integer>.
     *
     * Explanation
     * nestedLists.stream():
     * Stream of sublists (Stream<List<Integer>>).
     *
     * flatMap(List::stream):
     * Converts each sublist into a stream (Stream<Integer>), then merges all sub-streams into a single stream.
     *
     * subList -> subList.stream():
     * It is a lambda expression that takes each subList (a List from the original nestedLists) and returns a stream from it. This performs flatenning for us.
     * This lambda is equivalent to the method reference List::stream.
     *
     * collect(Collectors.toList()):
     * Converts the flattened single stream into a list.
     */
    public static void flattenAListOfLists(){
        System.out.println("**** flattenAListOfLists() ****");
        List<List<Integer>> nestedLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8)
        );

        List<Integer> flattenedList = nestedLists.stream()
                .flatMap(subList -> subList.stream()) // The core logic here is that we fetch the list within each list index. And then we create a stream out of that sub-list.
                .collect(Collectors.toList());

        List<Integer> flatList = nestedLists.stream()
                .flatMap(List::stream) // Flatten each sublist
                .collect(Collectors.toList());

        System.out.println("flattenedList: " + flattenedList);

        addDivider();
    }

    /**
     * 02. Flatten a Stream of Strings into Words.
     * Suppose you have a stream of sentences and want to extract all unique words.
     *
     * The core logic here is that we have string objects and NOT sub-lists here. So we split each string into a list of words and then create a Stream of this list of words.
     *
     * Explanation
     * sentence.split(" "):
     * Splits each sentence into an array of words.
     *
     * List.of(sentence.split(" ")):
     * Converts the array of words into a list.
     *
     * flatMap(...):
     * Flattens all word lists into a single stream of words.
     *
     * distinct():
     * Filters out duplicate words.
     */
    public static void flattenAStreamOfStringsIntoWords(){
        System.out.println("**** flattenAStreamOfStringsIntoWords() ****");
        List<String> sentences = List.of(
                "Java is cool programming",
                "I love programming",
                "Streams are powerful"
        );

        List<String> sentenceOutput = sentences.stream()
                .flatMap(sentence -> List.of(sentence.split(" ")).stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Sentence: " + sentenceOutput);

        addDivider();
    }

    /**
     * 03. Flatten a Stream of Optional Values ....
     * Suppose you have a stream of Optional<String> and want to extract only the non-empty values as a single flat stream.
     *
     * Explanation
     * Optional::stream:
     * Converts non-empty Optional values into a stream containing the value.
     *
     * Optional.empty() produces an empty stream.
     *
     * flatMap(Optional::stream):
     * Combines all non-empty streams into a single flat stream.
     *
     * Note: The flatMap() operation is used to "flatten" streams by combining multiple nested streams into a single continuous stream.
     * Here's what happens during the flatMap(opt -> opt.stream()) step:
     *
     * For each Optional<T>, opt.stream() is called.
     * If opt contains a value, the resulting stream will contain that single value.
     * If opt is empty, the resulting stream will contain nothing (i.e., it's an empty stream).
     *
     * The flatMap() then combines these individual streams into a single continuous stream. Since empty streams contribute no elements,
     * the values present in non-empty Optionals are the only ones that remain in the resulting flattened stream.
     * This means empty Optionals are implicitly filtered out without any need for explicit filtering.
     */
    public static void flattenAStreamOfOptionalValues(){
        System.out.println("**** flattenAStreamOfOptionalValues() ****");

        List<Optional<String>> optionalList = List.of(
                Optional.of("Hello"),
                Optional.empty(),
                Optional.of("World"),
                Optional.empty()
        );

        List<String> output = optionalList.stream()
                .flatMap(opt -> opt.stream())
                .filter(a -> !a.isEmpty()) // Not needed as the above Optional streaming automatically filters out the empty streams.
                .collect(Collectors.toList());
        System.out.println("Non empty Optional strings: " + output);

        addDivider();
    }


    /**
     * 04. Process Nested JSON-like Structures.
     * Suppose you have a list of categories, where each category contains subcategories. Flatten the entire structure to extract all subcategory names.
     */
    public static void processNestedJSONLikeStructures(){
        System.out.println("**** processNestedJSONLikeStructures() ****");

        // Point to be Noted here: List of Objects and NOT list of lists ....
        List<Category> categories = List.of(
                new Category("Programming", List.of("Java", "Python", "SQL")),
                new Category("Sports", List.of("Football", "Basketball")),
                new Category("Music", List.of("Rock", "Pop"))
        );

        List<String> subcategories = categories.stream()
                        .flatMap(cat -> cat.getSubcategories().stream()) // Flatten subcategories: Meaning we're first fetching the list of sub-cat & then creating a stream of it ....
                                .collect(Collectors.toList());
        System.out.println("Sub Categories: " + subcategories);

        addDivider();
    }


    /**
     * 05. Combination with map, filter, and distinct.
     * Flatten nested lists and extract unique even numbers.
     *
     * Explanation
     * flatMap(List::stream):
     * Flattens the nested lists into a single stream of integers.
     *
     * filter(num -> num % 2 == 0):
     * Keeps only even numbers.
     *
     * distinct():
     * Removes duplicates.
     */
    public static void combineFlatMapWithOtherOperations(){
        System.out.println("**** combineFlatMapWithOtherOperations() ****");

        // This is List of Lists .... And NOT list of Objects ....
        List<List<Integer>> nestedLists = List.of(
                List.of(1, 2, 3, 4),
                List.of(3, 4, 5, 6),
                List.of(6, 7, 8, 9)
        );

        List<Integer> uniqueEvenNumbers = nestedLists.stream()
                        .flatMap(subList -> subList.stream())
                                .filter(a -> a % 2 == 0)
                                        .collect(Collectors.toUnmodifiableList());

        System.out.println("Unique Even Numbers: " + uniqueEvenNumbers);

        addDivider();
    }

    public static void addDivider(){
        System.out.println("\n------------------------------------------");
    }

    static class Category {
        String name;
        List<String> subcategories;

        Category(String name, List<String> subcategories) {
            this.name = name;
            this.subcategories = subcategories;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getSubcategories() {
            return subcategories;
        }

        public void setSubcategories(List<String> subcategories) {
            this.subcategories = subcategories;
        }
    }

}

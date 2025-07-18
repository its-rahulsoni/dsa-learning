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

        // 06. Flatten a List of Arrays ....
        flattenAListOfArrays();

        // 07. Flatten a List of Files and Extract File Names ....
        flattenAListOfFilesAndExtractFileNames();

        // 08. Flatten a Multi-Level List ....
        flattenAMultiLevelList();

        // 09. Combine Two Streams Using flatMap() ....
        combineTwoStreams();
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
                .flatMap(sentence -> List.of(sentence.split(" ")).stream()) // Since, we require a list here to convert it into a Stream. So we're first creating a List of Words and then creating a stream() out of it ....
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

    /**
     * 06. flattenAListOfArrays
     * You have a List<String[]> where each array contains strings. Convert it into a flattened list of strings.
     *
     * Explanation:
     * Arrays::stream:
     * Converts each array (String[]) into a stream.
     *
     * flatMap(Arrays::stream) OR flatMap(arr -> List.of(arr).stream()):
     * Works on each array, flattens them into a single stream of String. By first converting them into a list and then into a stream.
     *
     * NOTE: We can not convert an array into a stream.
     *
     * collect(Collectors.toList()):
     * Collects all strings into one flat list.
     */
    public static void flattenAListOfArrays(){
        System.out.println("**** flattenAListOfArrays() ****");

        List<String[]> stringArrays = List.of(
                new String[]{"a", "b", "c"},
                new String[]{"d", "e"},
                new String[]{"f", "g", "h"}
        );

        List<String> flattenedListOfStrings = stringArrays.stream()
                        .flatMap(arr -> List.of(arr).stream()) // flatMap(Arrays::stream): would also work the same. Concept is to create a List of Array elements & then convert it into a stream ....
                                .collect(Collectors.toUnmodifiableList());

        System.out.println("Flattened List Of Strings: " + flattenedListOfStrings);

        addDivider();
    }


    /**
     * 07. Flatten a List of Files and Extract File Names.
     * You have a List<Directory> where each directory contains a list of files. Flatten all filenames into a single list.
     *
     * Explanation:
     * dir -> dir.files.stream():
     * For each Directory, get its stream of files.
     *
     * flatMap(dir -> dir.files.stream()):
     * Flattens all streams into one continuous stream.
     *
     * Result:
     * All filenames in a single flat list.
     */
    public static void flattenAListOfFilesAndExtractFileNames(){
        System.out.println("**** flattenAListOfFilesAndExtractFileNames() ****");

        List<Directory> directories = List.of(
                new Directory("Dir1", List.of("file1.txt", "file2.txt")),
                new Directory("Dir2", List.of("file3.txt")),
                new Directory("Dir3", List.of("file4.txt", "file5.txt", "file6.txt"))
        );

        List<String> directoryNamesList = directories.stream()
                        .flatMap(dir -> dir.getFiles().stream())
                                .collect(Collectors.toUnmodifiableList());

        System.out.println("Flattened List Of Directories: " + directoryNamesList);

        addDivider();
    }

    /**
     * 08. Flatten a Multi-Level List Using flatMap()
     * You have a deeply nested list (List<List<List<Integer>>>) and need to flatten it to a single list of integers.
     *
     * Explanation:
     * Level-Wise Flattening:
     * First .flatMap(level1 -> level1.stream()): Flattens List<List<List<Integer>>> into List<List<Integer>>.
     * Second .flatMap(level2 -> level2.stream()): Flattens List<List<Integer>> into List<Integer>.
     *
     * Final Result:
     * Single flat list of integers.
     */
    public static void flattenAMultiLevelList(){
        System.out.println("**** flattenAMultiLevelList() ****");

        List<List<List<Integer>>> deepNestedLists = List.of(
                List.of(List.of(1, 2, 3), List.of(4, 5)),
                List.of(List.of(6, 7), List.of(8, 9)),
                List.of(List.of(10, 11))
        );

        List<Integer> multiLevelFlatList = deepNestedLists.stream()
                        .flatMap(subList1 -> subList1.stream()) // First flattening, gives us a List that contains internally lists ....
                                .flatMap(subList2 -> subList2.stream()) // This flatenning gives us the elements within internal lists ....
                                        .collect(Collectors.toUnmodifiableList());

        System.out.println("Flattened Multi Level List: " + multiLevelFlatList);

        addDivider();
    }


    /**
     * 09. Combine Two Streams Using flatMap()
     * You have two lists (List<String> and List<Integer>) and need to create all possible pairs (Cartesian Product).
     *
     * Main concept behind this is that we need to stream over each element of list1 and then for each such element we need to create a stream of Second list.
     * We can then apply Map method to second stream and add both these elements together which are finnaly collected into a final List.
     *
     * Explanation:
     * Outer Stream (letters.stream()):
     * Iterates over each letter (A, B, C).
     *
     * Inner Stream (numbers.stream()):
     * Iterates over each number (1, 2) for every letter.
     *
     * flatMap(...):
     * Combines letter and number to form pairs.
     */
    public static void combineTwoStreams(){
        System.out.println("**** combineTwoStreams() ****");

        List<String> letters = List.of("A", "B", "C");
        List<Integer> numbers = List.of(1, 2);

        List<String> cartesianProduct = letters.stream()
                        .flatMap(letter ->  numbers.stream() // Second Stream of List created inside the stream of First list ....
                                .map(number -> letter + number))
                                .collect(Collectors.toUnmodifiableList());

        System.out.println("Cartesian Product by combining Two Streams: " + cartesianProduct);

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

    static class Directory {
        String name;
        List<String> files;

        Directory(String name, List<String> files) {
            this.name = name;
            this.files = files;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getFiles() {
            return files;
        }

        public void setFiles(List<String> files) {
            this.files = files;
        }
    }

}

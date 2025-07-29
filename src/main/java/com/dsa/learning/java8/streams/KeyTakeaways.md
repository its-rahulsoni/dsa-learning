01.
List<String> sentences = List.of(
"Hello world",
"Java streams",
"flatMap example"
);

When presented with strings/sentences in a list of lists. 
We need to use FlatMap to convert it into a stream of words.
Since, its just a string and we require a Collection like List to create a Stream out of it,
so we separate each word from the string/sentence by using delimiter " ".
And then collect these words/strings into a List. 
We now, can create a Stream out of this List of Words/Strings.

sentences.stream()
.flatMap(sentences -> List.of(sentences.split(" ").stream())

---

02.
Optional<Optional<String>> nestedOptional = Optional.of(Optional.of("value"));

Optional is NOT a Stream. So we don't have to use .stream()
We can directly use .flatMap(innerOptional -> innerOptional).
This will flatten the Optional.

---

03.
Map<String, List<Integer>> map = Map.of(
"A", List.of(1, 2),
"B", List.of(3, 4)
);

Approach #01: We can apply stream on Map's entrySet(). In this approach inside flatMap, we will have object of entrySet type.
We can use this entrySet object to fetch keys and values.

List<Integer> output2 = map.entrySet().stream()
.flatMap(entry -> entry.getValue().stream())
.toList();

Approach #02: To convert a Map's Value to stream, we apply a stream on its values() only - map.values().stream()

List<Integer> output = map.values().stream()
.flatMap(subList -> subList.stream())
.toList();

---

04.
Collectors.groupingBy(): Working ....


    Map<String, Long> wordCountMap = words.stream()
             .collect(Collectors.groupingBy(
                        w -> w, // Key: the word itself ....
                        Collectors.counting() // Value: count occurrences ....
               ));
How This Works:
groupingBy(w -> w)
Groups the stream elements (words) by their identity (the word itself).

Creates a Map<String, List<String>> temporarily:

    {
      "apple"     : ["apple"],
      "blueberry" : ["blueberry", "blueberry"],
      "apricot"   : ["apricot", "apricot", "apricot"],
      ...
    }

Collectors.counting()

Replaces each List<String> with its size (count of elements).
Internally, it uses:
Collectors.reducing(0L, e -> 1L, Long::sum)
Maps each element to 1L, then sums them up.

Final Result
Converts the intermediate Map<String, List<String>> to Map<String, Long>

---

05.
Creating a Stream of characters form a String.

String input = "banana";

    Map<Character, Long> output = input.chars()
                        .mapToObj(ch -> (char)ch)
                        .collect(Collectors.groupingBy(
                             ch -> ch,
                             Collectors.counting()
                         ));

Java does not have a Stream of char built-in. Since primitive types (char, int, etc.) are not objects, Java uses specialized streams for primitives (e.g., IntStream, DoubleStream, LongStream) instead of Stream<char> to optimize for performance.

    String input = "banana";
    IntStream stream = input.chars(); 

.chars() is used to create a stream of chars but in their INT form i.e. ASCII code. So we need to convert them to char objects.

Why mapToObj(ch -> (char) ch) Is Needed
To work with individual characters (char) as objects in the stream pipeline (like grouping by them or counting them), we need a Stream<Character>, not an IntStream.
Therefore, we map each integer codepoint (int) to its corresponding character (char) using mapToObj(ch -> (char) ch):

    input.chars()
    .mapToObj(ch -> (char) ch);  // Converts IntStream to Stream<Character>

Here:

input.chars() produces an IntStream of Unicode integers.

mapToObj(ch -> (char) ch) maps each integer to its corresponding char (b, a, n, ...).

Now, the resulting stream is a Stream<Character, which can directly work with Collectors and other stream operations.

---

06.
Adding values within a sub-list using mapToInt()

    Input nested list of integers:
    List<List<Integer>> nestedLists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8),
                List.of(2)
        );

    OptionalInt max = nestedLists.stream()
                .mapToInt(subList -> subList.stream().mapToInt(a -> a).sum())
                .max();

This can be used with calculating sum of int of a list -> 
    
    mapToInt(a -> a).sum();

---

07.
Using Comparator - just stick to one universal implementation

    Comparator.comparing(object -> object.getxxx());

Here, we need to pass an object of any type within the constructor ....

For primitive types - we can use:

    Comparator.comparingInt(emp -> emp.getAge());
    Comparator.comparingDouble(emp -> emp.getSalary());

---

08.
In a Nested-List, when we need to return a sub-list whose int values sum the max, we can use .reduce():

     Optional<List<Integer>> output = nestedLists.stream()
                .reduce((l1, l2) -> {
                    int a = l1.stream().mapToInt(Integer::intValue).sum();
                    int b = l2.stream().mapToInt(Integer::intValue).sum();
                    return a > b ? l1 : l2;
                });


To make the `sorted` method sort the strings **in descending order of length**, you simply need to reverse the comparison logic in the sorting function. Instead of comparing `a.length()` with `b.length()` as `Integer.compare(a.length(), b.length())`, you can flip them and use `Integer.compare(b.length(), a.length())`.

Here’s the updated code:

```java
List<String> output = strings.stream()
        .sorted((a, b) -> Integer.compare(b.length(), a.length())) // Reversed comparison for descending order
        .limit(3)
        .toList();
```

### Explanation:
1. **Original Sorting Logic (Ascending)**:
   ```java
   Integer.compare(a.length(), b.length())
   ```
    - Compares `a` and `b` such that smaller lengths come first.
    - Result: Ascending order.

2. **Reversed Sorting Logic (Descending)**:
   ```java
   Integer.compare(b.length(), a.length())
   ```
    - By swapping `b` and `a` in the arguments, longer lengths come first.
    - Result: Descending order.

---

### Full Example:

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = List.of("banana", "apple", "cherry", "blueberry", "pineapple", "pear");

        // Sort strings by length in descending order and take the top 3
        List<String> output = strings.stream()
                .sorted((a, b) -> Integer.compare(b.length(), a.length())) // Descending order
                .limit(3) // Take the top 3
                .toList();

        System.out.println(output); // Outputs: [pineapple, blueberry, banana]
    }
}
```

### Output:
```
[pineapple, blueberry, banana]
```

---

### Alternative Approach: Using `Comparator.comparing`
Java provides a clean way to create custom comparators using `Comparator.comparing`. For descending order, you combine `Comparator.comparing` with `.reversed()`.

Here’s the equivalent solution using `Comparator.comparing`:

```java
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = List.of("banana", "apple", "cherry", "blueberry", "pineapple", "pear");

        // Sort strings by length in descending order and take the top 3
        List<String> output = strings.stream()
                .sorted(Comparator.comparing(String::length).reversed()) // Cleaner descending comparator
                .limit(3)
                .toList();

        System.out.println(output); // Outputs: [pineapple, blueberry, banana]
    }
}
```

### Explanation:
1. `Comparator.comparing(String::length)`:
    - Creates a comparator to sort by the length of the strings.
2. `.reversed()`:
    - Reverses the comparator to sort in descending order.

### Output:
```
[pineapple, blueberry, banana]
```

---

### Key Points:
- Use `Integer.compare(b.length(), a.length())` to directly handle descending order manually.
- Or use `Comparator.comparing(...).reversed()` for a more declarative (and cleaner) approach. Both approaches produce the same result.


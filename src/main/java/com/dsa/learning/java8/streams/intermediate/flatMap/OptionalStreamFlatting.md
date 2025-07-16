This behavior occurs because of the way `Optional.stream()` works and how the `flatMap()` operation processes it behind the scenes. Let's break it down step by step and explain why removing the `.filter()` still results in a list of non-empty strings:

---

### 1. **Understanding `Optional.stream()`**
The `Optional.stream()` method converts an `Optional<T>` into a `Stream<T>` as follows:
- If the `Optional` contains a value (`Optional.of(value)`), `Optional.stream()` produces a stream with **exactly one element**, the value itself.
- If the `Optional` is empty (`Optional.empty()`), `Optional.stream()` produces an **empty stream**.

This operation is crucial because streams that are empty simply "disappear" when flattened during a `flatMap()` operation.

---

### 2. **Behavior of `flatMap()`**
The `flatMap()` operation is used to "flatten" streams by combining multiple nested streams into a single continuous stream. Here's what happens during the `flatMap(opt -> opt.stream())` step:
- For each `Optional<T>`, `opt.stream()` is called.
    - If `opt` contains a value, the resulting stream will contain that single value.
    - If `opt` is empty, the resulting stream will contain nothing (i.e., it's an empty stream).
- The `flatMap()` then combines these individual streams into a single continuous stream. Since empty streams contribute no elements, the values present in non-empty `Optional`s are the only ones that remain in the resulting flattened stream.

This means empty `Optional`s are implicitly filtered out **without any need for explicit filtering**.

---

### 3. **Why `filter(a -> !a.isEmpty())` is redundant**
When you remove the `.filter(a -> !a.isEmpty())`, here's what happens:
- Empty `Optional`s (i.e., `Optional.empty()`) don't contribute any elements to the stream because `Optional.stream()` produces empty streams for them.
- Therefore, the resulting stream only consists of the non-empty strings from the non-empty `Optional`s (`"Hello"` and `"World"` in this case).

The `filter(a -> !a.isEmpty())` would check for whether a string is empty (e.g., `""`), but in this case, all strings from the `Optional` have meaningful values and are not empty. Thus, the `filter()` doesn't affect the result.

---

### Behind the Scenes Flow Step-by-Step:
Let's trace the example:

#### Input List:
```java
List<Optional<String>> optionalList = List.of(
    Optional.of("Hello"),
    Optional.empty(),
    Optional.of("World"),
    Optional.empty()
);
```

#### Stream Operations:
1. **Stream starts**: `optionalList.stream()` produces a stream of four `Optional`s: `[Optional.of("Hello"), Optional.empty(), Optional.of("World"), Optional.empty()]`.

2. **flatMap(opt -> opt.stream())**:
    - For `Optional.of("Hello")`: `opt.stream()` produces a stream with one element: `Stream.of("Hello")`.
    - For `Optional.empty()`: `opt.stream()` produces an empty stream.
    - For `Optional.of("World")`: `opt.stream()` produces a stream with one element: `Stream.of("World")`.
    - For `Optional.empty()`: `opt.stream()` produces an empty stream.
    - After flattening all these streams, we are left with a stream containing just the non-empty values: `["Hello", "World"]`.

3. **filter(a -> !a.isEmpty())**:
    - This step would exclude empty strings (`""`), but since `"Hello"` and `"World"` are not empty, the stream remains unchanged.

4. **collect(Collectors.toList())**:
    - Finally, the elements of the stream (`["Hello", "World"]`) are collected into a list.

---

### Final Result:
The final `output` list contains `["Hello", "World"]`.

---

### Key Insight:
You don't need the explicit `filter(a -> !a.isEmpty())` because `Optional.stream()` already excludes values from `Optional.empty()` implicitly via empty streams. The filtering step would only be necessary if you were dealing with **empty strings** (`""`) in non-empty `Optional`s, but that's not the case here.


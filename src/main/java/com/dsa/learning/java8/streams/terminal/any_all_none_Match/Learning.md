This is a great question! Let’s break it down step by step to understand *why replacing `anyMatch()` with `allMatch()` causes only the last sublist to be returned*.

---

### The Key Difference Between `anyMatch()` and `allMatch()`:

#### 1. **`allMatch(Predicate)`**
- **Definition:** Returns `true` if **all elements** in the stream match the given predicate (or the stream is empty).
- **Behavior:** If **even one element** in the stream does not match the predicate, it immediately returns `false` (short-circuits evaluation).

#### 2. **`anyMatch(Predicate)`**
- **Definition:** Returns `true` if **any one element** in the stream matches the given predicate, even if other elements do not match.
- **Behavior:** As soon as a single element matches the predicate, it immediately returns `true` (short-circuits evaluation).

---

### What Happens in Your Code When You Use `allMatch()` Instead of `anyMatch()`?

#### Your Code:
```java
.filter(team -> team.stream().allMatch(task -> task.isCompleted) &&
                team.stream().allMatch(task -> task.priority == 5))
```

Let’s analyze this condition part by part:

1. **First Condition (`allMatch(task -> task.isCompleted)`):**
    - This ensures that **all tasks** in a team are completed (`task.isCompleted` is `true`).
    - The sublist passes this condition if:
        - Every task in the team has `isCompleted == true`.

2. **Second Condition (`allMatch(task -> task.priority == 5)`):**
    - This ensures that **all tasks** in a team have the high priority (`priority == 5`).
    - The sublist passes this condition if:
        - Every task in the team has `priority == 5`.

Using `allMatch` here means that **both conditions must be satisfied for the same sublist**. If a single task within the sublist violates either of the two conditions, the entire sublist will be excluded.

---

### Why Only the Last Sublist Passes?

Let’s walk through the example input:

```java
List<List<Task>> tasksByTeam = List.of(
    List.of(new Task("Task1", 5, true), new Task("Task2", 3, true)), // 2nd task fails "priority == 5"
    List.of(new Task("Task3", 4, false), new Task("Task4", 5, true)), // 1st task fails "completed"
    List.of(new Task("Task5", 5, true), new Task("Task6", 5, true))  // Passes both conditions
);
```

1. **First Sublist (`Task1` and `Task2`):**
   ```java
   team.stream().allMatch(task -> task.isCompleted) // true
   team.stream().allMatch(task -> task.priority == 5) // false
   ```
    - **Why?** Task2 has `priority == 3`, so the second condition (`allMatch(task -> task.priority == 5)`) fails.
    - Result: This sublist is excluded.

2. **Second Sublist (`Task3` and `Task4`):**
   ```java
   team.stream().allMatch(task -> task.isCompleted) // false
   team.stream().allMatch(task -> task.priority == 5) // N/A (not evaluated since the first condition is false)
   ```
    - **Why?** Task3 has `isCompleted == false`, so the first condition (`allMatch(task -> task.isCompleted)`) fails.
    - Result: This sublist is excluded.

3. **Third Sublist (`Task5` and `Task6`):**
   ```java
   team.stream().allMatch(task -> task.isCompleted) // true
   team.stream().allMatch(task -> task.priority == 5) // true
   ```
    - **Why?** Both tasks satisfy both `isCompleted == true` and `priority == 5`.
    - Result: This sublist passes the filter.

### Outcome:
Only the last sublist passes the filter, which contains:
```java
List.of(Task("Task5", 5, true), Task("Task6", 5, true))
```

---

### What Makes `anyMatch()` Different?

If you use `anyMatch(task -> task.priority == 5)` instead of `allMatch()`, the behavior changes drastically:

```java
team.stream().allMatch(task -> task.isCompleted) && 
team.stream().anyMatch(task -> task.priority == 5)
```

Here’s how the conditions are evaluated:

1. **First `allMatch(task -> task.isCompleted)`:**
    - Ensures all tasks in a team are `completed`.

2. **Second `anyMatch(task -> task.priority == 5)`:**
    - Ensures that **at least one task** in the team has `priority == 5`.

### Example Revised Analysis:

1. **First Sublist (`Task1` and `Task2`):**
   ```java
   team.stream().allMatch(task -> task.isCompleted) // true
   team.stream().anyMatch(task -> task.priority == 5) // true
   ```
    - **Why?** Only Task1 has `priority == 5`, but `anyMatch` allows just one match.

2. **Second Sublist (`Task3` and `Task4`):**
   ```java
   team.stream().allMatch(task -> task.isCompleted) // false
   team.stream().anyMatch(task -> task.priority == 5) // N/A
   ```
    - **Why?** Since Task3 fails the `isCompleted` check, the first condition fails.

3. **Third Sublist (`Task5` and `Task6`):**
   ```java
   team.stream().allMatch(task -> task.isCompleted) // true
   team.stream().anyMatch(task -> task.priority == 5) // true
   ```
    - **Why?** Both tasks pass both conditions.

### Outcome with `anyMatch`:
With `anyMatch`, **both the first and the third sublists** are included because at least one task in the respective sublists satisfies the `priority == 5` condition.

---

### Conclusion

- When using `allMatch(task -> task.priority == 5)`, the condition is overly strict because **all tasks in a sublist must have a priority of 5**. This causes only the sublists where every task has high priority to pass (e.g., the last sublist in your example).
- Conversely, `anyMatch(task -> task.priority == 5)` requires **just one task** with a high priority (a far more lenient condition), making it more inclusive.

So, the **choice of `allMatch` versus `anyMatch` depends on how strict you want the filter to be**:
- Use `allMatch` if the entire sublist must uniformly satisfy the condition.
- Use `anyMatch` if you only care that at least one task in the sublist satisfies the condition.
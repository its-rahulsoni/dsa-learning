### LPS Array Preparation

---

### **Understanding the Knuth-Morris-Pratt (KMP) Algorithm**

Let’s break down **KMP** step by step and focus on the **LPS (Longest Prefix-Suffix) array**, its meaning, and the logic behind it. Once you grasp how it is constructed and why it is used, the KMP algorithm will feel intuitive.

---

### **What Problem Does the KMP Algorithm Solve?**

The **Knuth-Morris-Pratt (KMP)** algorithm is used for **pattern matching** (e.g., finding if a string `pattern` exists in another string `text`). The key optimization in KMP is that it avoids unnecessary re-checking of already matched characters when a mismatch occurs.

For example:
- Suppose `text = "abcxabcdabcdabcy"` and `pattern = "abcdabcy"`.
    - When there is a mismatch after several characters matched, the naive approach starts matching from the beginning, discarding earlier work. This is inefficient.
    - **KMP** uses the **LPS array** to skip redundant checks, making the algorithm faster.

---

### **What Is the LPS Array (Longest Prefix-Suffix)?**

#### **Definition**:
The **LPS array** tells us for each index in the `pattern`, the length of the **longest prefix** of the pattern that is also a **suffix** ending at that index.

#### **Why Is It Needed?**
The LPS array helps us decide where to "jump back" in the pattern when a mismatch occurs:
- Instead of starting from the beginning, we use the **LPS value** to resume from a smarter matching position (based on partial matches already found).

---

### **Key Insights Behind LPS Array**

In `pattern`, at any index `i`:
- LPS value = **length of the largest prefix that matches the suffix** ending at `i`.

#### **Example**

For `pattern = "abcdabcy"`, calculate the LPS array:
1. At index `0` (`pattern[0] = 'a'`):
    - No prefix = No suffix = `LPS[0] = 0`.

2. At index `1` (`pattern[1] = 'b'`):
    - Prefix = `"a"`, Suffix = `"b"`. No match = `LPS[1] = 0`.

3. At index `2` (`pattern[2] = 'c'`):
    - Prefix = `"ab"`, Suffix = `"bc"`. No match = `LPS[2] = 0`.

4. At index `3` (`pattern[3] = 'd'`):
    - Prefix = `"abc"`, Suffix = `"bcd"`. No match = `LPS[3] = 0`.

5. At index `4` (`pattern[4] = 'a'`):
    - Prefix = `"abcd"`, Suffix = `"bcda"`. No match = `LPS[4] = 0`.
    - But: `"a"` matches prefix `"a"`. So, `LPS[4] = 1`.

6. At index `5` (`pattern[5] = 'b'`):
    - Prefix = `"abcda"`, Suffix = `"bcdab"`.
    - `"ab"` matches prefix `"ab"`. So, `LPS[5] = 2`.

7. At index `6` (`pattern[6] = 'c'`):
    - Prefix = `"abcdab"`, Suffix = `"bcdabc"`.
    - `"abc"` matches prefix `"abc"`. So, `LPS[6] = 3`.

8. At index `7` (`pattern[7] = 'y'`):
    - Prefix = `"abcdabc"`, Suffix = `"bcdabcy"`.
    - No match = `LPS[7] = 0`.

**Final LPS Array**: `[0, 0, 0, 0, 1, 2, 3, 0]`.

---

### **How the LPS Array Helps Avoid Redundant Checks**

#### **Thought Process:**
When a mismatch occurs during pattern matching:
1. The **LPS value** tells us how many characters in the current partial match can be reused.
2. Instead of restarting from the beginning of the pattern, **jump to the LPS value** at the mismatch index.
3. This avoids redundant comparisons and reduces the overall complexity.

---

#### **Real-Life Analogy:**
Imagine you’re assembling a puzzle (pattern) against a reference image (text):
- If one piece (character) doesn’t fit, the naive approach starts from scratch.
- The smarter approach (KMP) remembers parts that did match (`prefix-suffix` information) and skips ahead to leverage partial progress.

---

### **Step-by-Step Code Walkthrough**

Let’s break down the **LPS array construction** first, then the pattern matching using KMP.

#### **Code for Computing the LPS Array**
The LPS array is calculated using a **while loop**:

```java
// Build the prefix-suffix (LPS) array for KMP
private static int[] buildLPSArray(String pattern) {
    int m = pattern.length();
    int[] lps = new int[m];
    int len = 0; // Length of the previous longest prefix suffix
    int i = 1;

    // lps[0] is always 0
    while (i < m) {
        if (pattern.charAt(i) == pattern.charAt(len)) {
            len++;
            lps[i] = len;
            i++;
        } else {
            if (len != 0) {
                len = lps[len - 1]; // Move to previous prefix match
            } else {
                lps[i] = 0;
                i++;
            }
        }
    }

    return lps;
}
```

---

#### **Step-by-Step Execution:**

Input: `pattern = "abcdabcy"`

1. Initialize:
    - `lps = [0, 0, 0, 0, 0, 0, 0, 0]`
    - `len = 0`, `i = 1`.

2. At index `i = 1`:
    - Compare `pattern[1]` with `pattern[0]`: `'b'` != `'a'`.
    - `lps[1] = 0`, increment `i`.

3. At index `i = 2`:
    - Compare `pattern[2]` with `pattern[0]`: `'c'` != `'a'`.
    - `lps[2] = 0`, increment `i`.

4. At index `i = 4`:
    - Compare `pattern[4]` with `pattern[0]`: `'a'` == `'a'`.
    - `lps[4] = 1`, increment `len` and `i`.

5. Continue:
    - At `i = 5`: `'b'` == `'b'`, set `lps[5] = 2`.
    - At `i = 6`: `'c'` == `'c'`, set `lps[6] = 3`.

6. At index `i = 7`: `'y'` != `'d'`, backtrack via `len`.

---

#### **KMP Search Code**

```java
private static boolean kmpSearch(String text, String pattern) {
    int n = text.length();
    int m = pattern.length();

    // Step 1: Build the prefix-suffix table
    int[] lps = buildLPSArray(pattern);

    // Step 2: Perform KMP search
    int i = 0, j = 0; // i = index in text, j = index in pattern
    while (i < n) {
        if (text.charAt(i) == pattern.charAt(j)) {
            i++;
            j++;
        }

        if (j == m) {
            // Pattern found
            return true;
        } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
            if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
    }

    return false; // No match found
}
```

---

### **How KMP Helps in Rotation Check**

The concatenated string `str1 + str1` contains **all possible rotations** of `str1`. Instead of brute force substring search with redundant checks, we use KMP to efficiently find `str2` in `str1 + str1`.

For `str1 = "abcd"` and `str2 = "cdab"`:
- `concat = "abcdabcd"`.
- Use KMP to find `"cdab"` in `"abcdabcd"`.
- Output: `true`.

---

### **Complexity Analysis**

1. **LPS Array Construction**:
    - Time Complexity: \(O(m)\), where \(m = pattern.length()\).

2. **Substring Search (KMP)**:
    - Time Complexity: \(O(n)\), where \(n = text.length()\).

Overall Complexity: \(O(n + m)\).

---

### **Final Thoughts**
The **LPS array in KMP** eliminates redundant comparisons during mismatches:
- It lets us "reuse" previously matched characters (prefix-suffix overlap).
- This optimization makes the search efficient, even for long strings or patterns.

🚀 ### **Why Is the Prefix-Suffix Relationship Calculated in the LPS Array?**

The **Knuth-Morris-Pratt (KMP)** algorithm uses the **Longest Prefix-Suffix (LPS)** array to optimize pattern matching by avoiding redundant comparisons. Let’s dive into **why prefix and suffix relationships are calculated** and **how they are helpful** in the context of the KMP algorithm.

---

### **Purpose of the LPS Array**

The **main purpose** of the LPS array is to store information about repeated patterns (prefixes and suffixes) within the **pattern itself**. This helps us efficiently **jump to the next possible matching position** in the pattern when a mismatch occurs during substring search.

---

### **What Does LPS Represent?**
For a pattern of length `m`, the `LPS` array is of size `m`, and for each position `i`:
- **LPS[i] = length of the longest proper prefix of the substring pattern[0..i] that is also a suffix.**

---

### **Why Calculate Prefix-Suffix Relationships?**

#### Key Insight:
When a mismatch occurs between a character in the **text** and the **pattern**, instead of restarting the search from the beginning of the pattern (`j = 0` in naive search), we can use the prefix-suffix relationship to "jump" to the next best position in the pattern.

---

### **How Does Prefix-Suffix Help?**

1. **Prefix and Suffix Define Overlaps:**
    - Prefix: The starting part of the pattern.
    - Suffix: The ending part of the pattern.
    - These overlaps tell us that, when a mismatch occurs, part of the pattern before the mismatch is still valid and does not need to be rechecked.

   **Example:**  
   For `pattern = "ababc"`, consider:
    - Substring `"aba"`.
    - Prefix = `"ab"`.
    - Suffix = `"ab"`.
    - If a mismatch occurs after `"ab"`, the LPS array helps us identify that the next potential match should start at the prefix `"ab"`, skipping redundant checks.

2. **Avoid Redundant Comparisons:**
    - In a naive substring search, if there’s a mismatch, the `pattern` pointer (`j`) resets to `0`.
    - Using the LPS array, instead of resetting completely, we skip back to the index defined by `LPS[j - 1]`.
    - This "jump back" leverages previous work to save time.

---

### **Why Prefix-Suffix Is Calculated Instead of Other Metrics**

An overlap of prefix and suffix in the pattern itself tells us **how the pattern can overlap with itself**. This is important because:
- Pattern overlaps are the key to reducing the number of comparisons when a mismatch occurs.
- No other metric (e.g., substring decomposition) provides this specific alignment information.

---

### **Example: Pattern Matching With LPS**

Let’s consider an example where `text = "abcababd"` and `pattern = "ababd"`.

---

#### **Pattern: `"ababd"`**

We calculate the LPS array as follows:
| i   | Pattern[0..i] | Proper Prefixes | Proper Suffixes | Longest Prefix-Suffix | LPS[i] |
|-----|---------------|-----------------|------------------|-----------------------|--------|
| 0   | `"a"`         | `--`            | `--`             | `0`                   | `0`    |
| 1   | `"ab"`        | `"a"`           | `"b"`            | `0`                   | `0`    |
| 2   | `"aba"`       | `"a", "ab"`     | `"a", "ba"`      | `"a"`                 | `1`    |
| 3   | `"abab"`      | `"a", "ab", "aba"` | `"b", "ab", "bab"` | `"ab"`                | `2`    |
| 4   | `"ababd"`     | `"a", "ab", "aba", "abab"` | `"d", "bd", "ab"` | `0`                   | `0`    |

Final LPS array: `[0, 0, 1, 2, 0]`.

---

### **How Prefix-Suffix Relationship Saves Time**

Now let’s walk through the key steps in matching `"ababd"` within `"abcababd"`:

1. Initial comparison:
   ```
   "abcababd" (text)
    |
   "ababd"    (pattern)
    ^
   ```
    - Compare `'a'` with `'a'`, `'b'` with `'b'`, etc., until a mismatch occurs at index `2` in the text (`'c'` ≠ `'a'`).

2. Use the LPS array:
    - At mismatch at `j = 2`, use `LPS[1]` (value is `0`).
    - Set `j = LPS[j - 1]`.
    - No backtracking in text (`i` remains at `3`).

3. Continue matching:
   ```
   "abcababd" (text)
       ^
   "ababd"    (pattern)
     ^
   ```
    - Match `'a'`, `'b'`, `'a'`, `'b'` until mismatch occurs at `'d'` in the pattern.

4. Use LPS array again:
    - Mismatch at `j = 4` → Use `LPS[3] = 2`.
    - Set `j = 2`.

5. Final alignment:
   ```
   "abcababd" (text)
        ^
   "ababd"    (pattern)
      ^
   ```
    - Match the rest of the pattern.

---

### **Why Prefix-Suffix Relationships Save Time**

Let’s summarize the efficiency:
1. Instead of restarting from the beginning of the pattern on each mismatch, KMP uses the overlap information in the pattern itself.
2. This avoids unnecessary comparisons in the text and pattern, as the LPS array provides a "jump back" position.

In essence:
- **Prefix-suffix matches act like shortcuts** to reduce redundant work.

---

### **Practical Benefits**

1. **Efficient Mismatch Handling:**
    - Patterns like `"ababababababd"` (repeated substrings) would cause naive comparison to repeatedly restart in the pattern, but KMP uses the LPS array to skip unnecessary comparisons.

2. **Optimized Matching for Long Strings:**
    - For very large text and patterns, re-checking characters on mismatch would lead to \(O(n \cdot m)\) complexity with naive search. Using prefix-suffix relationships reduces this to \(O(n + m)\).

---

### **Key Takeaway**

The **LPS array** calculates prefix-suffix overlaps in the pattern to guide the search process when mismatches occur. It allows the search algorithm to leverage already-known matches within the pattern, eliminating unnecessary comparisons and making the KMP algorithm highly efficient. This makes it much faster than naive substring search algorithms.

Let me know if you'd like clarification on specific parts of the explanation or another example! 🚀

---

---


### Explanation of the KMP Search Algorithm (After LPS Array Preparation)

---

### **Explanation of the KMP Search Algorithm (After LPS Array Preparation)**

Now that you've understood how the **LPS (Longest Prefix-Suffix) array** is created, let me explain **step-by-step** what happens during the **"pattern search" phase** of the **Knuth-Morris-Pratt (KMP) algorithm**. The focus will be on the part of the `kmpSearch` method after building the LPS array.

---

### **How KMP Searches for a Pattern in Text**

Once the LPS array is prepared, the algorithm uses it to efficiently search for the `pattern` in the `text`. The key idea is:
- Use the **LPS array** to avoid unnecessary comparisons by skipping already matched characters whenever there is a mismatch.
- Use two pointers:
    - One pointer `i` for traversing the **text**.
    - One pointer `j` for traversing the **pattern**.

### **Key Insights**

1. The `LPS` array prevents backtracking in the text.
2. If we get a mismatch at a certain point, instead of resetting `j` to `0` (as naive search would do), we "jump back" to the position indicated by `lps[j - 1]` in the `pattern`.
3. If characters match, we increment both pointers (`i` and `j`) to continue matching.
4. The search continues until we either:
    - Successfully match the entire pattern (`j == m`, where `m` is the length of the pattern), or
    - Exhaust the text without finding the pattern (`i == n`, where `n` is the length of the text).

---

### **Code Walkthrough of the Search Phase**

Let’s analyze the **text traversal part** step by step.

#### **Input**
- `text = "abcxabcdabcdabcy"`
- `pattern = "abcdabcy"`
- `lps = [0, 0, 0, 0, 1, 2, 3, 0]` (already prepared).

#### **Algorithm Structure**

```java
int i = 0, j = 0; // i = index in text, j = index in pattern
while (i < n) { // Loop until we've traversed the entire text

    if (text.charAt(i) == pattern.charAt(j)) {
        // Case 1: Characters match
        i++;
        j++;
    }

    if (j == m) {
        // Case 2: Full pattern matched
        return true; // Pattern found in text
    } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
        // Case 3: Mismatch after some matches
        if (j != 0) {
            // Jump back in the pattern using LPS
            j = lps[j - 1];
        } else {
            // If no match, simply move forward in the text
            i++;
        }
    }
}

// Case 4: No match found after full traversal
return false;
```

---

### **Detailed Explanation of Each Case**

#### **Case 1: Characters Match**
```java
if (text.charAt(i) == pattern.charAt(j)) {
    i++;
    j++;
}
```

- If the characters pointed by `i` (text) and `j` (pattern) match:
    1. Increment both pointers:
        - `i` → Move to the next character in the text.
        - `j` → Move to the next character in the pattern.

- **Goal**:
    - Continue matching the next characters.
    - If `j` reaches `m` (length of the pattern), it means the entire pattern has been successfully matched.

---

#### **Case 2: Full Pattern Matched**
```java
if (j == m) {
    return true; // Pattern matched
}
```

- If `j` equals the length of the pattern (`m`), it means the entire pattern exists in the text.
- **Return `true`** immediately.

- **Example Walkthrough**:
    - At some point, `pattern = "abcdabcy"` is fully matched in `text = "abcxabcdabcdabcy"`.

---

#### **Case 3: Mismatch After Some Matches**
```java
} else if (i < n && text.charAt(i) != pattern.charAt(j)) {
    if (j != 0) {
        j = lps[j - 1]; // Use the LPS array to "jump back" in the pattern
    } else {
        i++; // Otherwise, move to the next character in text
    }
}
```

Here’s what happens if `text[i] != pattern[j]` (mismatch):

1. **If `j != 0` (partial match exists):**
    - Use the LPS array to determine the position in the pattern:
        - `j = lps[j - 1]`.
        - This skips unnecessary comparisons in the pattern while preserving the progress of already matched characters.

2. **If `j == 0` (no partial match):**
    - Simply increment `i` (move forward in the text) and continue comparing from the beginning of the pattern.

---

#### **Case 4: Traverse Entire Text Without Match**
```java
return false; // No match found
```

- If the entire text is traversed and no full match for the pattern is found, the function returns `false`.

---

### **Example Walkthrough**

#### Input:
- `text = "abcxabcdabcdabcy"`
- `pattern = "abcdabcy"`
- `lps = [0, 0, 0, 0, 1, 2, 3, 0]`.

#### Execution:

1. **Start with `i = 0, j = 0`:**
    - `text[0] = 'a' == 'a' = pattern[0]` → Match.
    - `i = 1, j = 1`.

2. **Next Characters:**
    - Match: `'b' == 'b'`, `'c' == 'c'`, `'x' != 'd'`.
    - Mismatch at `i = 3, j = 3`.

3. **Mismatch: Use LPS:**
    - `lps[j - 1] = lps[2] = 0`.
    - Update `j = 0`.
    - Move `i = 4`.

4. **Continue Matching:**
    - From `text[4]` (`'a'`), match `'abcdabcdabcy'` sequentially.

5. **Match Completed:**
    - Full pattern matched (`j = 8`) → Return `true`.

---

### **How the Algorithm Uses the LPS Array**

1. **Efficient Backtracking**:
    - The **LPS array** eliminates redundant comparisons by reusing partial matches in the pattern.

2. **Smarter Mismatch Handling**:
    - Instead of restarting from the beginning of the pattern, the LPS array tells us where to **jump** in the pattern.

---

### **Complexity Analysis**

#### **Time Complexity**:
1. **LPS Array Construction**: \(O(m)\), where \(m\) is the length of the pattern.
2. **Pattern Search**:
    - At each mismatch, we backtrack in the pattern using the LPS array.
    - Total complexity is \(O(n)\), where \(n\) is the length of the text.

Overall Time Complexity: **\(O(m + n)\)**.

#### **Space Complexity**:
- The LPS array requires \(O(m)\) space.

---

### **Advantages of KMP**

1. Eliminates unnecessary comparisons, making it **efficient for large strings**.
2. Handles repeated patterns in both text and pattern effectively.
3. Time complexity is linear in terms of the length of the strings: \(O(m + n)\).

---

### **Conclusion**

The code you provided elegantly uses the **KMP search algorithm** to find a pattern in a text. The LPS array enables efficient backtracking on mismatches, and the overall method is much faster than naive substring search.

Let me know if you'd like further clarification or additional examples! 🚀

---

---

else if (i < n && text.charAt(i) != pattern.charAt(j)) {
// Case 3: Mismatch after some matches
if (j != 0) {
// Jump back in the pattern using LPS
j = lps[j - 1];
} else {
// If no match, simply move forward in the text
i++;
}
}
this part of code, could u pls use an example to explain, a code walk through>
I am specifically intertested in learning how to use the lps array

---

Let's focus on the **part of the KMP search algorithm** where mismatches are handled using the **LPS array**:

```java
else if (i < n && text.charAt(i) != pattern.charAt(j)) {
    // Case 3: Mismatch after some matches
    if (j != 0) {
        // Jump back in the pattern using LPS
        j = lps[j - 1];
    } else {
        // If no match, simply move forward in the text
        i++;
    }
}
```

This part of the algorithm ensures that instead of restarting the pattern matching from scratch (i.e., resetting `j = 0`), we use the **LPS array** to jump directly to the next best position in the pattern where a match could continue. This prevents redundant checks and significantly optimizes the search process.

---

### **Thought Process**

When there’s a mismatch (`text.charAt(i) != pattern.charAt(j)`):
1. **If `j != 0`**:
    - Use the **LPS array** to determine how many characters we can skip back in the pattern.
    - By doing this, we continue matching from a previously known "partial match" instead of restarting.

2. **If `j == 0`**:
    - There’s no partial match to fall back on in the pattern, so we simply **move forward in the text** (increment `i`).

Now, let's solidify this with an example.

---

### **Example Walkthrough**

#### Inputs:
- **Text**: `"abcxabcdabcdabcy"`
- **Pattern**: `"abcdabcy"`
- **LPS Array**:
  ```
  lps = [0, 0, 0, 0, 1, 2, 3, 0]
  ```
    - This means:
        - For `pattern[0] = 'a'`, longest prefix-suffix length = 0.
        - For `pattern[1] = 'b'`, prefix-suffix length = 0.
        - For `pattern[4] = 'a'`, prefix-suffix length = 1 (prefix `"a"` = suffix `"a"`).
        - For `pattern[6] = 'c'`, prefix-suffix length = 3 (prefix `"abc"` = suffix `"abc"`).

---

#### Execution Steps

![KMP Explanation](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/KMP_searching.svg/700px-KMP_searching.svg.png)

---

#### **Initial State:**
- **Pointers**:
    - `i = 0` (text pointer).
    - `j = 0` (pattern pointer).
- Text: `"abcxabcdabcdabcy"`.
- Pattern: `"abcdabcy"`.
- LPS Array: `[0, 0, 0, 0, 1, 2, 3, 0]`.

---

### **Step-by-Step Walkthrough**

#### **Matching Phase (Partial Matches):**

1. **Compare `text[0] ('a')` with `pattern[0] ('a')`:**
    - Match → Increment both pointers:
        - `i = 1`, `j = 1`.

2. **Compare `text[1] ('b')` with `pattern[1] ('b')`:**
    - Match → Increment both:
        - `i = 2`, `j = 2`.

3. **Compare `text[2] ('c')` with `pattern[2] ('c')`:**
    - Match → Increment both:
        - `i = 3`, `j = 3`.

4. **Compare `text[3] ('x')` with `pattern[3] ('d')`:**
    - **Mismatch occurs!**

---

#### **Mismatch Handling (Use the LPS Array):**

At this point:
- `i = 3`, `j = 3` (`pattern[3]` = `'d'` has no match with `text[3]` = `'x'`).

**Steps:**
1. Check the LPS value:
    - `lps[j - 1] = lps[2] = 0`.
2. Update `j` to `lps[j - 1]` (backtrack in the pattern):
    - `j = 0` (reset pattern pointer).
3. Move `i` forward in the text:
    - `i = 4`.

---

#### **Continue Matching:**

5. **Compare `text[4] ('a')` with `pattern[0] ('a')`:**
    - Match → Increment both:
        - `i = 5`, `j = 1`.

6. **Compare `text[5] ('b')` with `pattern[1] ('b')`:**
    - Match → Increment both:
        - `i = 6`, `j = 2`.

7. **Compare `text[6] ('c')` with `pattern[2] ('c')`:**
    - Match → Increment both:
        - `i = 7`, `j = 3`.

8. **Compare `text[7] ('d')` with `pattern[3] ('d')`:**
    - Match → Increment both:
        - `i = 8`, `j = 4`.

9. **Compare `text[8] ('a')` with `pattern[4] ('a')`:**
    - Match → Increment both:
        - `i = 9`, `j = 5`.

10. **Compare `text[9] ('b')` with `pattern[5] ('b')`:**
    - Match → Increment both:
        - `i = 10`, `j = 6`.

11. **Compare `text[10] ('c')` with `pattern[6] ('c')`:**
    - Match → Increment both:
        - `i = 11, j = 7`.

12. **Compare `text[11] ('d')` with `pattern[7] ('y')`:**
    - **Mismatch again!**

---

#### **Mismatch Handling:**

At this point:
- `i = 11`, `j = 7` (`pattern[7]` = `'y'` has no match with `text[11]` = `'d'`).

**Steps:**
1. Use the LPS array:
    - `lps[j - 1] = lps[6] = 3`.
2. Update pointer `j`:
    - `j = lps[6] = 3` (reset pattern pointer to continue from the third character).
3. Keep `i` at `11` and continue comparing.

---

#### **Final Match:**

13. Continue comparing from `j = 3`:
    - Compare `text[11] ('d')` with `pattern[3] ('d')` → Match.
    - Keep comparing until the pattern is fully matched.

14. When `j == m` (`m = 8` is the pattern length), the pattern is fully found.

---

### **Key Interaction Between LPS and Jumps**

Here’s how the **LPS array** eliminates redundant checks:
1. Instead of restarting the pattern every time:
    - The LPS array lets us **reuse information about previous matches**.
2. By jumping to `lps[j-1]`, we skip unnecessary comparisons for characters in the pattern that are already known not to match.

---

### **Final Outcome**

For:
- `text = "abcxabcdabcdabcy"`
- `pattern = "abcdabcy"`

The KMP search returns `true`, as the pattern is found in the text.

---

### **Summary**

1. **Why Use LPS?**
    - The LPS array ensures we don’t repeat comparisons unnecessarily. It allows the pattern pointer `j` to "fall back" efficiently to the next possible match point.

2. **Steps During Mismatch**:
    - Use `lps[j-1]` to skip back in the pattern.
    - If `j == 0`, simply move forward in the text (`i++`).

3. **Time Complexity**:
    - **Pattern Matching**: \(O(n)\), as each character in the text is visited only once.
    - **LPS Construction**: \(O(m)\), where \(m\) is the length of the pattern.

This makes KMP much faster than naive substring search. Let me know if you need further clarification! 🚀

---

---



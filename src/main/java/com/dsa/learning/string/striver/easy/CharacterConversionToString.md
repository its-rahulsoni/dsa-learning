QUES: Converting a Char Array into a String ....

ANS: In Java, when you call `toString()` on an array (such as your `char[]` array), it doesn’t actually convert the contents of the array into a readable string representation. Instead, the `toString()` method inherited from `Object` is used, which returns the **class name and hash code** of the array in the following form:
```
[<type>@<hashCode>
```

In your case:
```java
char[] charArray = {'I', 'N', 'O', 'S', '-', 'L', 'U', 'H', 'A', 'R'};
System.out.println(charArray.toString());
```

This produces an output like `[C@279f2327`, where:
- `[C` indicates it’s a **character array** (in Java, `[C` is the notation for `char[]`).
- `279f2327` is the **hashcode** of the array.

---

### **How to Resolve This:**
To print the **contents of the array** (as a readable string), you need to use one of the following approaches:

#### **1. Use `String.valueOf(charArray)`**
If you want to **directly create a `String` from a `char[]`**, you can use the `String.valueOf(charArray)` method:
```java
char[] charArray = {'I', 'N', 'O', 'S', '-', 'L', 'U', 'H', 'A', 'R'};
System.out.println(String.valueOf(charArray));
```

**Output:**
```
INOS-LUHAR
```

---

#### **2. Use `Arrays.toString()`**
If you want to print the **array with individual characters separated by commas** (in a human-readable array format), use `java.util.Arrays.toString()`:
```java
import java.util.Arrays;

char[] charArray = {'I', 'N', 'O', 'S', '-', 'L', 'U', 'H', 'A', 'R'};
System.out.println(Arrays.toString(charArray));
```

**Output:**
```
[I, N, O, S, -, L, U, H, A, R]
```

---

#### **3. Convert to a String Manually Using a Loop**
For complete control, you can manually iterate over the array and build a string:
```java
char[] charArray = {'I', 'N', 'O', 'S', '-', 'L', 'U', 'H', 'A', 'R'};
StringBuilder sb = new StringBuilder();

for (char c : charArray) {
    sb.append(c);
}
System.out.println(sb.toString());
```

**Output:**
```
INOS-LUHAR
```

---

### **Quick Key Differences**
| Approach                      | Output Format             |
|-------------------------------|---------------------------|
| `String.valueOf(charArray)`   | `INOS-LUHAR`             |
| `Arrays.toString(charArray)`  | `[I, N, O, S, -, L, U, H, A, R]` |
| Manual Loop                   | `INOS-LUHAR`             |

---

### Why `toString()` Doesn’t Work for Arrays?
The `toString()` method for arrays is **not overridden** in the `Array` class to produce a meaningful string representation of the array contents. Instead, it inherits the `Object` class's implementation, which prints:
1. The type of the array (e.g., `[C` for `char[]`).
2. A hash code.

To see a proper string representation, you must explicitly format it using approaches like `String.valueOf`, `Arrays.toString`, or by looping through the array manually.

Let me know if you need further clarification! 🚀
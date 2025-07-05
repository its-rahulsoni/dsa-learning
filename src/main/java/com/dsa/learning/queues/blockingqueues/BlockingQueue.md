Several queue implementations in Java are designed to **block** in producer-consumer scenarios when certain conditions are met (e.g., the queue is full or empty). These queues are part of the `java.util.concurrent` package and implement the `BlockingQueue` interface.

### **Blocking Queues in Java**

Here are the key `BlockingQueue` implementations, along with their characteristics and use cases:

---

### **1. `ArrayBlockingQueue`**
- **Type**: Bounded, fixed-size blocking queue.
- Blocks a producer **if the queue is full** and blocks a consumer **if the queue is empty**.

#### Characteristics:
- Uses a **predefined capacity** specified when initializing the queue.
- FIFO (First-In-First-Out) ordering.
- Useful for **bounded** producer-consumer scenarios to prevent excessive memory usage.

#### Example:
```java
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2); // Capacity of 2

        // Producer thread
        new Thread(() -> {
            try {
                queue.put(1); // Blocks if queue is full
                System.out.println("Added: 1");
                queue.put(2); // Blocks if queue is full
                System.out.println("Added: 2");
                queue.put(3); // Will block here until consumer removes an element
                System.out.println("Added: 3");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate processing time
                System.out.println("Removed: " + queue.take()); // Blocks if queue is empty
                System.out.println("Removed: " + queue.take()); // Blocks if queue is empty
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
```

---

### **2. `LinkedBlockingQueue`**
- **Type**: Unbounded (or optionally bounded) blocking queue backed by a linked list.
- Blocks the producer **if the queue reaches its capacity** (for bounded queues) and blocks the consumer if the queue is empty.

#### Characteristics:
- FIFO (First-In-First-Out) ordering.
- By default, it is **unbounded** (capacity is `Integer.MAX_VALUE`) unless explicitly bounded.
- Better suited for **high-throughput systems** than `ArrayBlockingQueue` due to its linked list structure and separate locks for producers and consumers.

#### Example:
```java
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2); // Capacity of 2

        // Producer thread
        new Thread(() -> {
            try {
                queue.put(1); // Blocks if queue is full
                System.out.println("Added: 1");
                queue.put(2);
                System.out.println("Added: 2");
                queue.put(3); // Will block if the queue is full
                System.out.println("Added: 3");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Simulate processing time
                System.out.println("Removed: " + queue.take()); // Blocks if queue is empty
                System.out.println("Removed: " + queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
```

---

### **3. `PriorityBlockingQueue`**
- **Type**: Unbounded blocking queue, ordered by priority (like `PriorityQueue`).
- **Does NOT block producers** because it's unbounded, but can block consumers **if the queue is empty.**

#### Characteristics:
- Elements are stored based on **priority** (natural ordering or a custom comparator), not insertion order.
- It is **not strictly FIFO**, but the consumer **always retrieves the highest-priority element**.
- It does not block producers as it is **unbounded**, which means it grows dynamically instead of rejecting new elements when the queue is full.

#### Example:
```java
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        // Producer thread
        new Thread(() -> {
            try {
                queue.put(10); // Inserts without blocking (unbounded)
                System.out.println("Added: 10");
                queue.put(5);
                System.out.println("Added: 5");
                queue.put(20);
                System.out.println("Added: 20");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate processing time
                System.out.println("Removed: " + queue.take()); // Retrieves smallest element (priority)
                System.out.println("Removed: " + queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
```

**Key Differences:**
- Unbounded: It won't block producers since there's never a full condition.
- Blocks consumers if the queue is empty (via `take()`).
- Always retrieves elements by priority.

---

### **4. `DelayQueue`**
- **Type**: Unbounded queue that holds elements until a specified delay has expired.
- Blocks consumers **until the delay of the head element elapses**.

#### Characteristics:
- Requires elements to implement the interface `Delayed` to define the delay.
- Useful in scheduling tasks or rate-limiting scenarios where elements must become available after a delay.

#### Example:
```java
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        // Add elements with specified delays
        queue.put(new DelayedTask("Task1", 5)); // Delay: 5 seconds
        queue.put(new DelayedTask("Task2", 2)); // Delay: 2 seconds
        queue.put(new DelayedTask("Task3", 10)); // Delay: 10 seconds

        // Consumer retrieves tasks in delay order
        while (!queue.isEmpty()) {
            DelayedTask task = queue.take(); // Blocks until the delay elapses
            System.out.println("Processing: " + task.getName());
        }
    }
}

// A custom task that implements Delayed
class DelayedTask implements Delayed {
    private String name;
    private long delayTime; // Delay time in nanoseconds
    private long expiryTime;

    public DelayedTask(String name, long delaySeconds) {
        this.name = name;
        this.delayTime = TimeUnit.SECONDS.toNanos(delaySeconds);
        this.expiryTime = System.nanoTime() + delayTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expiryTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.NANOSECONDS), o.getDelay(TimeUnit.NANOSECONDS));
    }
}
```

**How It Works:**
- Tasks are consumed after their delay has expired, ordered by the shortest delay first.
- If no tasks are ready, `take()` will block until the next delay expires.

---

### **Comparison of Blocking Queues**

| **Queue**              | **Bounded?** | **Blocking Producer When Full?** | **Blocking Consumer When Empty?** | **Order**            |
|-------------------------|--------------|-----------------------------------|------------------------------------|----------------------|
| `ArrayBlockingQueue`    | Yes          | Yes                               | Yes                                | FIFO                 |
| `LinkedBlockingQueue`   | No (by default, can be bounded)| Yes (if bounded)              | Yes                                | FIFO                 |
| `PriorityBlockingQueue` | No           | No                                | Yes                                | Based on priority    |
| `DelayQueue`            | No           | No                                | Yes                                | Time-based (delayed) |

---

### Key Takeaway:
- If you need a **bounded queue** with blocking behavior, `ArrayBlockingQueue` and `LinkedBlockingQueue` are good choices.
- If you need **priority-based ordering with blocking consumers**, use `PriorityBlockingQueue`.
- If you need scheduling by delay, use `DelayQueue`.

Let me know if you need further clarification or additional examples!
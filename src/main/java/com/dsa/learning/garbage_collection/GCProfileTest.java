package com.dsa.learning.garbage_collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Add the below command to VM options:
 * -Xms128m -Xlog:gc*:file=gc.log:time,level
 */
public class GCProfileTest {

    public static void main(String[] args) {

        List<byte[]> memoryHog = new ArrayList<>();
        String name;
        int i=0;

        try {
            while (true) {
                byte[] chunk = new byte[1 * 1024 * 1024]; // 1MB
                memoryHog.add(chunk);
                i++;
                if (i % 10 == 0) {
                    System.out.println(i + "MB allocated");
                    Thread.sleep(100); // Give GC a chance
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OOM after allocating: " + i + "MB");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

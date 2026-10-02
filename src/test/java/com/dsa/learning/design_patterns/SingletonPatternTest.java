package com.dsa.learning.design_patterns;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingletonPatternTest {

    @Test
    void getInstanceReturnsSameObject() {
        assertSame(SingletonPattern.getInstance(), SingletonPattern.getInstance());
    }

    @Test
    void stateIsSharedAcrossReferences() {
        SingletonPattern first = SingletonPattern.getInstance();
        SingletonPattern second = SingletonPattern.getInstance();

        int before = first.incrementAndGet();
        assertEquals(before + 1, second.incrementAndGet());
    }

    @Test
    void concurrentCallersGetSameInstance() throws InterruptedException {
        Set<SingletonPattern> instances = ConcurrentHashMap.newKeySet();
        ExecutorService pool = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 100; i++) {
            pool.submit(() -> instances.add(SingletonPattern.getInstance()));
        }
        pool.shutdown();
        assertTrue(pool.awaitTermination(5, TimeUnit.SECONDS));

        assertEquals(1, instances.size());
    }
}

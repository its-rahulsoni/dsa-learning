package com.dsa.learning.java8.streams.intermediate.skip;

import java.util.List;

public class SkipUsages {

    public static void main(String[] args) {

        // 01. Paginate through a data set ....
        paginateThroughADataSet();
    }

    /**
     * 01. Paginate through a data set.
     * When working with large datasets (e.g., in a web app), use limit() to implement pagination.
     *
     * Explanation:
     * skip() skips the elements corresponding to previous pages.
     * limit() ensures only pageSize elements are shown.
     */
    public static void paginateThroughADataSet() {
        System.out.println("**** paginateThroughADataSet() ****");

        List<String> data = List.of(
                "Alice", "Bob", "Cathy", "Daniel", "Elena",
                "Frank", "George", "Helen", "Iris", "Jack");

        // Pagination parameters
        int pageSize = 3;
        int page = 2; // Get the second page (zero-based)

        data.stream()
                .skip(5)
                .limit(4)
                .forEach(System.out::println);

    }
}

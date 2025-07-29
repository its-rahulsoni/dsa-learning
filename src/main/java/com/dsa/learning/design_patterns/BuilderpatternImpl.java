package com.dsa.learning.design_patterns;

public class BuilderpatternImpl {

    public static void main(String[] args) {

        /**
         * Our manual implementation uses new because you explicitly defined a public constructor for your inner builder class:
         *
         * BuilderPattern bp = new BuilderPattern.InnerBuilder() // ✅ this works because...
         * This works only because InnerBuilder is:
         *
         * a public static class,
         * and you're using its default public constructor.
         *
         * ----------------------------------------------------
         *
         * 💡 Why new doesn't work with Lombok ????
         * When you use Lombok’s @Builder:
         * @Builder
         * public class BuilderPattern {
         *    ...
         * }
         * Lombok does not expose the inner builder class directly for instantiation. Instead, it adds this method in SearchQuery:
         *
         * public static BuilderPatternBuilder builder() {
         *     return new BuilderPatternBuilder();
         * }
         * So you must call:
         * BuilderPattern.builder()...
         *
         * You cannot do:
         * new BuilderPatternBuilder() or new BuilderPattern.InnerBuilder() // ❌ not visible
         */
        BuilderPattern bp = new BuilderPattern.InnerBuilder()
                .setId(1)
                .setName("name")
                .setAge(26)
                .setAddress("Address")
                .build();

        System.out.println("Builder details: " + bp.toString());
    }

}

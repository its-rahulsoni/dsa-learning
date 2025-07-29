package com.dsa.learning.design_patterns;

public class BuilderPattern {

    int id;
    int age;
    String name;
    String address;

    // Private constructor (only accessible via Builder) ....
    private BuilderPattern(int id, int age, String name, String address){
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
    }

    /**
     *  Getters (omitted setters to keep immutability) ....
     */
    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString(){
        return "id: " + id + ", age: " + age + ", name: " + name + ", address: " + address;
    }

    // Static inner Builder class ....
    public static class InnerBuilder{
        private int id;
        private int age;
        private String name;
        private String address;

        /**
         *  Setter Methods to set fields (return Builder for chaining) ....
         */
        public InnerBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public InnerBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public InnerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public InnerBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public BuilderPattern build(){
            return new BuilderPattern(this.id,
                    this.age,
                    this.name,
                    this.address);
        }
    }
}

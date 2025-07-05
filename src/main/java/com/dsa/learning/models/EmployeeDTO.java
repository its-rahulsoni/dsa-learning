package com.dsa.learning.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class EmployeeDTO {
    private int id;
    private int age;
    private String name;
    private double salary;

    public EmployeeDTO(int id, int age, String name, double salary) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Override toString for better debugging output
    @Override
    public String toString() {
        return "EmployeeDTO{id=" + id + ", age='" + age + ", name='" + name + "', salary=" + salary + "}";
    }
}
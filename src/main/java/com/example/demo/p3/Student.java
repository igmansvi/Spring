package com.example.demo.p3;

public class Student {
    private final Integer id;
    private final String name;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student[ Id: " + this.id + ", " + "Name: "+ this.name + " ]";
    }
}

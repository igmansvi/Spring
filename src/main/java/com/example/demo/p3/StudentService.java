package com.example.demo.p3;

import org.springframework.stereotype.Component;

@Component
public class StudentService {
    private final Student student;

    public StudentService(Student student) {
        this.student = student;
    }

    public void display() {
        System.out.println(student);
    }
}

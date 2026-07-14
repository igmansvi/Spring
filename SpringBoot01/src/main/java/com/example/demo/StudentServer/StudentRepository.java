package com.example.demo.StudentServer;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    public Student save(Student student) {
        System.out.println("Student created successfully!");
        return student;
    }
}

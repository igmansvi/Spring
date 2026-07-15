package com.example.demo.StudentServer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Student {
    @Id
    int id;
    String name;
    int age;
    String department;

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }
}

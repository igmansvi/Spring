package com.example.demo.StudentServer;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }

    public Student validate(Student student) {
        int id = student.getId();
        String name = student.getName();
        int age = student.getAge();
        String department = student.getDepartment();

        if(id < 0 || name == null || age < 0 || department == null) {
            System.out.println("Error: Failed Creation!");
            return null;
        }

        return studentRepository.save(student);
    }
}

package com.example.demo.StudentServer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) { this.studentService = studentService; }

    // Store the student
    /*
        Request: curl.exe -i -X POST http://localhost:8080/student/create -H "Content-type: application/json" -d '{\"id\":101, \"name\":\"mansvi\", \"age\":21, \"department\":\"cse\"}'
     */
    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student result = studentService.validate(student);
        if(result == null) {
            ResponseEntity.status(400).body("Error: Student Creation Failed, Invalid information!");
        }
        return ResponseEntity.status(201).body(result);
    }

    // Read the Student by ID

    // Update the student information

    // Delete the student information
}


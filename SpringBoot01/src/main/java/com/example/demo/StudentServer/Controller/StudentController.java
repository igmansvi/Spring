package com.example.demo.StudentServer.Controller;

import com.example.demo.StudentServer.Entity.Student;
import com.example.demo.StudentServer.Dto.StudentResponse;
import com.example.demo.StudentServer.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<StudentResponse<Student>> create(@RequestBody Student student) {
        StudentResponse<Student> result = studentService.validate(student);
        if(!result.success()) {
            return ResponseEntity.status(400).body(result);
        }
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    public ResponseEntity<StudentResponse<List<Student>>> all() {
        StudentResponse<List<Student>> result = studentService.getAll();
        if(!result.success()) {
            return ResponseEntity.status(404).body(result);
        }
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse<Student>> getById(@PathVariable int id) {
        StudentResponse<Student> result = studentService.getById(id);
        if(!result.success()) {
            return ResponseEntity.status(404).body(result);
        }
        return ResponseEntity.status(200).body(result);
    }

    // Read the Student by ID

    // Update the student information

    // Delete the student information
}

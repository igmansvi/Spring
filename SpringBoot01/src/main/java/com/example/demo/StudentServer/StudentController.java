package com.example.demo.StudentServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    // Store the student
    @GetMapping("/create")
    public String storeStudent() { return "id: 1, name: abc"; }

    // Read the Student by ID

    // Update the student information

    // Delete the student information
}

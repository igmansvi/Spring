package com.example.demo.StudentServer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    // Store the student
    /*
        Request: curl.exe -X POST http://localhost:8080/student/create -H "Content-type: application/json" -d '{\"id\":101, \"name\":\"mansvi\", \"age\":21, \"department\":\"cse\"}'
     */
    @PostMapping("/create")
    public String storeStudent(@RequestBody Student student) {
        return student.toString();
    }

    // Read the Student by ID

    // Update the student information

    // Delete the student information
}


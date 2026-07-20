package com.example.demo.StudentServer.Controller;

import com.example.demo.StudentServer.Dto.StudentRequestDTO;
import com.example.demo.StudentServer.Entity.Student;
import com.example.demo.StudentServer.Dto.StudentResponseDTO;
import com.example.demo.StudentServer.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = {"http://localhost:4200"})
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) { this.studentService = studentService; }

    // Store the student
    /*
        Request: curl.exe -i -X POST http://localhost:8080/student/create -H "Content-type: application/json" -d '{\"name\":\"abc\", \"age\":21, \"department\":\"cse\"}'
     */
    @PostMapping("/create")
    public ResponseEntity<StudentResponseDTO<Student>> create(@RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO<Student> result = studentService.validate(studentRequestDTO);
        if(!result.success()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // Real all students
    @GetMapping
    public ResponseEntity<StudentResponseDTO<List<Student>>> all() {
        StudentResponseDTO<List<Student>> result = studentService.getAll();
        if(!result.success()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // Read the Student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO<Student>> getById(@PathVariable int id) {
        StudentResponseDTO<Student> result = studentService.getById(id);
        if(!result.success()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // Update the student information
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO<Student>> update(@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable int id) {
        StudentResponseDTO<Student> result = studentService.update(studentRequestDTO, id);
        if(!result.success()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // Delete the student information
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDTO<Student>> delete(@PathVariable int id) {
        StudentResponseDTO<Student> result = studentService.delete(id);
        if(!result.success()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

package com.example.demo.StudentServer.Service;

import com.example.demo.StudentServer.Entity.Student;
import com.example.demo.StudentServer.Repository.StudentRepository;
import com.example.demo.StudentServer.Dto.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }

    private boolean isValid(Student student) {
        int id = student.getId();
        String name = student.getName();
        int age = student.getAge();
        String department = student.getDepartment();

        return id >= 0 && name != null && age >= 0 && department != null;
    }

    private boolean doesExists(int id) {
        return studentRepository.existsById(id);
    }

    public StudentResponse<Student> validate(Student student) {
        if(!isValid(student)) {
            return new StudentResponse<Student>(false, "Error: Student Creation Failed, Invalid information!", null);
        }

        if(doesExists(student.getId())) {
            return new StudentResponse<Student>(false, "Already Exists!", null);
        }

        Student created = studentRepository.save(student);
        return new StudentResponse<Student>(true, "Student created successfully!", created);
    }

    public StudentResponse<List<Student>> getAll() {
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()) {
            return new StudentResponse<List<Student>>(false, "No students exists!", null);
        }
        return new StudentResponse<List<Student>>(true, "All students data!", students);
    }

    public StudentResponse<Student> getById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) {
            return new StudentResponse<Student>(false, "Student: " + id + " does not exists!", null);
        }
        return new StudentResponse<Student>(true, "Student: " + id, student.get());
    }

    public StudentResponse<Student> update(Student student, int id) {
        if(!isValid(student)) {
            return new StudentResponse<Student>(false, "Error: Student Creation Failed, Invalid information!", null);
        }

        if(id != student.getId()) {
            return new StudentResponse<Student>(false, "Invalid request body!", null);
        }

        if(!doesExists(student.getId())) {
            return new StudentResponse<Student>(false, "Student: " + id +" does not exists!", null);
        }

        Student updated = studentRepository.save(student);
        return new StudentResponse<Student>(true, "Student: " + id + " updated successfully!", updated);
    }

    public StudentResponse<Student> delete(int id) {
        if(!doesExists(id)) {
            return new StudentResponse<Student>(false, "Student: " + id +" does not exists!", null);
        }

        studentRepository.deleteById(id);
        return new StudentResponse<Student>(true, "Student: "+ id + " deleted successfully!", null);
    }
}

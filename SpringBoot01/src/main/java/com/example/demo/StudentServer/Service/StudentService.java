package com.example.demo.StudentServer.Service;

import com.example.demo.StudentServer.Dto.StudentRequestDTO;
import com.example.demo.StudentServer.Entity.Student;
import com.example.demo.StudentServer.Repository.StudentRepository;
import com.example.demo.StudentServer.Dto.StudentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }

    private boolean isValid(StudentRequestDTO studentRequestDTO) {
        if(studentRequestDTO == null) {
            return false;
        }

        String name = studentRequestDTO.name();
        int age = studentRequestDTO.age();
        String department = studentRequestDTO.department();

        return name != null && age >= 0 && department != null;
    }

    private boolean doesExists(int id) {
        return studentRepository.existsById(id);
    }

    private Student toStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setName(studentRequestDTO.name());
        student.setAge(studentRequestDTO.age());
        student.setDepartment(studentRequestDTO.department());
        return student;
    }

    public StudentResponseDTO<Student> validate(StudentRequestDTO studentRequestDTO) {
        if(!isValid(studentRequestDTO)) {
            return new StudentResponseDTO<Student>(false, "Error: Student Creation Failed, Invalid information!", null);
        }

        Student student = toStudent(studentRequestDTO);

        Student created = studentRepository.save(student);
        return new StudentResponseDTO<Student>(true, "Student created successfully!", created);
    }

    public StudentResponseDTO<List<Student>> getAll() {
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()) {
            return new StudentResponseDTO<List<Student>>(false, "No students exists!", null);
        }
        return new StudentResponseDTO<List<Student>>(true, "All students data!", students);
    }

    public StudentResponseDTO<Student> getById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) {
            return new StudentResponseDTO<Student>(false, "Student: " + id + " does not exists!", null);
        }
        return new StudentResponseDTO<Student>(true, "Student: " + id, student.get());
    }

    public StudentResponseDTO<Student> update(StudentRequestDTO studentRequestDTO, int id) {
        if(!isValid(studentRequestDTO)) {
            return new StudentResponseDTO<Student>(false, "Error: Student Creation Failed, Invalid information!", null);
        }

        Student student = toStudent(studentRequestDTO);
        student.setId(id);

        if(!doesExists(id)) {
            return new StudentResponseDTO<Student>(false, "Student: " + id +" does not exists!", null);
        }

        Student updated = studentRepository.save(student);
        return new StudentResponseDTO<Student>(true, "Student: " + id + " updated successfully!", updated);
    }

    public StudentResponseDTO<Student> delete(int id) {
        if(!doesExists(id)) {
            return new StudentResponseDTO<Student>(false, "Student: " + id +" does not exists!", null);
        }

        studentRepository.deleteById(id);
        return new StudentResponseDTO<Student>(true, "Student: "+ id + " deleted successfully!", null);
    }
}

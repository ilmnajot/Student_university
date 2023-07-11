package com.example.student_university.controller;

import com.example.student_university.dto.StudentDto;
import com.example.student_university.service.StudentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/register")
    public HttpEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto registeredStudent = studentService.saveStudent(studentDto);
        return ResponseEntity.ok(registeredStudent);

    }
    @GetMapping("/all")
    public HttpEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(allStudents);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id){
        Optional<StudentDto> student = studentService.getStudent(id);
        return student
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto){
        Optional<StudentDto> updatedStudent = studentService.updateStudent(studentId, studentDto);
        return updatedStudent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{studentId}")
    public HttpEntity<Void> removeStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    /*
    from here we integrate student with course
     */
    @PostMapping("/{studentId}/courses/{courseId}")
    public HttpEntity<StudentDto> addCourseToStudent(
            @PathVariable("studentId") Long studentId,
            @PathVariable("courseId") Long courseId
    ){
        StudentDto studentDto = studentService.addCourseToStudent(studentId, courseId);
        if (studentDto!=null){
            return ResponseEntity.ok(studentDto);
        } else{
            return ResponseEntity.notFound().build();
        }
    }



}

package com.example.student_university.service;

import com.example.student_university.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    Optional<StudentDto> getStudent(Long id);

    Optional<StudentDto> updateStudent(Long studentId, StudentDto studentDto);

    void deleteStudent(Long studentId);

    StudentDto addCourseToStudent(Long studentId, Long courseId);
}

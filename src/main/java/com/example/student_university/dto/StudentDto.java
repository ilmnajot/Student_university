package com.example.student_university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private List<CourseDto> courses;


//    public static StudentDto convertToDto(Student student){
//        StudentDto studentDto = new StudentDto();
//        studentDto.setId(student.getId());
//        studentDto.setFirstName(student.getFirstName());
//        studentDto.setLastName(student.getLastName());
//        studentDto.setEmail(student.getEmail());
//        studentDto.setPhoneNumber(student.getPhoneNumber());
//        return studentDto;
//    } b   bunaqa qilqilsaham bo''ladi
//    public static Student convertToEntity(StudentDto studentDto){
//        Student student = new Student();
//        student.setId(studentDto.getId());
//        student.setFirstName(studentDto.getFirstName());
//        student.setLastName(studentDto.getLastName());
//        student.setEmail(studentDto.getEmail());
//        student.setPhoneNumber(studentDto.getPhoneNumber());
//        return student;
//    }
}

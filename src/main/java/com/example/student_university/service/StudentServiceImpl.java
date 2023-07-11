package com.example.student_university.service;

import com.example.student_university.dto.CourseDto;
import com.example.student_university.dto.StudentDto;
import com.example.student_university.entity.Course;
import com.example.student_university.entity.Student;
import com.example.student_university.exception.HandleException;
import com.example.student_university.repository.CourseRepository;
import com.example.student_university.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    @Transactional
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = convertToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return convertToStudentDto(savedStudent);
    }

    @Override
    @Transactional
    public Optional<StudentDto> updateStudent(Long studentId, StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()){
            Student toStudent = convertToStudent(studentDto);
            toStudent.setId(studentId);
            Student save = studentRepository.save(toStudent);
            return Optional.of(convertToStudentDto(save));
        }
     throw new HandleException("student to update with " + studentId + " is not found");
    }

    @Override
    public void deleteStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(studentId);
        } else {
        throw new HandleException("student with id " + studentId + " is not found");
    }
    }

    //    @Override
//    public List<StudentDto> getAllStudents() {
//        List<Student> studentList = studentRepository.findAll();
//        return studentList.stream().map(StudentDto::convertToDto).collect(Collectors.toList()); bunaqa qilsaham bo'ladi
//    }
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(this::convertToStudentDto).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDto> getStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){

            return optionalStudent.map(this::convertToStudentDto);
        }
        throw new HandleException("there is no student with id " + id);
    }

    private StudentDto convertToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        return studentDto;
    }
    private Student convertToStudent(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        return student;
    }
    /*
    here course and students begin
     */

    @Override
    public StudentDto addCourseToStudent(Long studentId, Long courseId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            Student student = optionalStudent.get();

            student.getCourses().add(course);
            studentRepository.save(student);
            return convertToStudentDto(student) ;
        }
throw new HandleException("student with id " + studentId + " and course  with id " + courseId + " is not found");
    }


    private CourseDto convertCourseEntityToCourseDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
         courseDto.setName(course.getName());
         courseDto.setDescription(course.getDescription());
         return courseDto;
    }


}

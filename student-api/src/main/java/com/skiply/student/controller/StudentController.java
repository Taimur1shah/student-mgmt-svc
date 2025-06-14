package com.skiply.student.controller;

import com.skiply.student.api.StudentApi;
import com.skiply.student.api.model.RegisterNewStudent201Response;
import com.skiply.student.api.model.StudentRequest;
import com.skiply.student.api.model.StudentResponse;
import com.skiply.student.domain.Student;
import com.skiply.student.response.StudentResponseBuilder;
import com.skiply.student.service.StudentService;
import com.skiply.student.service.impl.StudentServiceImpl;
import java.util.NoSuchElementException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class StudentController  implements StudentApi {

  private final StudentService studentService;

  public StudentController(StudentServiceImpl studentService) {
    this.studentService = studentService;
  }

  @SneakyThrows
  @Override
  public ResponseEntity<RegisterNewStudent201Response> registerNewStudent(StudentRequest studentReq){
    Student student = new Student();
    BeanUtils.copyProperties(studentReq,student);
    Student savedStudent = studentService.saveStudent(student);

    return new ResponseEntity<>(
        StudentResponseBuilder.buildStudentCreateResponse(savedStudent.getStudentId()),
        HttpStatus.CREATED);
  }


  @Override
  public ResponseEntity<Object> getStudentById(Integer studentId){
    Student student = studentService.getStudentById(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student with ID " + studentId + " not found"));

    StudentResponse studentResp = new StudentResponse();
    BeanUtils.copyProperties(student, studentResp);
    return ResponseEntity.ok(studentResp);
  }

  @Override
  public ResponseEntity<Object> updateStudentById(Integer studentId, StudentRequest studentReq){
    Student existingStudent = studentService.getStudentById(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student with ID " + studentId + " not found"));

    BeanUtils.copyProperties(studentReq, existingStudent);
    studentService.saveStudent(existingStudent);

    StudentResponse studentResp = new StudentResponse();
    BeanUtils.copyProperties(existingStudent, studentResp);
    return ResponseEntity.ok(studentResp);
  }

  @Override
  public ResponseEntity<Void> deleteStudentById(Integer studentId){
    Student student = studentService.getStudentById(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student with ID " + studentId + " not found"));

    studentService.deleteStudent(studentId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

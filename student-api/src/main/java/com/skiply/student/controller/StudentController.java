package com.skiply.student.controller;

import com.skiply.student.api.StudentApi;
import com.skiply.student.api.model.StudentRequest;
import com.skiply.student.api.model.StudentResponse;
import com.skiply.student.domain.Student;
import com.skiply.student.service.StudentService;
import com.skiply.student.service.impl.StudentServiceImpl;
import com.skiply.student.util.StudentUtil;
import java.util.Optional;
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
  public ResponseEntity<Object> registerNewStudent(StudentRequest studentReq){
    Student student = new Student();
    BeanUtils.copyProperties(studentReq,student);
    studentService.saveStudent(student);

    StudentResponse studentResp = new StudentResponse();
    BeanUtils.copyProperties(student,studentResp);

    return ResponseEntity.ok(studentResp);
  }


  @Override
  public ResponseEntity<Object> getStudentById(Integer studentId){
    Optional<Student> student = studentService.getStudentById(studentId);
    if(student.isPresent()){
      StudentResponse studentResp = new StudentResponse();
      BeanUtils.copyProperties(student.get(),studentResp);
      return ResponseEntity.ok(studentResp);
    }else {
      StudentUtil studentUtil = new StudentUtil();
      return ResponseEntity.ok(studentUtil.getNotFoundErrorResponse());
    }
  }

  @Override
  public ResponseEntity<Object> updateStudentById(Integer studentId,StudentRequest studentReq){
    Optional<Student> existingStudent = studentService.getStudentById(studentId);
    if(existingStudent.isPresent()){
      Student updateStudent = new Student();
      updateStudent.setStudentId(studentId);
      BeanUtils.copyProperties(studentReq,updateStudent);
      studentService.saveStudent(updateStudent);

      StudentResponse studentResp = new StudentResponse();
      BeanUtils.copyProperties(updateStudent,studentResp);
      return ResponseEntity.ok(studentResp);
    }else {
      StudentUtil studentUtil = new StudentUtil();
      return ResponseEntity.ok(studentUtil.getNotFoundErrorResponse());
    }
  }

  @Override
  public ResponseEntity<Void> deleteStudentById(Integer studentId){
    Optional<Student> student = studentService.getStudentById(studentId);
    if(student.isPresent()){
      studentService.deleteStudent(studentId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      StudentUtil studentUtil = new StudentUtil();
      return ResponseEntity.notFound().build();
    }
  }
}

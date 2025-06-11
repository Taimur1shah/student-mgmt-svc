package com.skiply.student.service;

import com.skiply.student.domain.Student;
import java.util.Optional;


public interface StudentService {

  void saveStudent(Student student);

  Optional<Student> getStudentById(Integer studentId);

  void deleteStudent(Integer studentId);

}

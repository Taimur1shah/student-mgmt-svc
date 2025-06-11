package com.skiply.student.service.impl;

import com.skiply.student.domain.Student;
import com.skiply.student.repository.StudentRepository;
import com.skiply.student.service.StudentService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository repository;

  public StudentServiceImpl(StudentRepository repository) {
    this.repository = repository;
  }


  @Override
  public void saveStudent(Student student) {
     repository.save(student);
  }

  @Override
  public Optional<Student>  getStudentById(Integer studentId) {
    Optional<Student> optionalStudent = repository.findById(studentId);
    return optionalStudent;
  }

  @Override
  public void deleteStudent(Integer studentId) {
    repository.deleteById(studentId);
  }
}

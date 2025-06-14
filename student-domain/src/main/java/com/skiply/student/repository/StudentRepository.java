package com.skiply.student.repository;

import com.skiply.student.domain.Student;
import java.util.Optional;

public interface StudentRepository {
  Student save(Student student);
  Optional<Student> findById(Integer id);
  void deleteById(Integer id);
}

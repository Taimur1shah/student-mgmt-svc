package com.skiply.student.repository;

import com.skiply.student.domain.Student;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepoImpl implements StudentRepository {

  private final StudentSpringDataJpa jpaRepository;

  public StudentRepoImpl(StudentSpringDataJpa jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public void save(Student student) {
    jpaRepository.save(student);
  }

  @Override
  public Optional<Student> findById(Integer id) {
    return jpaRepository.findById(id);
  }

  @Override
  public void deleteById(Integer id) {
    jpaRepository.deleteById(id);
  }


}
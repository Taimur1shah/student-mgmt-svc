package com.skiply.student.repository;

import com.skiply.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSpringDataJpa extends JpaRepository<Student, Integer> {

}

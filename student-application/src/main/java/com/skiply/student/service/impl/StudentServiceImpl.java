package com.skiply.student.service.impl;

import com.skiply.student.domain.Student;
import com.skiply.student.repository.StudentRepository;
import com.skiply.student.service.StudentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository repository;

  private static final String SERVICE_NAME = "studentService";

  public StudentServiceImpl(StudentRepository repository) {
    this.repository = repository;
  }

  @Override
  @Retry(name = SERVICE_NAME, fallbackMethod = "fallbackSaveStudent")
  @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackSaveStudent")
  @RateLimiter(name = SERVICE_NAME)
  public Student saveStudent(Student student) {
    return repository.save(student);
  }

  @Override
  @Cacheable("students")
  @Retry(name = SERVICE_NAME, fallbackMethod = "fallbackGetStudentById")
  @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackGetStudentById")
  @RateLimiter(name = SERVICE_NAME)
  public Optional<Student> getStudentById(Integer studentId) {
    return repository.findById(studentId);
  }

  @Override
  @Retry(name = SERVICE_NAME, fallbackMethod = "fallbackDeleteStudent")
  @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackDeleteStudent")
  @RateLimiter(name = SERVICE_NAME)
  public void deleteStudent(Integer studentId) {
    repository.deleteById(studentId);
  }

  // --- FALLBACK METHODS ---
  public Student fallbackSaveStudent(Student student, Throwable ex) {
    log.error("Fallback triggered for saveStudent due to: {}", ex.getMessage());
    return new Student();
  }

  public Optional<Student> fallbackGetStudentById(Integer studentId, Throwable ex) {
    log.error("Fallback triggered for getStudentById due to: {}", ex.getMessage());
    return Optional.empty();
  }

  public void fallbackDeleteStudent(Integer studentId, Throwable ex) {
    log.error("Fallback triggered for deleteStudent due to: {}", ex.getMessage());
  }
}

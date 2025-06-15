package com.skiply.student.service.test;

import com.skiply.student.domain.Student;
import com.skiply.student.repository.StudentRepository;
import com.skiply.student.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentServiceImpl studentService;

  private Student sampleStudent;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    sampleStudent = new Student();
    sampleStudent.setStudentId(1);
    sampleStudent.setStudentName("Ali Khan");
  }

  @Test
  void testSaveStudent() {
    when(studentRepository.save(any(Student.class))).thenReturn(sampleStudent);

    Student saved = studentService.saveStudent(sampleStudent);

    assertNotNull(saved);
    assertEquals(1, saved.getStudentId());
    assertEquals("Ali Khan", saved.getStudentName());
    verify(studentRepository, times(1)).save(sampleStudent);
  }

  @Test
  void testGetStudentById_found() {
    when(studentRepository.findById(1)).thenReturn(Optional.of(sampleStudent));

    Optional<Student> result = studentService.getStudentById(1);

    assertTrue(result.isPresent());
    assertEquals("Ali Khan", result.get().getStudentName());
    verify(studentRepository, times(1)).findById(1);
  }

  @Test
  void testGetStudentById_notFound() {
    when(studentRepository.findById(2)).thenReturn(Optional.empty());

    Optional<Student> result = studentService.getStudentById(2);

    assertFalse(result.isPresent());
    verify(studentRepository, times(1)).findById(2);
  }

  @Test
  void testDeleteStudent() {
    doNothing().when(studentRepository).deleteById(1);

    studentService.deleteStudent(1);

    verify(studentRepository, times(1)).deleteById(1);
  }
}


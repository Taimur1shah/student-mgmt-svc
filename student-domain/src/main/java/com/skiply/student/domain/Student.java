package com.skiply.student.domain;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import jdk.jfr.DataAmount;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id", nullable = false, updatable = false)
  private Integer studentId;

  @Column(nullable = false)
  private String studentName;

  @Column(nullable = false)
  private String grade;

  @Column(name = "mobile_number", nullable = false)
  private String mobileNumber;

  @Column(name = "school_name", nullable = false)
  private String schoolName;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Student() {
    this.createdAt = LocalDateTime.now();
  }
}


package com.skiply.student;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.skiply.student", "com.skiply.student.api"})
public class StudentApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentApplication.class, args);
  }


}

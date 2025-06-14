package com.skiply.student.response;

import com.skiply.student.api.model.RegisterNewStudent201Response;

public  class StudentResponseBuilder {
 public static RegisterNewStudent201Response buildStudentCreateResponse(Integer studentId){
   RegisterNewStudent201Response registerNewStudent201Response = new RegisterNewStudent201Response();
   registerNewStudent201Response.setStudentId(studentId);
   return registerNewStudent201Response;
 }
}

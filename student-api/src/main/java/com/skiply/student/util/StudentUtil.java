package com.skiply.student.util;

import com.skiply.student.api.model.APIErrorResponse;

public class StudentUtil {

  public APIErrorResponse getNotFoundErrorResponse(){
    APIErrorResponse errorResponse = new APIErrorResponse();
    errorResponse.setCode("404");
    errorResponse.setDetail("Resource Not Found");
    errorResponse.setName("NOT_FOUND");
    errorResponse.setReason("Record does not exist");
    return errorResponse;
  }

}

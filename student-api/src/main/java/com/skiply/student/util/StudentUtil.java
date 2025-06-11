package com.skiply.student.util;

import com.skiply.student.api.model.ErrorResponse;

public class StudentUtil {

  public ErrorResponse getNotFoundErrorResponse(){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode("404");
    errorResponse.setDetail("Resource Not Found");
    errorResponse.setName("NOT_FOUND");
    errorResponse.setReason("Record does not exist");
    return errorResponse;
  }

}

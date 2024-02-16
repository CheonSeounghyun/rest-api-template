package com.wini.restapitemplate.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ApiExceptionControllerAdvice {

    public ResponseEntity<Object> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e){
        return ResponseEntity.status(405).body(new ApiResponse(ApiResponseConstants.STATUS_ERROR, ApiResponseConstants.MESSAGE_METHOD_NOT_ALLOWED_ERROR, e.getMessage()));
    }
}

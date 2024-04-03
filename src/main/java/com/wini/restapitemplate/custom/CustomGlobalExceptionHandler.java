package com.wini.restapitemplate.custom;

import com.wini.restapitemplate.api.ApiErrorResponse;
import com.wini.restapitemplate.api.ApiResponseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        BindingResult bindingResult = exception.getBindingResult();
        String message = "Validation failed for object='" + bindingResult.getObjectName() + "'. Error count: " + bindingResult.getErrorCount();

        logger.error("message", exception);

        return ResponseEntity.status(400).body(new ApiErrorResponse(ApiResponseConstants.STATUS_FAIL, message, errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        String message = "Required request body is missing or can not readable";

        logger.error("message", exception);

        return ResponseEntity.status(400).body(new ApiErrorResponse(ApiResponseConstants.STATUS_FAIL, message, null));
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<Object> handleBadSqlGrammarException(BadSqlGrammarException exception) {

        String message = ApiResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR;

        logger.error("message", exception);

        return ResponseEntity.status(400).body(new ApiErrorResponse(ApiResponseConstants.STATUS_ERROR, message, "Bad Sql Grammar Exception"));
    }
}

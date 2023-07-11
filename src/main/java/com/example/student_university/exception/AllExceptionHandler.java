package com.example.student_university.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AllExceptionHandler {


    @ExceptionHandler(HandleException.class)
    public HttpEntity<?> handleException(HandleException handleException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), handleException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}

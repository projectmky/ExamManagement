package com.example.ExamManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicantRestExceptionHandler {
    //Handles the exception between the REST client and REST Service which throws the exception.
    //Global Exceptions handling


    @ExceptionHandler
    public ResponseEntity<ApplicantErrorResponse> handleException(ApplicantNotFoundException exception) {
        ApplicantErrorResponse response = new ApplicantErrorResponse();
        response.setMessage(exception.getMessage());
        response.setErrorStatus(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApplicantErrorResponse> handleException(Exception e) {
        ApplicantErrorResponse response = new ApplicantErrorResponse();
        response.setMessage(e.getMessage());
        response.setErrorStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

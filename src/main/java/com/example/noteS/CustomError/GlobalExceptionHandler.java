package com.example.noteS.CustomError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseError> handleGenericException(Exception ex) {
        CustomResponseError error = new CustomResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
        System.out.println("Error: " + ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
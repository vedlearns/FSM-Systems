package com.hackerrank.restcontrolleradvice.exception;

import com.hackerrank.restcontrolleradvice.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class FizzBuzzExceptionHandler extends ResponseEntityExceptionHandler {

  //TODO:: implement handler methods for FizzException, BuzzException and FizzBuzzException
    @ExceptionHandler(FizzBuzzException.class)
    public ResponseEntity<GlobalError> handleFizzBuzzException(FizzBuzzException e){
        GlobalError response = new GlobalError(e.getMessage(), e.getErrorReason());
        return new ResponseEntity<>(response, HttpStatus.INSUFFICIENT_STORAGE);
    }

    @ExceptionHandler(FizzException.class)
    public ResponseEntity<GlobalError> handelFizzException(FizzException e){
        return new ResponseEntity<>(new GlobalError(e.getMessage(), e.getErrorReason()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BuzzException.class)
    public ResponseEntity<GlobalError> handelBuzzException(BuzzException e){
        return new ResponseEntity<>(new GlobalError(e.getMessage(), e.getErrorReason()), HttpStatus.BAD_REQUEST);
    }

}

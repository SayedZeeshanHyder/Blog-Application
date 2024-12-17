package com.blogapp.blog.Exceptions;

import com.blogapp.blog.Payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String,String> resp = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
          String fieldName = ((FieldError)error).getField();
          String errorMessage = error.getDefaultMessage();
          resp.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}

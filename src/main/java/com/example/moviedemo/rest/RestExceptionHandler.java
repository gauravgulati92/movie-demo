package com.example.moviedemo.rest;

import com.example.moviedemo.dtos.response.AppResponse;
import com.example.moviedemo.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
	public ResponseEntity<AppResponse> notFound(NotFoundException exception) {		
            AppResponse response = new AppResponse();
            response.setDescription(exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        
	}

}
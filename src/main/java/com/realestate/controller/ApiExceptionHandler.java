/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realestate.controller;

import com.realestate.domain.ApiException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Administrator
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        ApiException apiException = new ApiException();
        apiException.setMessage(e.getMessage());
        apiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiException.setThrowable(e.getCause());
        apiException.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        ApiException apiException = new ApiException();
        apiException.setMessage(e.getMessage());
        apiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiException.setThrowable(e.getCause());
        apiException.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        ApiException apiException = new ApiException();
        apiException.setMessage("File is too big!\nMaximum permitted size of 1048576 bytes.");
        apiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiException.setThrowable(e.getCause());
        apiException.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException apiException = new ApiException();
        apiException.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        apiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiException.setThrowable(ex.getCause());
        apiException.setTimestamp(new Timestamp(System.currentTimeMillis()));
        
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
    
}

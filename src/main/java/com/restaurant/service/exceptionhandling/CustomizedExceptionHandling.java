package com.restaurant.service.exceptionhandling;

import com.restaurant.beans.dto.ErrorResponse;
import com.restaurant.beans.dto.ErrorType;
import com.restaurant.beans.exception.DetailsNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DetailsNotFound.class)

    public ResponseEntity<?> handleExceptions(DetailsNotFound exception, WebRequest webRequest) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorType(ErrorType.NO_RESTAURANT_FOUND);
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Restaurant Not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }
}
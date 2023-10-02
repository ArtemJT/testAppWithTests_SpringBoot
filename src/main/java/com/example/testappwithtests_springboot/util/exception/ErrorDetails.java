package com.example.testappwithtests_springboot.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * @author Artem Kovalov on 01.10.2023
 */
public record ErrorDetails(Date timestamp, Integer status, HttpStatus error, String details, String message) {
    public static ResponseEntity<ErrorDetails> getResponseEntity(String message, WebRequest request, HttpStatus status) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), status.value(), status, request.getDescription(false), message);
        return new ResponseEntity<>(errorDetails, status);
    }
}

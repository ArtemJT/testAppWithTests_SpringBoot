package com.example.testappwithtests_springboot.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

import static com.example.testappwithtests_springboot.util.exception.ErrorDetails.getResponseEntity;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@ControllerAdvice
public record ServiceExceptionHandler() {

    @ExceptionHandler(ResourceWasDeletedException.class)
    ResponseEntity<ErrorDetails> handleResourceDeleted(WebRequest request, ResourceWasDeletedException ex) {
        String message = ex != null ? ex.getLocalizedMessage() : "Resource was deleted";
        return getResponseEntity(message, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    ResponseEntity<ErrorDetails> handleSqlException(WebRequest request, SQLException ex) {
        return getResponseEntity(ex.getLocalizedMessage(), request, HttpStatus.NOT_FOUND);
    }

}

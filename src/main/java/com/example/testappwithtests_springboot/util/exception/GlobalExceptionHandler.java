package com.example.testappwithtests_springboot.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.format.DateTimeParseException;

import static com.example.testappwithtests_springboot.util.exception.ErrorDetails.getResponseEntity;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    ResponseEntity<ErrorDetails> handleNumberFormatException(WebRequest request) {
        return getResponseEntity("Request has error number format", request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(WebRequest request, MethodArgumentNotValidException ex) {
        Object[] detailMessageArguments = ex.getDetailMessageArguments();
        String message = detailMessageArguments != null ? detailMessageArguments[1].toString() : ex.getMessage();
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    ResponseEntity<ErrorDetails> handleDateTimeParseException(WebRequest request, DateTimeParseException ex) {
        return getResponseEntity(ex.getLocalizedMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateRangeException.class)
    ResponseEntity<ErrorDetails> handleDateRangeException(WebRequest request, DateRangeException ex) {
        return getResponseEntity(ex.getLocalizedMessage(), request, HttpStatus.BAD_REQUEST);
    }

}

package com.example.testappwithtests_springboot.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Artem Kovalov on 02.10.2023
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateRangeException extends RuntimeException {

    public DateRangeException(String message) {
        super(message);
    }
}

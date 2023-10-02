package com.example.testappwithtests_springboot.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceWasDeletedException extends RuntimeException {

    public ResourceWasDeletedException() {
        super();
    }

    public ResourceWasDeletedException(String message) {
        super(message);
    }
}

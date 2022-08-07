package com.example.demo.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonExistException extends RuntimeException {
    public PersonExistException(String message) {
        super(message);
    }
}

package com.example.demo.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyMemberDefinedException extends RuntimeException {

    public AlreadyMemberDefinedException() {
        super();
    }

    public AlreadyMemberDefinedException(String exception) {
        super(exception);
    }

}
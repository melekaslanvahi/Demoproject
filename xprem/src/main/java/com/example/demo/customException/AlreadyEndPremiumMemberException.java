package com.example.demo.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyEndPremiumMemberException extends RuntimeException {

    public AlreadyEndPremiumMemberException() {
        super();
    }

    public AlreadyEndPremiumMemberException(String exception) {
        super(exception);
    }

}
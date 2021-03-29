package com.example.demo.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyPremiumMemberException extends RuntimeException {

    public AlreadyPremiumMemberException() {
        super();
    }

    public AlreadyPremiumMemberException(String exception) {
        super(exception);
    }

}
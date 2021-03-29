package com.example.demo.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotPremiumMemberException extends RuntimeException {

    public NotPremiumMemberException() {
        super();
    }

    public NotPremiumMemberException(String exception) {
        super(exception);
    }

}
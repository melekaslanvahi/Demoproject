package com.example.demo.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotPaymentException extends RuntimeException {

    public NotPaymentException() {
        super();
    }

    public NotPaymentException(String exception) {
        super(exception);
    }

}
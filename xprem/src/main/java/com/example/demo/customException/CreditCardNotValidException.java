package com.example.demo.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditCardNotValidException extends RuntimeException {

    public CreditCardNotValidException() {
        super();
    }

    public CreditCardNotValidException(String exception) {
        super(exception);
    }

}
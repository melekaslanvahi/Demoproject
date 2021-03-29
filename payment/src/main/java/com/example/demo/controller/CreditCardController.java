package com.example.demo.controller;

import com.example.demo.model.GetPaymentAutomaticlyRequest;
import com.example.demo.model.GetPaymentAutomaticlyResponse;
import com.example.demo.model.GetPaymentFromCardRequest;
import com.example.demo.model.GetPaymentFromCardResponse;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("creditcards")
@CrossOrigin
@Validated
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping(value = "getPaymentFromCard")
    public ResponseEntity<GetPaymentFromCardResponse> getPaymentFromCard(@RequestBody @Validated GetPaymentFromCardRequest getPaymentFromCardRequest) {

        GetPaymentFromCardResponse response = creditCardService.getPaymentFromCard( getPaymentFromCardRequest );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value= "getPaymentAutomaticly")
    public ResponseEntity<GetPaymentAutomaticlyResponse> getPaymentAutomaticly(@RequestBody @Validated GetPaymentAutomaticlyRequest getPaymentFromCardRequest) {

        GetPaymentAutomaticlyResponse response = creditCardService.getPaymentAutomaticly( getPaymentFromCardRequest );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

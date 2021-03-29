package com.example.demo.service;

import com.example.demo.models.GetPaymentAutomaticlyRequest;
import com.example.demo.models.GetPaymentAutomaticlyResponse;
import com.example.demo.models.GetPaymentFromCardRequest;
import com.example.demo.models.GetPaymentFromCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "payment", url = "http://localhost:8091/payment")
public interface CreditCardService {

    @PostMapping("/api/v1/creditcards/getPaymentFromCard")
    ResponseEntity<GetPaymentFromCardResponse> getPaymentFromCard(@RequestBody @Validated GetPaymentFromCardRequest getPaymentFromCardRequest );

    @PostMapping("/api/v1/creditcards/getPaymentAutomaticly")
    ResponseEntity<GetPaymentAutomaticlyResponse> getPaymentAutomaticly(@RequestBody @Validated GetPaymentAutomaticlyRequest getPaymentFromCardRequest );
}

package com.example.demo.service;

import com.example.demo.models.GetPaymentAutomaticlyRequest;
import com.example.demo.models.GetPaymentAutomaticlyResponse;
import com.example.demo.models.GetPaymentFromCardRequest;
import com.example.demo.models.GetPaymentFromCardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CreditCardClient {

    private static final String URL_PAYMENT = "http://localhost:8091/payment/api/v1/creditcards/getPaymentFromCard";
    private static final String URL_PAYMENT_AUTOMATICLY = "http://localhost:8091/payment/api/v1/creditcards/getPaymentAutomaticly";

    public ResponseEntity<GetPaymentFromCardResponse>  callGetPaymentFromCard( GetPaymentFromCardRequest request ) {

        var restTemplate = new RestTemplate();
        ResponseEntity<GetPaymentFromCardResponse> response = restTemplate.postForEntity(URL_PAYMENT, request, GetPaymentFromCardResponse.class);
        return response;
    }

    public ResponseEntity<GetPaymentAutomaticlyResponse>  callGetPaymentFromCardAutomaticly(GetPaymentAutomaticlyRequest request ) {

        var restTemplate = new RestTemplate();
        ResponseEntity<GetPaymentAutomaticlyResponse> response = restTemplate.postForEntity(URL_PAYMENT_AUTOMATICLY, request, GetPaymentAutomaticlyResponse.class);
        return response;
    }
}

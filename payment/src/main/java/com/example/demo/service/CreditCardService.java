package com.example.demo.service;

import com.example.demo.model.GetPaymentAutomaticlyRequest;
import com.example.demo.model.GetPaymentAutomaticlyResponse;
import com.example.demo.model.GetPaymentFromCardRequest;
import com.example.demo.model.GetPaymentFromCardResponse;

import java.util.List;

public interface CreditCardService {

    GetPaymentFromCardResponse getPaymentFromCard(GetPaymentFromCardRequest request);
    GetPaymentAutomaticlyResponse getPaymentAutomaticly(GetPaymentAutomaticlyRequest request );
}

package com.example.demo.models;

import lombok.Data;

@Data
public class GetPaymentMemberInfo {

    Long userId;
    boolean receivedPayment;
}

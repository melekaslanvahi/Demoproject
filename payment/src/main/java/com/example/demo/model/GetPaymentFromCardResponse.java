package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetPaymentFromCardResponse {

    @ApiModelProperty(notes = "paymentReceived")
    private boolean paymentReceived;
}

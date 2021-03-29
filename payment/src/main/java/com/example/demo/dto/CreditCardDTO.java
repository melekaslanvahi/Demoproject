package com.example.demo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreditCardDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 229012051235676780L;

    @ApiModelProperty(notes = "name")
    private String name;

    @ApiModelProperty(notes = "surname")
    private String surname;

    @ApiModelProperty(notes = "cardNumber")
    private String cardNumber;

    @ApiModelProperty(notes = "cvv")
    private int cvv;

    @ApiModelProperty(notes = "cardExpireDate")
    private String cardExpireDate;

    @ApiModelProperty(notes = "userId")
    private Long userId;
}

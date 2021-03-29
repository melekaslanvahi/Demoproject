package com.example.demo.models;

import com.example.demo.utils.Constants;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import validation.Email;


@Data
public class GetPaymentFromCardRequest {

    @ApiModelProperty(notes = "name")
    @NotNull
    private String name;

    @ApiModelProperty(notes = "surname")
    @NotNull
    private String surname;

    @ApiModelProperty(notes = "cardNumber")
    @NotNull
    private String cardNumber;

    @ApiModelProperty(notes = "cvv")
    @NotNull
    //@Size(max = Constants.MAX_CVV)
    private int cvv;

    @ApiModelProperty(notes = "cardExpireDate")
    @NotNull
    private String cardExpireDate;

    @NotNull
    @ApiModelProperty(notes = "userId")
    private Long userId;

    @NotNull
    @ApiModelProperty(notes = "email")
    @Email
    private String email;

}

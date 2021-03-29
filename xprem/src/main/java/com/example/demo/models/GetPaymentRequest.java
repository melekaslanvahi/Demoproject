package com.example.demo.models;

import com.example.demo.utils.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GetPaymentRequest {

    @ApiModelProperty(notes = "name")
    @NotNull
    @Size(max = Constants.MAX_NAME_SIZE)
    private String name;

    @ApiModelProperty(notes = "surname")
    @NotNull
    @Size(max = Constants.MAX_SURNAME_SIZE)
    private String surname;

    @ApiModelProperty(notes = "cardNumber")
    @NotNull
    @Size(max = Constants.MAX_CARD_NUMBER_REQUEST_SIZE)
    private String cardNumber;

    @ApiModelProperty(notes = "cvv")
    @NotNull
    private int cvv;

    @ApiModelProperty(notes = "cardExpireDate")
    @NotNull
    @Size(max = Constants.MAX_EXPIRE_DATE)
    private String cardExpireDate;

    @NotNull
    @ApiModelProperty(notes = "userId")
    private Long userId;
}

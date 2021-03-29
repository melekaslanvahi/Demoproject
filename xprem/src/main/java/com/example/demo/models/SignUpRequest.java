package com.example.demo.models;

import com.example.demo.utils.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @ApiModelProperty(notes = "name")
    @NotNull
    @Size(max = Constants.MAX_NAME_SIZE)
    private String name;

    @ApiModelProperty(notes = "surname")
    @NotNull
    @Size(max = Constants.MAX_SURNAME_SIZE)
    private String surname;

    @NotNull
    @ApiModelProperty(notes = "email is used as username")
    @Email
    private String email;

    @ApiModelProperty(notes = "password")
    @NotNull
    @Size(max = Constants.MAX_REQUEST_PASSWORD_SIZE)
    private String password;
}

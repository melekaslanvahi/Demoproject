package com.example.demo.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class MemberInfo {

    @NotNull
    @ApiModelProperty(notes = "userId")
    private Long userId;

    @NotNull
    @ApiModelProperty(notes = "email")
    @Email
    private String email;
}

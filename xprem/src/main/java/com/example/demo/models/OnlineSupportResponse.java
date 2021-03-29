package com.example.demo.models;

import com.example.demo.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OnlineSupportResponse {

    private String subject;

    private String message;

    private Long user;
}

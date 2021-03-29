package com.example.demo.models;

import com.example.demo.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OnlineSupportRequest {

    @NotNull
    @Size(max = Constants.MAX_SUBJECT_SIZE)
    private String subject;

    @NotNull
    @Size(max = Constants.MAX_MESSAGE_SIZE)
    private String message;

    private Long user;
}

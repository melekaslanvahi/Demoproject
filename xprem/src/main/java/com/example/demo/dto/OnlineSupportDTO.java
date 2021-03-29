package com.example.demo.dto;

import com.example.demo.utils.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class OnlineSupportDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = 845170338657401320L;

    @NotNull
    @Size(max = Constants.MAX_SUBJECT_SIZE)
    private String subject;

    @NotNull
    @Size(max = Constants.MAX_MESSAGE_SIZE)
    private String message;

    @NotNull
    private Long user;
}

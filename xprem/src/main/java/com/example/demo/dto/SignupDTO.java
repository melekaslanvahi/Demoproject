package com.example.demo.dto;

import com.example.demo.utils.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SignupDTO implements Serializable {

    private static final long serialVersionUID = 2290120512316448001L;

    private String name;

    private String surname;

    private String email;

    private String password;
}

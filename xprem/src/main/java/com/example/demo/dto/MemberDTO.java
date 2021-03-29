package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 2290120512316448000L;

    @ApiModelProperty(notes = "name")
    private String name;

    @ApiModelProperty(notes = "surname")
    private String surname;

    @ApiModelProperty(notes = "email")
    private String email;

    @ApiModelProperty(notes = "membershipDate")
    private String membershipDate;

    @ApiModelProperty(notes = "membershipStatus")
    private String membershipStatus;

    @ApiModelProperty(notes = "membershipType")
    private String membershipType;

    @ApiModelProperty(notes = "memberShipEndDate")
    private String memberShipEndDate;
}

package com.example.demo.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetPaymentAutomaticlyRequest {

    @NotNull
    @ApiModelProperty(notes = "membersInfos")
    List<MemberInfo> membersInfos;
}

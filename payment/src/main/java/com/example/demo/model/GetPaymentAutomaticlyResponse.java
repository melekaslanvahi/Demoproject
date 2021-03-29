package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetPaymentAutomaticlyResponse {

    @ApiModelProperty(notes = "GetPaymentMemberInfo")
    List<GetPaymentMemberInfo> getPaymentMemberInfos;
}

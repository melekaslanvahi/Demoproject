package com.example.demo.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
public class GetPaymentAutomaticlyResponse {

    @ApiModelProperty(notes = "GetPaymentMemberInfo")
    List<GetPaymentMemberInfo> getPaymentMemberInfos;
}

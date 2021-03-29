package com.example.demo.model;

import com.example.demo.utils.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GetPaymentAutomaticlyRequest {

    @ApiModelProperty(notes = "membersInfos")
    List<MemberInfo> membersInfos;
}

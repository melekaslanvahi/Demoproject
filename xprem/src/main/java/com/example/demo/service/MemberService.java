package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.models.EndMembershipResponse;
import com.example.demo.models.GetPaymentRequest;
import com.example.demo.models.GetPaymentResponse;
import com.example.demo.models.SignUpRequest;

import java.util.List;

public interface MemberService {

    List<MemberDTO> findAll();

    MemberDTO findById(Long id);

    GetPaymentResponse getPayment(GetPaymentRequest request);

    EndMembershipResponse endMembership(Long id);

    MemberDTO signUp(SignUpRequest request);
}

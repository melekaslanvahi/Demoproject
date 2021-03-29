package com.example.demo.service;

import com.example.demo.models.OnlineSupportRequest;
import com.example.demo.models.OnlineSupportResponse;
import org.springframework.stereotype.Service;

@Service
public interface OnlineSupportService {

    OnlineSupportResponse addOnlineSupport(OnlineSupportRequest onlineSupportRequest);
}

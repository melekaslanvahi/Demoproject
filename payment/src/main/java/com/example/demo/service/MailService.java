package com.example.demo.service;

import com.example.demo.model.CallerPerson;
import org.springframework.stereotype.Service;

public interface MailService {
    void sendEmail(CallerPerson callerPerson);
}

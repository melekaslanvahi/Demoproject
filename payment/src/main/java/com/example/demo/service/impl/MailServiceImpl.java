package com.example.demo.service.impl;

import com.example.demo.model.CallerPerson;
import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(CallerPerson callerPerson) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(callerPerson.getEmail());
        msg.setSubject(callerPerson.getSubject());
        msg.setText(callerPerson.getMessage());
        msg.setFrom("info@gmail.com");
        //javaMailSender.send(msg);
        System.out.println("Mail gönderildi.");
    }
}

package com.example.demo.listener;

import com.example.demo.model.CallerPerson;
import com.example.demo.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallerPersonListener {
    //kuyruktan surekli çağrıları dinliyecek alacak işleyecek

    @Autowired
    MailService mailService;

    @Autowired
    public CallerPersonListener( MailService mailService){
        this.mailService = mailService;
    }

    @RabbitListener(queues = "email-queue") //message'ı bu kuyruktan dinliyecek
    public void handleMessage(CallerPerson callerPerson){ //çağrıyı alıp process eder
        System.out.println("call received..");
        System.out.println(callerPerson.toString()); //aldıgı her msjı ekrana yazack

        mailService.sendEmail( callerPerson );
    }

}
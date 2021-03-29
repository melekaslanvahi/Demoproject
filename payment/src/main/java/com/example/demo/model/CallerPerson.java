package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class CallerPerson implements Serializable {

    private String callerPersonId;
    private String callerPerson;
    private OffsetDateTime createdAt;
    private  Boolean seen;
    private String subject;
    private String message;
    private String email;
}
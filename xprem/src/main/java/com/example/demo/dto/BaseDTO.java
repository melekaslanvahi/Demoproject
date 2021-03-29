package com.example.demo.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class BaseDTO implements Serializable {

    protected Long id;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME )
    private OffsetDateTime createdDate;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME )
    private OffsetDateTime updatedDate;
}

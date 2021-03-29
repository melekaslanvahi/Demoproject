package com.example.demo.domain.entity;

import com.example.demo.dto.BaseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Data
public abstract class BaseEntity<T extends BaseEntity, D extends BaseDTO> {

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE)
    private OffsetDateTime createdDate;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private OffsetDateTime updatedDate;
}

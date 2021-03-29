package com.example.demo.dto;

import lombok.Getter;

@Getter
public enum ApiErrorCodes {
    MISSING_MEMBER(100, "Üye bulunamadı."),
    BAD_MEMBER(110, ""),
    BAD_PARAMETER(120, ""),
    BAD_BACKEND(130, ""),
    NOT_VALID_CARD(160 , "Geçersiz kart !") ;

    private int errorId;
    private String errorMessage;

    private ApiErrorCodes(int errorId, String errorMessage) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }
}


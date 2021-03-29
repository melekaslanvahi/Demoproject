package com.example.demo.dto;

import lombok.Getter;

@Getter
public enum ApiErrorCodes {
    MISSING_MEMBER(100, "Üye bulunamadı."),
    BAD_MEMBER(110, ""),
    BAD_PARAMETER(120, ""),
    BAD_BACKEND(130, ""),
    NOT_PREMIUM(140 , "Premium Üyelik Gerektirir."),
    ALREADY_PREMIUM(150, "Zaten Premium Üyesiniz."),
    NOT_VALID_CARD(160 , "Geçersiz kart !"),
    NOT_PAYMENT(170 , "Ödeme alınamadı !"),
    ALREADY_END_PREMIUM(180, "Üyeliğiniz zaten daha önceden sonlandırılmıştır !"),
    ALREADY_MEMBER_DEFINED(180, "Üyeliğiniz daha önceden gerçekleştirilmiştir !")
    ;

    private int errorId;
    private String errorMessage;

    private ApiErrorCodes(int errorId,String errorMessage) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }
}


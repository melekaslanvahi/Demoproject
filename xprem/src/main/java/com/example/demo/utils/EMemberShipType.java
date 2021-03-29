package com.example.demo.utils;

import lombok.Getter;

@Getter
public enum EMemberShipType {

    PREMIUM(1, "Premium Üye"),
    STANDART(2, "Standart Üye"),
    UNKNOWN(3, "Bilinmeyen Üye")
    ;

    private int code;
    private String name;

    EMemberShipType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EMemberShipType getById(int id) {
        for(EMemberShipType e : values()) {
            if(e.code == id ) return e;
        }
        return UNKNOWN;
    }
}

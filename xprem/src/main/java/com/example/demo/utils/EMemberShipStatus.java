package com.example.demo.utils;

import lombok.Getter;

@Getter
public enum EMemberShipStatus {
    CONTINUE(1, "Üyelik Devam Ediyor"),
    END(2, "Üyelik sonlandırıldı"),
    UNKNOWN(3,"Üyelik Bilinmiyor")
    ;

    private int code;
    private String name;

    EMemberShipStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EMemberShipStatus getById(int id) {
        for(EMemberShipStatus e : values()) {
            if(e.code == id ) return e;
        }
        return UNKNOWN;
    }
}

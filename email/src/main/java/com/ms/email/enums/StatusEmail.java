package com.ms.email.enums;

public enum StatusEmail {
    SENT("Sent"),
    ERROR("Error");

    private final String status;

    StatusEmail(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}

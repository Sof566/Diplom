package com.example.vodocanalmobileapp.models;

public class ConfirmRequest {
    public String email;
    public String code;

    public ConfirmRequest(String email, String code){
        this.email = email;
        this.code = code;
    }

}
package com.example.vodocanalmobileapp.models;

public class AddAccountRequest {
    public String email;
    public String account;

    public AddAccountRequest(String email, String account){
        this.email = email;
        this.account = account;
    }
}

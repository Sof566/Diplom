package com.example.vodocanalmobileapp.models;

public class MeterRequest {
    public String email;
    public String account;
    public int meter;

    public MeterRequest(String email, String account, int meter){
        this.email = email;
        this.account = account;
        this.meter = meter;
    }
}

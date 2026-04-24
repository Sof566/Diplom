package com.example.vodocanalmobileapp.api;

import com.example.vodocanalmobileapp.User;
import com.example.vodocanalmobileapp.models.AccountsResponse;
import com.example.vodocanalmobileapp.models.AddAccountRequest;
import com.example.vodocanalmobileapp.models.BasicResponse;
import com.example.vodocanalmobileapp.models.ConfirmRequest;
import com.example.vodocanalmobileapp.models.EmailRequest;
import com.example.vodocanalmobileapp.models.LoginRequest;
import com.example.vodocanalmobileapp.models.LoginResponse;
import com.example.vodocanalmobileapp.models.MeterRequest;
import com.example.vodocanalmobileapp.models.MeterResponse;
import com.example.vodocanalmobileapp.models.RegisterStartRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/register_start")
    Call<BasicResponse> registerStart(@Body RegisterStartRequest request);

    @POST("/register_confirm")
    Call<BasicResponse> confirmEmail(@Body ConfirmRequest request);

    @POST("/register")
    Call<BasicResponse> register(@Body User user);

    @POST("/accounts")
    Call<AccountsResponse> getAccounts(@Body EmailRequest request);

    @POST("/add_account")
    Call<BasicResponse> addAccount(@Body AddAccountRequest request);

    @POST("/meter")
    Call<MeterResponse> sendMeter(@Body MeterRequest request);
}
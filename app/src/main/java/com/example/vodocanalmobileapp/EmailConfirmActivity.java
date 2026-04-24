package com.example.vodocanalmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodocanalmobileapp.api.ApiService;
import com.example.vodocanalmobileapp.api.RetrofitClient;
import com.example.vodocanalmobileapp.models.BasicResponse;
import com.example.vodocanalmobileapp.models.ConfirmRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailConfirmActivity extends AppCompatActivity {

    EditText codeInput;
    Button confirmButton;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirm);

        codeInput = findViewById(R.id.codeInput);
        confirmButton = findViewById(R.id.confirmButton);

        email = getIntent().getStringExtra("email");

        confirmButton.setOnClickListener(v -> confirm());
    }

    private void confirm(){

        String code = codeInput.getText().toString();

        if(code.length() != 6){
            codeInput.setError("Введите 6 цифр");
            return;
        }

        ConfirmRequest req = new ConfirmRequest(email,code);

        ApiService api = RetrofitClient
                .getInstance(this)
                .create(ApiService.class);

        Call<BasicResponse> call = api.confirmEmail(req);

        call.enqueue(new Callback<BasicResponse>() {

            @Override
            public void onResponse(Call<BasicResponse> call,
                                   Response<BasicResponse> response) {

                if(response.isSuccessful() && response.body()!=null){

                    if(response.body().status.equals("success")){

                        Toast.makeText(
                                EmailConfirmActivity.this,
                                "Регистрация завершена",
                                Toast.LENGTH_LONG
                        ).show();

                        Intent intent =
                                new Intent(EmailConfirmActivity.this,
                                        LoginActivity.class);

                        startActivity(intent);
                        finish();

                    }else{

                        Toast.makeText(
                                EmailConfirmActivity.this,
                                "Неверный код",
                                Toast.LENGTH_LONG
                        ).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {

                Toast.makeText(
                        EmailConfirmActivity.this,
                        "Ошибка соединения",
                        Toast.LENGTH_LONG
                ).show();

            }
        });
    }
}

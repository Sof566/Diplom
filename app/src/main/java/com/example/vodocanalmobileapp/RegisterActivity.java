package com.example.vodocanalmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vodocanalmobileapp.api.ApiService;
import com.example.vodocanalmobileapp.api.RetrofitClient;
import com.example.vodocanalmobileapp.models.BasicResponse;
import com.example.vodocanalmobileapp.models.LoginResponse;
import com.example.vodocanalmobileapp.models.RegisterStartRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;

    private EditText name, email, city, street, house,
            flat, surname, patronymic, phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.tb_name);
        registerButton = findViewById(R.id.btn_register);



        /*password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        house = findViewById(R.id.house);
        flat = findViewById(R.id.flat);
        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        patronymic = findViewById(R.id.patronymic);
        phone = findViewById(R.id.phone);*/

        //registerButton = findViewById(R.id.btn_submit);

        registerButton.setOnClickListener(v -> register());
    }

    private void register(){

        if(!validateFields()) return;

        RegisterStartRequest req = new RegisterStartRequest();

        req.name = name.getText().toString();

        /*req.email = email.getText().toString();
        req.password = password.getText().toString();
        req.city = city.getText().toString();
        req.street = street.getText().toString();
        req.house = house.getText().toString();
        req.flat = flat.getText().toString();
        req.surname = surname.getText().toString();
        req.name = name.getText().toString();
        req.patronymic = patronymic.getText().toString();
        req.phone = phone.getText().toString();*/

        ApiService api = RetrofitClient
                .getInstance(this)
                .create(ApiService.class);

        Call<BasicResponse> call = api.registerStart(req);

        call.enqueue(new Callback<BasicResponse>() {

            @Override
            public void onResponse(Call<BasicResponse> call,
                                   Response<BasicResponse> response) {

                if(!response.isSuccessful()){

                    Toast.makeText(RegisterActivity.this,
                            "Ошибка сервера: " + response.code(),
                            Toast.LENGTH_LONG).show();

                    return;
                }

                BasicResponse res = response.body();

                if(res == null){

                    Toast.makeText(RegisterActivity.this,
                            "Пустой ответ сервера",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                if(res.status.equals("code_sent")){

                    Toast.makeText(RegisterActivity.this,
                            "Код отправлен на почту",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(
                            RegisterActivity.this,
                            EmailConfirmActivity.class
                    );

                    intent.putExtra("email",req.email);

                    startActivity(intent);

                }else{

                    Toast.makeText(RegisterActivity.this,
                            "Ошибка: " + res.status,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this,
                        "Ошибка соединения",
                        Toast.LENGTH_LONG).show();

            }
        });
    }

    private boolean validateFields(){

        boolean valid = true;

        //String emailStr = email.getText().toString();
        //String phoneStr = phone.getText().toString();
        //String passwordStr = password.getText().toString();

        /*if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            email.setError("Некорректный email");
            valid = false;
        }

        if(passwordStr.length() < 4){
            password.setError("Минимум 4 символа");
            valid = false;
        }

        if(!phoneStr.matches("\\d{10,11}")){
            phone.setError("Телефон должен содержать цифры");
            valid = false;
        }*/

        return valid;
    }
}


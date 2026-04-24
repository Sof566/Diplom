package com.example.vodocanalmobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodocanalmobileapp.api.ApiService;
import com.example.vodocanalmobileapp.api.RetrofitClient;
import com.example.vodocanalmobileapp.models.LoginRequest;
import com.example.vodocanalmobileapp.models.LoginResponse;

public class LoginActivity extends AppCompatActivity {

    EditText loginInput;
    EditText passwordInput;

    Button loginButton;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginInput = findViewById(R.id.loginInput);
        passwordInput = findViewById(R.id.passwordInput);

        loginButton = findViewById(R.id.loginButton);
        regButton = findViewById(R.id.regButton);

        loginButton.setOnClickListener(v -> login());

        passwordInput.setOnTouchListener((v, event) -> {

            if(event.getAction() == android.view.MotionEvent.ACTION_UP){

                android.graphics.drawable.Drawable drawable =
                        passwordInput.getCompoundDrawables()[2];

                if(drawable != null){

                    if(event.getRawX() >=
                            (passwordInput.getRight() - drawable.getBounds().width())){

                        if(passwordInput.getTransformationMethod()
                                instanceof android.text.method.PasswordTransformationMethod){

                            passwordInput.setTransformationMethod(null);

                        }else{

                            passwordInput.setTransformationMethod(
                                    android.text.method.PasswordTransformationMethod.getInstance());

                        }

                        passwordInput.setSelection(passwordInput.getText().length());

                        return true;
                    }
                }
            }

            return false;
        });

        Button serverButton = findViewById(R.id.serverButton);

        serverButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(LoginActivity.this,
                            ServerSettingsActivity.class);

            startActivity(intent);

        });

        regButton.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });
    }

    private void login(){

        String email = loginInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginInput.setError("Введите корректный email");
            return;
        }

        if(password.length() < 4){
            passwordInput.setError("Пароль слишком короткий");
            return;
        }

        LoginRequest request = new LoginRequest(email,password);

        ApiService api = RetrofitClient
                .getInstance(this)
                .create(ApiService.class);

        Call<LoginResponse> call = api.login(request);

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call,
                                   Response<LoginResponse> response) {

                if(response.isSuccessful() && response.body()!=null){

                    LoginResponse res = response.body();

                    if(res.status.equals("success")){

                        SharedPreferences prefs =
                                getSharedPreferences("user",MODE_PRIVATE);

                        prefs.edit()
                                .putString("email",email)
                                .putString("name",res.name)
                                .apply();

                        Toast.makeText(LoginActivity.this,
                                "Добро пожаловать "+res.name,
                                Toast.LENGTH_LONG).show();

                        Intent intent =
                                new Intent(LoginActivity.this,MainActivity.class);

                        startActivity(intent);
                        finish();

                    }else{

                        Toast.makeText(LoginActivity.this,
                                "Неверный email или пароль",
                                Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this,
                        "Ошибка соединения с сервером",
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}
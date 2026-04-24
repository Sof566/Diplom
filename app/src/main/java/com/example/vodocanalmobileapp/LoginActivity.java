package com.example.vodocanalmobileapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vodocanalmobileapp.R;
import com.example.vodocanalmobileapp.api.database.AppDatabase;
import com.example.vodocanalmobileapp.entity.UserEntity;

public class LoginActivity extends AppCompatActivity {

    EditText etLogin, etPassword;
    Button btnLogin;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "tictocblyat"
        ).allowMainThreadQueries().build();

        btnLogin.setOnClickListener(v -> {

            try {

                String login = etLogin.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                UserEntity user = db.userDao().login(login, password);

                if (user != null) {
                    Toast.makeText(this, "Успешный вход", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });
    }
}
package com.example.vodocanalmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vodocanalmobileapp.R;
import com.example.vodocanalmobileapp.api.database.AppDatabase;
import com.example.vodocanalmobileapp.entity.UserEntity;

public class RegisterActivity extends AppCompatActivity {

    EditText etLogin, etPassword;
    Button btnRegister;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "tictocblyat"
        ).allowMainThreadQueries().build();

        btnRegister.setOnClickListener(v -> {

            UserEntity user = new UserEntity();
            user.login = etLogin.getText().toString().trim();
            user.password = etPassword.getText().toString().trim();

            db.userDao().insert(user);

            Toast.makeText(this, "Пользователь создан", Toast.LENGTH_SHORT).show();

            // 👉 переход на главный экран
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);

            // 👉 закрыть текущий экран (чтобы нельзя было вернуться кнопкой назад)
            finish();
        });
    }
}
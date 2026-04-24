package com.example.vodocanalmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vodocanalmobileapp.LoginActivity;
import com.example.vodocanalmobileapp.R;
import com.example.vodocanalmobileapp.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGoRegister, btnGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoRegister = findViewById(R.id.btnGoRegister);
        btnGoLogin = findViewById(R.id.btnGoLogin);

        btnGoRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnGoLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
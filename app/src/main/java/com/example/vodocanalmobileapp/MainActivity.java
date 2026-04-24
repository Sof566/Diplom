package com.example.vodocanalmobileapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView nameText;
    TextView accountText;
    TextView balanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        accountText = findViewById(R.id.accountText);
        balanceText = findViewById(R.id.balanceText);

        String name = getIntent().getStringExtra("name");
        String account = getIntent().getStringExtra("account");
        int balance = getIntent().getIntExtra("balance",0);

        nameText.setText("Имя: " + name);
        accountText.setText("Лицевой счет: " + account);
        balanceText.setText("Баланс: " + balance + " ₽");
    }
}
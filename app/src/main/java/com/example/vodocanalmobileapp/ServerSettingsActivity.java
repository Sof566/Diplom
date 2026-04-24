package com.example.vodocanalmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vodocanalmobileapp.api.ServerConfig;

public class ServerSettingsActivity extends AppCompatActivity {

    EditText ipInput;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_settings);

        ipInput = findViewById(R.id.ipInput);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {

            String ip = ipInput.getText().toString();

            ServerConfig.saveServerIp(this, ip);

            Toast.makeText(this,
                    "Сервер сохранен",
                    Toast.LENGTH_LONG).show();

            finish();
        });
    }
}
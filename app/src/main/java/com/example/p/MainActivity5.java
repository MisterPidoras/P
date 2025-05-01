package com.example.p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p.utils.UserManager;

public class MainActivity5 extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        userManager = new UserManager(this);
        etUsername = findViewById(R.id.emailEditText);
        etPassword = findViewById(R.id.passwordEditText);

        // Если пользователь уже авторизован, перенаправляем его
        if (userManager.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void onLoginClick(View v) {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userManager.loginUser(username, password)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Неверные данные", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRegisterClick(View v) {
        startActivity(new Intent(this, MainActivity3.class));
    }
}
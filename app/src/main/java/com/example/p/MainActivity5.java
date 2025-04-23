package com.example.p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onc(View v) {
        // Continue without login
        Intent f = new Intent(MainActivity5.this, MainActivity.class);
        startActivity(f);
    }

    public void onRegisterClick(View v) {
        // Go to registration screen
        Intent f = new Intent(MainActivity5.this, MainActivity3.class);
        startActivity(f);
    }

    public void onLoginClick(View v) {
        // Handle login logic here
        // After successful login:
        // Intent f = new Intent(LoginActivity.this, MainActivity.class);
        // startActivity(f);
    }
}
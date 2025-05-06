package com.example.p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import com.example.p.utils.UserManager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {
    private UserManager userManager;
    private static final String PREFS_NAME = "ThemePrefs";
    private static final String KEY_THEME = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Загрузка темы перед установкой содержимого
        loadTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EdgeToEdge.enable(this);
        userManager = new UserManager(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.backButton).setOnClickListener(v -> finish());

        RadioGroup themeRadioGroup = findViewById(R.id.themeRadioGroup);
        // Установка текущего выбранного переключателя
        String currentTheme = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getString(KEY_THEME, "light");
        themeRadioGroup.check(currentTheme.equals("dark") ? R.id.darkTheme : R.id.lightTheme);

        themeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.darkTheme) {
                saveTheme("dark");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                saveTheme("light");
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            recreate(); // Пересоздаем активность для мгновенного применения темы
        });


    }

    private void saveTheme(String theme) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_THEME, theme);
        editor.apply();
    }

    private void loadTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String theme = sharedPreferences.getString(KEY_THEME, "light");
        if ("dark".equals(theme)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    public void animm(View V){
        userManager.logout();
        Intent intent1 = new Intent(SettingsActivity.this, MainActivity2.class);
        startActivity(intent1);
    }
}
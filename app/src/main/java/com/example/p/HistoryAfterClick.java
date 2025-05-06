package com.example.p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.p.model.history;
import com.example.p.utils.UserManager;

public class HistoryAfterClick extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String currentUserKey;
    private int heartsCount = 3;
    private static final String HEARTS_COUNT_KEY = "hearts_count";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Полноэкранный режим с прозрачными барами
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        // Скрыть ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_history_after_click);

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // Получаем имя текущего пользователя
        UserManager userManager = new UserManager(this);
        String currentUsername = userManager.getCurrentUser();
        currentUserKey = "learnedWords_" + (currentUsername != null ? currentUsername : "default");

        // Получение данных из интента
        history item = (history) getIntent().getSerializableExtra("HISTORY_ITEM");
        if (item != null) {
            // Настройка элементов интерфейса
            TextView tvTitle = findViewById(R.id.tvTitle);
            TextView tvDifficulty = findViewById(R.id.tvDifficulty);
            ImageView imageView = findViewById(R.id.imageView);

            tvTitle.setText(item.getName());
            tvDifficulty.setText("Сложность: " + item.getHard());

            // Загрузка изображения
            int resId = getResources().getIdentifier(
                    item.getImg(),
                    "drawable",
                    getPackageName()
            );
            if (resId != 0) imageView.setImageResource(resId);
        }

        // Кнопка "Продолжить" - запускает с сохраненной позицией
        Button buttonContinue = findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(v -> {
            Intent intent = new Intent(HistoryAfterClick.this, MainActivity4.class);
            startActivity(intent);
        });

        // Кнопка "Начать" - обнуляет прогресс и запускает заново
        Button buttonStart = findViewById(R.id.button5);
        buttonStart.setOnClickListener(v -> {
            // Обнуляем сохраненные значения
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("saved_position", 0);
            editor.putInt("saved_answer", 0);
            heartsCount = 3;
            editor.putInt(HEARTS_COUNT_KEY + currentUserKey, heartsCount);
            editor.apply();

            // Запускаем активность с нулевой позицией
            Intent intent = new Intent(HistoryAfterClick.this, MainActivity4.class);
            intent.putExtra("saved_position", 0);
            intent.putExtra("saved_answer", 0);
            startActivity(intent);
        });

        // Скрытие системных баров при касании
        findViewById(android.R.id.content).setOnClickListener(v -> {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        });
    }
}
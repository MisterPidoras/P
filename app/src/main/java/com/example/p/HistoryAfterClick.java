package com.example.p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.p.model.history;

public class HistoryAfterClick extends AppCompatActivity {
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
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(v -> {
            Intent intent1 = new Intent(HistoryAfterClick.this, MainActivity4.class);
            startActivity(intent1);});

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
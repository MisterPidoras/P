package com.example.p;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.p.Adapter.vbAdapter;
import com.example.p.model.history;
import com.example.p.utils.UserManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private UserManager userManager;
    private String currentUserKey;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userManager = new UserManager(this);
        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        currentUserKey = "user_" + (userManager.getCurrentUser() != null ? userManager.getCurrentUser() : "default");

        // Инициализация первой истории
        if(!sharedPreferences.contains(currentUserKey + "_story_1_locked")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(currentUserKey + "_story_1_locked", false);
            editor.apply();
        }

        setupStories();
        setupButtons();
    }
    // Временный метод для сброса (удалить после тестирования)
    public void resetStories() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(currentUserKey + "_story_1_locked", false);
        editor.putBoolean(currentUserKey + "_story_2_locked", true);
        editor.apply();
    }
    private void setupStories() {
        List<history> stories = new ArrayList<>();

        // Для первой истории всегда разблокирована
        boolean isStory1Locked = sharedPreferences.getBoolean(currentUserKey + "_story_1_locked", false);

        // Для второй истории проверяем флаг
        boolean isStory2Locked = sharedPreferences.getBoolean(currentUserKey + "_story_2_locked", true);
        boolean isStory3Locked = sharedPreferences.getBoolean(currentUserKey + "_story_3_locked", true);
        boolean isStory4Locked = sharedPreferences.getBoolean(currentUserKey + "_story_4_locked", true);
        boolean isStory5Locked = sharedPreferences.getBoolean(currentUserKey + "_story_5_locked", true);
        boolean isStory6Locked = sharedPreferences.getBoolean(currentUserKey + "_story_6_locked", true);

        stories.add(new history(
                "Лесное приключение",
                "forest",
                "Вам предстоит прожить один день в лесу",
                "Сложно",
                1,
                isStory1Locked
        ));

        stories.add(new history(
                "Скоро",
                "young_adult_male",
                "Скоро Скоро",
                "Легко",
                2,
                isStory2Locked
        ));
        stories.add(new history(
                "Мир войны",
                "stone",
                "Скоро Скоро",
                "Легко",
                3,
                isStory3Locked
        ));
        stories.add(new history(
                "Мы будем петь наслаждение",
                "young_adult_male",
                "Не",
                "Невероятно",
                4,
                isStory4Locked
        ));
        stories.add(new history(
                "История обмана",
                "young_adult_male",
                "Скоро Скоро",
                "Легко",
                5,
                isStory5Locked
        ));
        stories.add(new history(
                "Моногатари",
                "young_adult_male",
                "Скоро Скоро",
                "Очень легко",
                6,
                isStory6Locked
        ));

        setupRecyclerView(stories);
    }

    private void setupRecyclerView(List<history> items) {
        RecyclerView recyclerView = findViewById(R.id.res);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new vbAdapter(items, this, item -> {
            if (item.isLocked()) {
                Toast.makeText(this, "Сначала подумай", Toast.LENGTH_SHORT).show();
            } else {
                openStoryDetails(item);
            }
        }));
    }

    private void openStoryDetails(history item) {
        Intent intent = new Intent(this, HistoryAfterClick.class);
        intent.putExtra("HISTORY_ITEM", item);
        startActivity(intent);
    }

    private void setupButtons() {
        findViewById(R.id.settingsButton).setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));

        findViewById(R.id.settingsButton2).setOnClickListener(v ->
                startActivity(new Intent(this, SaveWord.class)));
    }

    // Метод для разблокировки следующей истории


    @Override
    protected void onResume() {
        super.onResume();
        // Обновляем список при возвращении на экран
        setupStories();
    }
}
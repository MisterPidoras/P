package com.example.p;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.p.Adapter.worAd;
import com.example.p.model.word;
import com.example.p.utils.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SaveWord extends AppCompatActivity {
    private RecyclerView recyclerView;
    private worAd adapter;
    private String currentUserKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_word);
        UserManager userManager = new UserManager(this);
        String currentUsername = userManager.getCurrentUser();
        if (currentUsername == null) {
            // если пользователь не найден, можно выйти или показать пустой список
            finish();
            return;
        }
        currentUserKey = "learnedWords_" + currentUsername;

        recyclerView = findViewById(R.id.res);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadLearnedWords();

        findViewById(R.id.backButton).setOnClickListener(v -> finish());
        findViewById(R.id.clearButton).setOnClickListener(v -> clearLearnedWords());

    }

    private void loadLearnedWords() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        Set<String> words = sharedPreferences.getStringSet(currentUserKey, new HashSet<>());

        List<word> wordList = new ArrayList<>();
        int id = 0;
        for (String pair : words) {
            String[] parts = pair.split("\\|");
            if (parts.length == 2) {
                wordList.add(new word(id++, parts[0], parts[1])); // parts[0] - слово, parts[1] - перевод
            }
        }

        adapter = new worAd(wordList, this, null);
        recyclerView.setAdapter(adapter);
    }
    private void clearLearnedWords() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(currentUserKey);  // удаляем слова текущего пользователя
        editor.apply();

        // Обновляем список (очищаем адаптер)
        adapter.updateData(new ArrayList<>());
    }

}

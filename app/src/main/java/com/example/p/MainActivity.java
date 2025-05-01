package com.example.p;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.p.Adapter.vbAdapter;
import com.example.p.model.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<history> listvb = new ArrayList<>();
        listvb.add(new history("Haaaaawe 1", "young_adult_male", "Duncanue события 1", "Сложно", 1));
        listvb.add(new history("Haaaaawe 2", "rectangle", "Duncanue события 2", "Легко", 2));

        setupRecyclerView(listvb);
        findViewById(R.id.settingsButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.settingsButton2).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SaveWord.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView(List<history> items) {
        RecyclerView recyclerView = findViewById(R.id.res);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new vbAdapter(items, this, item -> {
            Intent intent = new Intent(MainActivity.this, HistoryAfterClick.class);
            intent.putExtra("HISTORY_ITEM", (Serializable) item); // Исправлено
            startActivity(intent);
        }));
    }
}
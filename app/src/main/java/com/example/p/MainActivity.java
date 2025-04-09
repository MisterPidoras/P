package com.example.p;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p.Adapter.vbAdapter;
import com.example.p.model.history;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<history> listvb = new ArrayList<>();
        // Use actual drawable resource names from your project
        listvb.add(new history("Name", "young_adult_male", "Description", "Hard", 1));
        listvb.add(new history("Name 2", "rectangle", "Description 2", "Easy", 2));

        setupRecyclerView(listvb);
    }

    private void setupRecyclerView(List<history> items) {
        RecyclerView recyclerView = findViewById(R.id.res);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new vbAdapter(items, this));
    }
}
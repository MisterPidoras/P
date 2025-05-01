package com.example.p;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.p.utils.UserManager;
import java.util.HashSet;
import java.util.Set;

public class MainActivity4 extends AppCompatActivity {

    private TextView textView;
    private ImageButton btnNext, btnBack;
    private String[] texts = {
            "Это первый текст",
            "Это второй текст",
            "Это третий текст",
            "Это третий текст",
            "Это четвертый текст",
            "Это пятый текст"
    };
    private int currentPosition = 0;
    private SharedPreferences sharedPreferences;
    private UserManager userManager;
    private String currentUserKey;

    // Перевод, заданный программистом
    private static final String TRANSLATION_SDF = "программистский_перевод";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        userManager = new UserManager(this);
        String currentUsername = userManager.getCurrentUser();
        if (currentUsername == null) {
            startActivity(new Intent(this, MainActivity5.class));
            finish();
            return;
        }
        currentUserKey = "learnedWords_" + currentUsername;

        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        Set<String> learnedWords = sharedPreferences.getStringSet(currentUserKey, new HashSet<>());

        textView = findViewById(R.id.textView);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        updateText();

        btnNext.setOnClickListener(v -> {
            if (currentPosition < texts.length - 1) {
                currentPosition++;
                updateText();
                check();
            }
        });

        btnBack.setOnClickListener(v -> {
            if (currentPosition > 0) {
                currentPosition--;
                updateText();
                check();
            }
        });
    }

    private void check() {
        ImageView imageView = findViewById(R.id.circle1);
        if (currentPosition == 2) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        if (currentPosition == 3) {
            imageView.setVisibility(View.VISIBLE);
            showCustomDialog();
        }
        if (currentPosition == 5) {
            changeBackGround(R.drawable.img_1);
        } else {
            changeBackGround(R.drawable.img);
        }
    }

    private void updateText() {
        String currentText = texts[currentPosition];
        textView.setText(currentText);
        btnBack.setEnabled(currentPosition > 0);
        btnNext.setEnabled(currentPosition < texts.length - 1);
    }

    void showCustomDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_answer, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .create();

        EditText inputWord = dialogView.findViewById(R.id.answer_input);
        TextView error = dialogView.findViewById(R.id.error_text);

        dialog.setOnShowListener(d -> {
            Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            okButton.setOnClickListener(v -> {
                String enteredWord = inputWord.getText().toString().trim();

                if (enteredWord.equals("sdf")) {
                    // Сохраняем слово и предопределенный перевод
                    saveLearnedWord(enteredWord, TRANSLATION_SDF);
                    dialog.dismiss();
                } else {
                    error.setVisibility(View.VISIBLE);
                    error.setText("Неверное слово");
                }
            });
        });
        dialog.show();
    }

    private void changeBackGround(int Image) {
        RelativeLayout BackGround = findViewById(R.id.BackG);
        BackGround.setBackgroundResource(Image);
    }

    private void saveLearnedWord(String word, String translation) {
        Set<String> learnedWords = sharedPreferences.getStringSet(currentUserKey, new HashSet<>());
        String wordPair = word + "|" + translation; // Формат: слово|перевод
        learnedWords.add(wordPair);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(currentUserKey, new HashSet<>(learnedWords));
        editor.apply();
    }
}
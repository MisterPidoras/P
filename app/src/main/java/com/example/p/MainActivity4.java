package com.example.p;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity4 extends AppCompatActivity {

    private TextView textView;
    private Button btnNext, btnBack;

    private String[] texts = {
            "Это первый текст",
            "Это второй текст",
            "Это третий текст",
            "Это четвертый текст",
            "Это пятый текст"
    };

    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textView = findViewById(R.id.textView);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        // Устанавливаем начальный текст
        updateText();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < texts.length - 1) {
                    currentPosition++;
                    updateText();
                    check();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > 0) {
                    currentPosition--;
                    updateText();
                    check();
                }
            }
        });
    }
    private void check(){
        ImageView imageView = findViewById(R.id.circle1);
        if (currentPosition==2) {
            imageView.setVisibility(View.VISIBLE);
            showCustomDialog();// Показываем
        } else {
            imageView.setVisibility(View.GONE); // Скрываем
        }
    }
    private void updateText() {
        textView.setText(texts[currentPosition]);

        // Обновляем состояние кнопок
        btnBack.setEnabled(currentPosition > 0);
        btnNext.setEnabled(currentPosition < texts.length - 1);
    }
    void showCustomDialog() {
        // Надуваем наш XML
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_answer, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView) // Подключаем наш XML
                .setPositiveButton("OK", null)
                .create();

        // Получаем элементы из НАШЕГО макета
        EditText input = dialogView.findViewById(R.id.answer_input);
        TextView error = dialogView.findViewById(R.id.error_text);

        // Обработка нажатия с проверкой
        dialog.setOnShowListener(d -> {
            Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            okButton.setOnClickListener(v -> {
                if (input.getText().toString().equals("42")) {
                    dialog.dismiss();
                } else {
                    error.setVisibility(View.VISIBLE);
                    error.setText("Неверно! Ответ: 42");
                }
            });
        });

        dialog.show();
    }
}
package com.example.p;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AnswerDialog extends Dialog {
    private EditText answerInput;
    private TextView errorText;
    private String correctAnswer;

    public AnswerDialog(@NonNull Context context, String correctAnswer) {
        super(context);
        this.correctAnswer = correctAnswer;
        setContentView(R.layout.dialog_answer);

    }



    private void checkAnswer() {
        String userAnswer = answerInput.getText().toString().trim();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            dismiss(); // Закрываем диалог при правильном ответе
        } else {
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("Неверный ответ, попробуйте еще раз");
        }
    }
}
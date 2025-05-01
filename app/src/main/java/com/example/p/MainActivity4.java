package com.example.p;
import android.app.AlertDialog;
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
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.text.InputType;
import android.view.WindowManager;
import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
public class MainActivity4 extends AppCompatActivity {

    private TextView textView;
    private ImageButton btnNext, btnBack;

    private String[] texts = {
            "Очнувшись передо мной был хвойный forest,через который шла тропинка.",
            "Осмотревшись я решил пройти в глубь по тропинке через forest.",
            "Спустя 10 минут я вышел на тропинку выложенную камнями.",
            "По ее бокам находились достаточно масивные stones",
            "Услышав шорох я решил подойти к одному из них,за ним был rabbit",
            "Но увидев меня он сразу начал убегать и вскоре испарился в глубине forest",
            "Насладившись видом данного forest я продолжил путь",
            "Пройдя около одного киллометра я вышел на village",
            "Данная village была небольшая,но выглядила словно из сказки",
            "Возле нее протекала river и на фоне заката это выглядило особо прекрасно",
            "По бокам тропинке к village росли trees с пышными кронами",
            "Осмотрев village из далека,я пошел к houses,чтобы попросится остаться на ночлег",
            "Встав перед одним из house и осмотрев его.Я постучал в door.",
            "Сразу после стуков,начали раздаваться отчетливые  шаги в house.",
            "После со скрипом отворилась door и передомной появился силуэт мужчины.",
            "Он был явно удивлен моему apperance.Не став ждать моих слов он сказал:'Что тебе надо?'",
            "Ответив,что я путник и мне нужен ночлег на одну night.Он впустил меня внутрь",
            "Оказавшись внутри перед моими глазами появился table с едой.Владелец предложил поесть после дороги.",
            "Я не стал отказыватсья и сел за table.И поблагодарив его за food начал есть",
            "После dinner владелец провел меня до комнаты,в которой я могу переночевать",
            "Войдя в нее можно было заметить,что не в ней давно никто не живет,но она была довольно чистой",
            "В ней в углу стояла bed,а возле нее тумбочка,на ней стояла лампа,также было окно с видом на реку",
            "В другом углу стоял достаточно большой closet,он был закрыт.",
            "Осмотрев room я сразу лег спать"
    };
    private String[] otveti={
            "лес","камни","заяц","деревню","река","деревья","домам","дверь","появлению","ночь","стол",
            "еду","ужина","кровать","шкаф","комнату"
    };
    private int currentPosition = 0;
    private int currentAnswer = 0;

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
        } else {
            imageView.setVisibility(View.GONE); // Скрываем
        }
        if (currentPosition == 3) {
            imageView.setVisibility(View.VISIBLE);
            showCustomDialog();
        }
        if (currentPosition == 5){
            changeBackGround(R.drawable.img_1);
        }
        else {
            changeBackGround(R.drawable.img);
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

        ImageView imageView = findViewById(R.id.circle1);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_answer, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView) // Подключаем наш XML
                .setPositiveButton("OK", null)
                .setNegativeButton("Отмена", null)
                .create();

        // Получаем элементы из макета
        EditText input = dialogView.findViewById(R.id.answer_input);
        TextView error = dialogView.findViewById(R.id.error_text);

        // Настройка ввода на русском языке
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        input.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Автоматическое открытие клавиатуры
        dialog.setOnShowListener(d -> {
            input.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);

            Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            okButton.setOnClickListener(v -> {
                String userInput = input.getText().toString().trim();

                if (userInput.equals(otveti[currentAnswer])) { // Замените на ваш правильный ответ
                    currentAnswer++;
                    imageView.setVisibility(View.GONE);
                    dialog.dismiss();
                } else {
                    error.setVisibility(View.VISIBLE);
                    error.setText("Неверно! Попробуйте ещё раз.");
                    // Анимация для привлечения внимания
                    error.animate()
                            .alpha(0.7f).setDuration(200)
                            .withEndAction(() -> error.animate().alpha(1f).setDuration(200))
                            .start();

                    // Вибрация при ошибке

                }
            });
        });

        // Обработка физической клавиатуры
        input.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
                return true;
            }
            return false;
        });

        // Настройка размера диалога
        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }
    private void changeBackGround(int Image){
        RelativeLayout BackGround = findViewById(R.id.BackG);
        BackGround.setBackgroundResource(Image);
    }
}
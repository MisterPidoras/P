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
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.content.SharedPreferences;
import android.content.Intent;

import com.example.p.utils.UserManager;

import java.util.HashSet;
import java.util.Set;

public class MainActivity4 extends AppCompatActivity {

    private TextView textView;
    private ImageButton btnNext, btnBack;
    private SharedPreferences sharedPreferences;
    private String currentUserKey;

    private String[] texts = {
            "Очнувшись передо мной был хвойный forest,через который шла тропинка.",
            "Очнувшись передо мной был хвойный forest,через который шла тропинка.",
            "Осмотревшись я решил пройти в глубь по тропинке через forest.",
            "Спустя 10 минут я вышел на тропинку выложенную камнями.",
            "По ее бокам находились достаточно масивные stones",
            "По ее бокам находились достаточно масивные stones",
            "Услышав шорох я решил подойти к одному из них,за ним был rabbit",
            "Услышав шорох я решил подойти к одному из них,за ним был rabbit",
            "Но увидев меня он сразу начал убегать и вскоре испарился в глубине forest",
            "Насладившись видом данного forest я продолжил путь",
            "Пройдя около одного киллометра я вышел на village",
            "Пройдя около одного киллометра я вышел на village",
            "Данная village была небольшая,но выглядила словно из сказки",
            "Возле нее протекала river и на фоне заката это выглядило особо прекрасно",
            "Возле нее протекала river и на фоне заката это выглядило особо прекрасно",
            "По бокам тропинке к village росли trees с пышными кронами",
            "По бокам тропинке к village росли trees с пышными кронами",
            "Осмотрев village из далека,я пошел к houses,чтобы попросится остаться на ночлег",
            "Осмотрев village из далека,я пошел к houses,чтобы попросится остаться на ночлег",
            "Встав перед одним из house и осмотрев его.Я постучал в door.",
            "Встав перед одним из house и осмотрев его.Я постучал в door.",
            "Сразу после стуков,начали раздаваться отчетливые  шаги в house.",
            "После со скрипом отворилась door и передомной появился силуэт мужчины.",
            "Он был явно удивлен моему apperance.Не став ждать моих слов он сказал:'Что тебе надо?'",
            "Он был явно удивлен моему apperance.Не став ждать моих слов он сказал:'Что тебе надо?'",
            "Ответив,что я путник и мне нужен ночлег на одну night.Он впустил меня внутрь",
            "Ответив,что я путник и мне нужен ночлег на одну night.Он впустил меня внутрь",
            "Оказавшись внутри перед моими глазами появился table с едой.Владелец предложил поесть после дороги.",
            "Оказавшись внутри перед моими глазами появился table с едой.Владелец предложил поесть после дороги.",
            "Я не стал отказыватсья и сел за table.И поблагодарив его за food начал есть",
            "Я не стал отказыватсья и сел за table.И поблагодарив его за food начал есть",
            "После dinner владелец провел меня до комнаты,в которой я могу переночевать",
            "После dinner владелец провел меня до комнаты,в которой я могу переночевать",
            "Войдя в нее можно было заметить,что не в ней давно никто не живет,но она была довольно чистой",
            "В ней в углу стояла bed,а возле нее тумбочка,на ней стояла лампа,также было окно с видом на river",
            "В ней в углу стояла bed,а возле нее тумбочка,на ней стояла лампа,также было окно с видом на river",
            "В другом углу стоял достаточно большой closet,он был закрыт.",
            "В другом углу стоял достаточно большой closet,он был закрыт.",
            "Осмотрев room я сразу лег спать",
            "Осмотрев room я сразу лег спать"
    };

    private String[] otveti = {
            "лес","камни","заяц","деревню","река","деревья","домам","дверь","появлению","ночь","стол",
            "еду","ужина","кровать","шкаф","комнату"
    };

    private String[] englishWords = {
            "forest","stones","rabbit","village","river","trees","houses","door","apperance","night","table",
            "food","dinner","bed","closet","room"
    };

    private int currentPosition = 0;
    private int currentAnswer = 0;
    private static final String SAVED_POSITION_KEY = "saved_position";
    private static final String SAVED_ANSWER_KEY = "saved_answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textView = findViewById(R.id.textView);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // Получаем имя текущего пользователя
        UserManager userManager = new UserManager(this);
        String currentUsername = userManager.getCurrentUser();
        currentUserKey = "learnedWords_" + (currentUsername != null ? currentUsername : "default");

        // Проверяем, есть ли сохраненная позиция
        if (getIntent().hasExtra(SAVED_POSITION_KEY)) {
            currentPosition = getIntent().getIntExtra(SAVED_POSITION_KEY, 0);
            currentAnswer = getIntent().getIntExtra(SAVED_ANSWER_KEY, 0);
        } else {
            // Загружаем последнюю позицию из SharedPreferences
            currentPosition = sharedPreferences.getInt(SAVED_POSITION_KEY, 0);
            currentAnswer = sharedPreferences.getInt(SAVED_ANSWER_KEY, 0);
        }

        // Устанавливаем начальный текст
        updateText();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < texts.length - 1) {
                    currentPosition++;
                    updateText();
                    check();
                    saveCurrentPosition();
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
                    saveCurrentPosition();
                }
            }
        });
    }

    private void saveCurrentPosition() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SAVED_POSITION_KEY, currentPosition);
        editor.putInt(SAVED_ANSWER_KEY, currentAnswer);
        editor.apply();
    }

    // Метод для создания Intent с текущей позицией
    public static Intent createIntent(Context context, int currentPosition, int currentAnswer) {
        Intent intent = new Intent(context, MainActivity4.class);
        intent.putExtra(SAVED_POSITION_KEY, currentPosition);
        intent.putExtra(SAVED_ANSWER_KEY, currentAnswer);
        return intent;
    }

    private void check(){
        RelativeLayout backGround = findViewById(R.id.BackG);
        if (currentPosition==1 || currentPosition==5 || currentPosition==7|| currentPosition==11 || currentPosition==14
                || currentPosition==16 || currentPosition==18 || currentPosition==20 || currentPosition==24 || currentPosition==26 || currentPosition==28
                || currentPosition==30 || currentPosition==32 || currentPosition==35 || currentPosition==37 || currentPosition==39){
            showCustomDialog();
        }
        if ((currentPosition==3) || (currentPosition>7 && currentPosition<=9)){
            changeBackGround(R.drawable.tropinkakamni,backGround);
        }
        if (currentPosition==4){
            changeBackGround(R.drawable.stoneszoom,backGround);
        }
        if (currentPosition>=6 && currentPosition<10){
            changeBackGround(R.drawable.rabbit,backGround);
        }
        if (currentPosition>=10 && currentPosition<17){
            changeBackGround(R.drawable.village,backGround);
        }
        if (currentPosition>=17 && currentPosition<22){
            changeBackGround(R.drawable.house,backGround);
        }
        if (currentPosition>=22 && currentPosition<27){
            changeBackGround(R.drawable.chelovekidom,backGround);
        }
        if (currentPosition>=27 && currentPosition<31){
            changeBackGround(R.drawable.food,backGround);
        }
        if (currentPosition==31){
            changeBackGround(R.drawable.room,backGround);
        }
    }

    private void updateText() {
        textView.setText(texts[currentPosition]);
        btnBack.setEnabled(currentPosition > 0);
        btnNext.setEnabled(currentPosition < texts.length - 1);
    }

    void showCustomDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_answer, null);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .setNegativeButton("Отмена", null)
                .create();

        EditText input = dialogView.findViewById(R.id.answer_input);
        TextView error = dialogView.findViewById(R.id.error_text);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        input.setImeOptions(EditorInfo.IME_ACTION_DONE);

        dialog.setOnShowListener(d -> {
            input.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);

            Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            okButton.setOnClickListener(v -> {
                String userInput = input.getText().toString().trim().toLowerCase();

                if (userInput.equals(otveti[currentAnswer])) {
                    // Сохраняем слово и перевод
                    saveLearnedWord(englishWords[currentAnswer], otveti[currentAnswer]);

                    currentAnswer++;
                    saveCurrentPosition();
                    dialog.dismiss();
                } else {
                    error.setVisibility(View.VISIBLE);
                    error.setText("Неверно! Попробуйте ещё раз.");
                    error.animate()
                            .alpha(0.7f).setDuration(200)
                            .withEndAction(() -> error.animate().alpha(1f).setDuration(200))
                            .start();
                }
            });
        });

        input.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
                return true;
            }
            return false;
        });

        dialog.show();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    private void saveLearnedWord(String englishWord, String russianTranslation) {
        Set<String> words = sharedPreferences.getStringSet(currentUserKey, new HashSet<>());
        Set<String> newWords = new HashSet<>(words);
        newWords.add(englishWord + "|" + russianTranslation);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(currentUserKey, newWords);
        editor.apply();
    }

    private void changeBackGround(int Image, View targetView) {
        Drawable currentDrawable = targetView.getBackground();
        Drawable newDrawable = targetView.getContext().getResources().getDrawable(Image);

        if (currentDrawable == null) {
            targetView.setBackground(newDrawable);
            return;
        }

        Drawable[] layers = new Drawable[] {
                currentDrawable,
                newDrawable
        };

        TransitionDrawable transition = new TransitionDrawable(layers);
        targetView.setBackground(transition);
        transition.startTransition(500);
    }
}
package com.example.p;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p.utils.UserManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private Map<String, Set<String>> synonyms = new HashMap<String, Set<String>>() {{
        put("лес", new HashSet<>(Arrays.asList("лес", "бор", "чаща", "лесок")));
        put("камни", new HashSet<>(Arrays.asList("камни", "валуны", "булыжники")));
        put("заяц", new HashSet<>(Arrays.asList("заяц", "кролик", "зайчик")));
        put("деревню", new HashSet<>(Arrays.asList("деревню", "село", "поселок")));
        put("река", new HashSet<>(Arrays.asList("река", "речка", "поток")));
        put("деревья", new HashSet<>(Arrays.asList("деревья", "растения", "стволы")));
        put("домам", new HashSet<>(Arrays.asList("домам", "жилищам", "строениям")));
        put("дверь", new HashSet<>(Arrays.asList("дверь", "вход", "проем")));
        put("появлению", new HashSet<>(Arrays.asList("появлению", "возникновению", "появлению")));
        put("ночь", new HashSet<>(Arrays.asList("ночь", "ночное время", "тьма")));
        put("стол", new HashSet<>(Arrays.asList("стол", "столик", "трапеза")));
        put("еду", new HashSet<>(Arrays.asList("еду", "пищу", "продукты")));
        put("ужина", new HashSet<>(Arrays.asList("ужина", "вечерней трапезы", "еды")));
        put("кровать", new HashSet<>(Arrays.asList("кровать", "постель", "ложе")));
        put("шкаф", new HashSet<>(Arrays.asList("шкаф", "гардероб", "кладовка")));
        put("комнату", new HashSet<>(Arrays.asList("комнату", "помещение", "залу")));
    }};
    private int currentPosition = 0;
    private int currentAnswer = 0;
    private int heartsCount = 3;
    private static final String SAVED_POSITION_KEY = "saved_position";
    private static final String SAVED_ANSWER_KEY = "saved_answer";
    private static final String HEARTS_COUNT_KEY = "hearts_count";
    private static final String UNLOCKED_STORIES_KEY = "unlocked_stories";

    private ImageView[] heartViews = new ImageView[3];
    private TextView heartsInfoText;
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
        initHearts();
        heartsCount = sharedPreferences.getInt(HEARTS_COUNT_KEY + currentUserKey, 3);
        updateHeartsDisplay();
        // Устанавливаем начальный текст
        updateText();
        check();
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
    public Intent createIntent(Context context, int currentPosition, int currentAnswer) {
        Intent intent = new Intent(context, MainActivity4.class);
        intent.putExtra(SAVED_POSITION_KEY, currentPosition);
        intent.putExtra(SAVED_ANSWER_KEY, currentAnswer);
        return intent;
    }

    private void check(){
        RelativeLayout backGround = findViewById(R.id.BackG);
        if ((currentPosition==1 && currentAnswer==0) || (currentPosition==5 && currentAnswer==1)|| (currentPosition==7&& currentAnswer==2)|| (currentPosition==11 && currentAnswer==3) || (currentPosition==14 && currentAnswer==4)
                || (currentPosition==16 && currentAnswer==5) || (currentPosition==18 && currentAnswer==6) || (currentPosition==20 && currentAnswer==7)|| (currentPosition==24 && currentAnswer==8) || (currentPosition==26 && currentAnswer==9)|| (currentPosition==28 && currentAnswer==10)
                || (currentPosition==30 && currentAnswer==11) || (currentPosition==32 && currentAnswer==12) || (currentPosition==35 && currentAnswer==13) || (currentPosition==37 && currentAnswer==14)|| (currentPosition==39 && currentAnswer==15)){
            showCustomDialog();
        }

        if ((currentPosition==3) || (currentPosition>7 && currentPosition<=9)){
            changeBackGround(R.drawable.tropinkakamni,backGround);
        }
        if (currentPosition==4){
            changeBackGround(R.drawable.stoneszoom,backGround);
        }
        if (currentPosition==6){
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
        if (currentPosition>=31){
            changeBackGround(R.drawable.room,backGround);

        }
        if (currentPosition == texts.length - 1) {
            boolean success = heartsCount > 0;
            onStoryCompleted(success);

            if(!success) {
                Toast.makeText(this, "История перезапущена", Toast.LENGTH_SHORT).show();
            }
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
                .create();

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (Message) null);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Отмена", (Message) null);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setDimAmount(0.7f);

        }

        EditText input = dialogView.findViewById(R.id.answer_input);
        TextView error = dialogView.findViewById(R.id.error_text);

        dialog.setOnShowListener(d -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);

            Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            okButton.setOnClickListener(v -> {
                String userInput = input.getText().toString().trim().toLowerCase();

                if (isAnswerCorrect(userInput)) {
                    handleCorrectAnswer(dialog);
                } else {
                    decreaseHearts();
                    error.setVisibility(View.VISIBLE);
                    error.setText("Неверно! Попробуйте ещё раз.");

                    if (heartsCount <= 0) {
                        dialog.dismiss();
                        onStoryCompleted(false);
                    }
                }
            });

            Button cancelButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            cancelButton.setVisibility(View.GONE);
        });

        // Блокируем навигацию
        btnBack.setEnabled(false);
        btnNext.setEnabled(false);



        dialog.setOnDismissListener(d -> {
            // Если диалог закрылся, но ответ не был дан (не через handleCorrectAnswer)
            if (currentAnswer == getOriginalAnswerForPosition(currentPosition)) {
                // Откатываем позицию назад
                if (currentPosition > 0) {
                    currentPosition--;
                    updateText();
                    saveCurrentPosition();
                }
            }

            btnBack.setEnabled(currentPosition > 0);
            btnNext.setEnabled(currentPosition < texts.length - 1);
        });

        dialog.show();
    }private int getOriginalAnswerForPosition(int position) {
        if (position == 1) return 0;
        if (position == 5) return 1;
        if (position == 7) return 2;
        if (position == 11) return 3;
        if (position == 14) return 4;
        if (position == 16) return 5;
        if (position == 18) return 6;
        if (position == 20) return 7;
        if (position == 24) return 8;
        if (position == 26) return 9;
        if (position == 28) return 10;
        if (position == 30) return 11;
        if (position == 32) return 12;
        if (position == 35) return 13;
        if (position == 37) return 14;
        if (position == 39) return 15;
        return -1;
    }
    private void handleCorrectAnswer(AlertDialog dialog) {
        saveLearnedWord(englishWords[currentAnswer], otveti[currentAnswer]);
        currentAnswer++;
        saveCurrentPosition();
        dialog.dismiss();

        // Обновляем позицию только здесь
        if (currentPosition < texts.length - 1) {
            currentPosition++;
            updateText();
            check();
            saveCurrentPosition();
        }}


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
    private void initHearts() {
        LinearLayout heartsContainer = findViewById(R.id.heartsContainer);
        heartsInfoText = findViewById(R.id.heartsInfoText);

        // Если контейнер не найден в layout, нужно добавить его в XML
        if (heartsContainer == null) {
            // Создаем контейнер программно, если он не был добавлен в XML
            heartsContainer = new LinearLayout(this);
            heartsContainer.setId(R.id.heartsContainer);
            heartsContainer.setOrientation(LinearLayout.HORIZONTAL);
            heartsContainer.setGravity(Gravity.CENTER);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.topMargin = 16;

            ((RelativeLayout) findViewById(R.id.BackG)).addView(heartsContainer, params);
        }// Очищаем контейнер перед добавлением сердечек
        heartsContainer.removeAllViews();

        // Создаем ImageView для каждого сердца
        for (int i = 0; i < 3; i++) {
            ImageView heart = new ImageView(this);
            heart.setLayoutParams(new LinearLayout.LayoutParams(
                    dpToPx(24),
                    dpToPx(24)
            ));
            heart.setImageResource(R.drawable.ic_heart);
            heart.setTag(i);
            heartsContainer.addView(heart);
            heartViews[i] = heart;
        }
    }
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
    private boolean isAnswerCorrect(String userInput) {
        // Проверяем точный ответ
        if (userInput.equals(otveti[currentAnswer])) {
            return true;
        }

        // Проверяем синонимы
        Set<String> possibleAnswers = synonyms.get(otveti[currentAnswer]);
        return possibleAnswers != null && possibleAnswers.contains(userInput);
    }

    private void updateHeartsDisplay() {
        for (int i = 0; i < heartViews.length; i++) {
            heartViews[i].setVisibility(i < heartsCount ? View.VISIBLE : View.GONE);
        }

        if (heartsCount <= 0) {
            heartsInfoText.setVisibility(View.VISIBLE);
            heartsInfoText.setText("Вы в режиме просмотра. Чтобы открыть следующую историю, пройдите текущую, имея сердца.");

            // Автоматически скрываем сообщение через 3 секунды
            heartsInfoText.postDelayed(() -> {
                if (heartsInfoText != null) {
                    heartsInfoText.setVisibility(View.GONE);
                }
            }, 3000);

            btnNext.setEnabled(false);
            btnBack.setEnabled(false);
        } else {
            heartsInfoText.setVisibility(View.GONE);
        }
    }

    private void decreaseHearts() {
        if (heartsCount > 0) {
            heartsCount--;
            sharedPreferences.edit().putInt(HEARTS_COUNT_KEY + currentUserKey, heartsCount).apply();
            updateHeartsDisplay();

            if (heartsCount == 0) {
                // Блокируем следующие истории
                Set<String> unlockedStories = sharedPreferences.getStringSet(UNLOCKED_STORIES_KEY + currentUserKey, new HashSet<>());
                unlockedStories.add("1"); // Только первая история разблокирована
                sharedPreferences.edit().putStringSet(UNLOCKED_STORIES_KEY + currentUserKey, unlockedStories).apply();
            }
        }
    }
    private void unlockNextStory() {
        // Используем тот же формат ключа, что и в MainActivity
        String currentUserKey = "user_" + (new UserManager(this).getCurrentUser() != null ?
                new UserManager(this).getCurrentUser() : "default");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Разблокируем историю с ID 2
        editor.putBoolean(currentUserKey + "_story_2_locked", false);
        editor.apply();
    }

    // Вызывайте этот метод при успешном прохождении истории
    private void onStoryCompleted(boolean success) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Всегда сбрасываем прогресс и сердца
        heartsCount = 3;
        currentPosition = 0;
        currentAnswer = 0;

        editor.putInt(HEARTS_COUNT_KEY + currentUserKey, heartsCount)
                .putInt(SAVED_POSITION_KEY, currentPosition)
                .putInt(SAVED_ANSWER_KEY, currentAnswer);

        // Разблокируем следующую историю только при успешном завершении
        if(success) {
            unlockNextStory();
            // Обновляем MainActivity
            sendBroadcast(new Intent("UPDATE_STORIES"));
            Toast.makeText(this, "Следующая история разблокирована!", Toast.LENGTH_SHORT).show();
        }

        editor.apply();

        // Перезагружаем активность
        Intent intent = new Intent(this, MainActivity4.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
            WindowInsetsController controller = getWindow().getInsetsController();
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}

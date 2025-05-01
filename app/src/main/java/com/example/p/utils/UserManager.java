package com.example.p.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class UserManager {
    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_USERS = "registered_users";
    private static final String KEY_CURRENT_USER = "current_user";

    private final SharedPreferences sharedPreferences;

    public UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Регистрация нового пользователя
    public boolean registerUser(String username, String password) {
        Set<String> users = getRegisteredUsers();
        if (users.contains(username)) {
            return false; // Пользователь уже существует
        }

        users.add(username);
        sharedPreferences.edit()
                .putStringSet(KEY_USERS, users)
                .putString(username + "_pass", password) // Сохраняем пароль
                .apply();
        return true;
    }

    // Авторизация пользователя
    public boolean loginUser(String username, String password) {
        String savedPassword = sharedPreferences.getString(username + "_pass", null);
        if (savedPassword != null && savedPassword.equals(password)) {
            sharedPreferences.edit().putString(KEY_CURRENT_USER, username).apply();
            return true;
        }
        return false;
    }

    // Получение текущего пользователя
    public String getCurrentUser() {
        return sharedPreferences.getString(KEY_CURRENT_USER, null);
    }

    // Выход пользователя
    public void logout() {
        sharedPreferences.edit().remove(KEY_CURRENT_USER).apply();
    }

    // Получение списка зарегистрированных пользователей
    private Set<String> getRegisteredUsers() {
        return sharedPreferences.getStringSet(KEY_USERS, new HashSet<>());
    }
}
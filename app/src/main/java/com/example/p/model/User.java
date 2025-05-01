package com.example.p.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private Set<String> learnedWords;

    public User(String username) {
        this.username = username;
        this.learnedWords = new HashSet<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLearnedWords(Set<String> learnedWords) {
        this.learnedWords = learnedWords;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getLearnedWords() {
        return learnedWords;
    }
}
package com.example.p.model;

public class word {
    int id;
    String wordy;
    String trans;

    public int getId() {
        return id;
    }

    public word(int id, String wordy, String trans) {
        this.id = id;
        this.wordy = wordy;
        this.trans = trans;
    }

    public String getWordy() {
        return wordy;
    }

    public String getTrans() {
        return trans;
    }
}

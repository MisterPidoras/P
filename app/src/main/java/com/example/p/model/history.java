package com.example.p.model;


import java.io.Serializable;

public class history implements Serializable { // Добавлен интерфейс
    private final String name;
    private final String img;
    private final String Desc;
    private final String hard;
    private final int id;

    public history(String name, String img, String desc, String hard, int id) {
        this.name = name;
        this.img = img;
        this.Desc = desc;
        this.hard = hard;
        this.id = id;
    }

    // Геттеры
    public String getName() { return name; }
    public String getImg() { return img; }
    public String getDesc() { return Desc; }
    public String getHard() { return hard; }
    public int getId() { return id; }
}
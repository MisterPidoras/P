package com.example.p.model;

public class history {
    private final String name;
    private final String img; // Should match drawable resource names
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

    // Getters
    public String getName() { return name; }
    public String getImg() { return img; }
    public String getDesc() { return Desc; }
    public String getHard() { return hard; }
    public int getId() { return id; }
}
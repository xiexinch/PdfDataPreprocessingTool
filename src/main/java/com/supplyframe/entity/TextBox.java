package com.supplyframe.entity;

public class TextBox {
    private String words;
    private Location location;

    public TextBox(String words, Location location) {
        this.words = words;
        this.location = location;
    }

    public TextBox() {
    }

    public TextBox(String words, int top, int left, int width, int height) {
        this.words = words;
        this.location = new Location(top, left, width, height);
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

package com.traderg.cli.backend_models;

import java.util.Map;

import com.google.gson.Gson;

public class Card {
    private int id;
    private String type;
    private String size;

    public Card(int id, String type, String size) {
        this.id = id;
        this.type = type;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static Card fromJson(Map<String, Object> json) {
        final Gson gson = new Gson();
        return gson.fromJson(gson.toJson(json), Card.class);
    }
    
    @Override
    public String toString() {
        return type + " (" + size + ")";
    }
}

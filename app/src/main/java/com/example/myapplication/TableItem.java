package com.example.myapplication;

import java.io.Serializable;

public class TableItem implements Serializable {
    private String name;
    private String status;
    private String color;
    private int image;
    private  int chair_number;

    public TableItem(String name, String status, int chair_number) {
        this.name = name;
        this.status = status;
        this.image = R.drawable.table_icon;
        this.color = "#4EC33A";
        this.chair_number= chair_number;
    }

    public int getChair_number() {
        return chair_number;
    }

    public void setChair_number(int chair_number) {
        this.chair_number = chair_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

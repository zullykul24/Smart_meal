package com.example.myapplication;

import java.io.Serializable;

public class MenuFoodItem implements Serializable {
        private int dish_id;
        private String dish_name;
        private int group_id;
        private  int price;
        private byte[] image;

    public MenuFoodItem(int dish_id, String dish_name, int group_id, int price, byte[] image) {
        this.dish_id = dish_id;
        this.dish_name = dish_name;
        this.group_id = group_id;
        this.price = price;
        this.image = image;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

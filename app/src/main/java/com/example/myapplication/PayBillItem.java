package com.example.myapplication;

public class PayBillItem {
    private int STT;
    private String dishName;
    private int SL;
    private String priceEach;
    private String priceTotal;

    public PayBillItem(int STT, String dishName, int SL, String priceEach) {
        this.STT = STT;
        this.dishName = dishName;
        this.SL = SL;
        this.priceEach = priceEach;
        this.priceTotal = String.valueOf(SL * Integer.parseInt(priceEach));
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public String getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(String priceEach) {
        this.priceEach = priceEach;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }
}

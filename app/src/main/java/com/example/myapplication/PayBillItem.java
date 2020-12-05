package com.example.myapplication;

public class PayBillItem {
    private int STT;
    private String dishName;
    private int SL;
    private double priceEach;
    private double priceTota;

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

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }

    public double getPriceTota() {
        return priceTota;
    }

    public void setPriceTota(double priceTota) {
        this.priceTota = priceTota;
    }

    public PayBillItem(String dishName, int SL, double priceEach) {
        this.dishName = dishName;
        this.SL = SL;
        this.priceEach = priceEach;
        this.priceTota = this.SL * priceEach;
    }


}

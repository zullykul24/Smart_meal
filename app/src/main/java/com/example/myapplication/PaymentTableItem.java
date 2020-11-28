package com.example.myapplication;

public class PaymentTableItem {
    private String tableName;

    public PaymentTableItem(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

package com.example.spring_7_11.Model;

public class Payment {
    private String id;
    private double amount;

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public double getAmount() {
        return amount;
    }
}

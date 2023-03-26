package com.example.currencyconversionapplication.model;

import lombok.Data;

@Data
public class Conversion {
    private String intermediaryCurrency;
    private double convertedAmount;
    private double profit;

    public Conversion(String intermediaryCurrency, double convertedAmount, double profit) {
        this.intermediaryCurrency = intermediaryCurrency;
        this.convertedAmount = convertedAmount;
        this.profit = profit;
    }
}




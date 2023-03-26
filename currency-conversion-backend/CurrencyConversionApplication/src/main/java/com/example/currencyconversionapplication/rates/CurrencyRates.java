package com.example.currencyconversionapplication.rates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CurrencyRates {

    @JsonProperty("base_currency")
    private String baseCurrency;

    @JsonProperty("rates")
    private Map<String, Double> rates;

    public CurrencyRates() {
        this.baseCurrency = "";
        this.rates = new HashMap<>();
    }
}






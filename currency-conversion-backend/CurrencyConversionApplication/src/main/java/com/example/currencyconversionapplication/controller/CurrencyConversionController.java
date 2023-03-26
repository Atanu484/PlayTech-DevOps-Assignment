package com.example.currencyconversionapplication.controller;


import com.example.currencyconversionapplication.integration.ConversionResult;
import com.example.currencyconversionapplication.services.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService conversionService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/convert")
    public ConversionResult convert(@RequestParam double amount, @RequestParam String fromCurrency, @RequestParam String toCurrency) {
        return conversionService.getBestConversion(amount, fromCurrency, toCurrency);
    }
}


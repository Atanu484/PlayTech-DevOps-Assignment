package com.example.currencyconversionapplication.services;

import java.util.*;

import com.example.currencyconversionapplication.integration.ConversionResult;
import com.example.currencyconversionapplication.rates.CurrencyRates;
import com.example.currencyconversionapplication.integration.CurrencyRatesApi;
import com.example.currencyconversionapplication.model.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    private static final List<String> CURRENCIES = Arrays.asList("EUR", "GBP", "USD", "JPY", "CHF", "CAD", "AUD", "NZD", "CNY", "INR");

    @Autowired
    private CurrencyRatesApi currencyRatesApi;

    public ConversionResult getBestConversion(double amount, String fromCurrency, String toCurrency) {
        CurrencyRates fromRates = currencyRatesApi.getRates(fromCurrency);
        CurrencyRates toRates = currencyRatesApi.getRates(toCurrency);

        List<Conversion> conversions = new ArrayList<>();
        Conversion mostProfitableForClient = null;
        Conversion mostProfitableForService = null;

        for (String intermediaryCurrency : CURRENCIES) {
            if (intermediaryCurrency.equals(fromCurrency) || intermediaryCurrency.equals(toCurrency)) {
                continue;
            }

            Double fromToIntermediary = fromRates.getRates().get(intermediaryCurrency);
            Double intermediaryToTo = toRates.getRates().get(intermediaryCurrency);

            if (fromToIntermediary == null || intermediaryToTo == null) {
                continue;
            }

            double convertedAmount = amount * fromToIntermediary * intermediaryToTo;
            double profit = Math.abs(amount - convertedAmount);

            Conversion conversion = new Conversion(intermediaryCurrency, convertedAmount, profit);
            conversions.add(conversion);

            if (mostProfitableForClient == null || mostProfitableForClient.getConvertedAmount() < conversion.getConvertedAmount()) {
                mostProfitableForClient = conversion;
            }

            if (mostProfitableForService == null || mostProfitableForService.getProfit() < conversion.getProfit()) {
                mostProfitableForService = conversion;
            }
        }

        return new ConversionResult(conversions, mostProfitableForClient, mostProfitableForService);
    }
}







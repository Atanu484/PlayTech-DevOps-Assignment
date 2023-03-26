// CurrencyRatesApi.java
package com.example.currencyconversionapplication.integration;

import com.example.currencyconversionapplication.rates.CurrencyRates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyRatesApi {

    private static final String API_KEY = "SX3GtQThYlGYEz3RJd09ASZ9vj4ZGt26IfWS0S3g";
    private static final String BASE_URL = "https://api.freecurrencyapi.com/v1/latest?apikey=" + API_KEY;
    private final Logger logger = LoggerFactory.getLogger(CurrencyRatesApi.class);

    public CurrencyRates getRates(String baseCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "&base_currency=" + baseCurrency;

        try {
            return restTemplate.getForObject(url, CurrencyRates.class);
        } catch (HttpClientErrorException e) {
            logger.error("Error fetching currency rates: {}", e.getMessage());
            return null;
        }
    }
}


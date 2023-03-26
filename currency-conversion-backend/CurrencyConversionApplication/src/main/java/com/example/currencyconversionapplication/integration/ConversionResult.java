package com.example.currencyconversionapplication.integration;



import com.example.currencyconversionapplication.model.Conversion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ConversionResult {

    private List<Conversion> conversions;
    private Conversion mostProfitableForClient;
    private Conversion mostProfitableForService;

}


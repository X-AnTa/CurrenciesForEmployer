package com.anta.currency_for_employer.controller;

import com.anta.currency_for_employer.service.CurrencyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RESTController {

    private final CurrencyServiceImpl currencyService;

    public RESTController(CurrencyServiceImpl currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping("/admin/update")
    public ResponseEntity<Boolean> updateCurrencies(){
        return new ResponseEntity<>(currencyService.updateCurrencies(), HttpStatus.OK);
    }
}

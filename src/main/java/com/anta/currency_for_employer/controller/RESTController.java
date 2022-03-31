package com.anta.currency_for_employer.controller;

import com.anta.currency_for_employer.entity.Currency;
import com.anta.currency_for_employer.service.CurrencyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class RESTController {

    private final CurrencyServiceImpl currencyService;

    public RESTController(CurrencyServiceImpl currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping("/admin/update")
    public ResponseEntity<Boolean> updateCurrencies() {
        return new ResponseEntity<>(currencyService.updateCurrencies(), HttpStatus.OK);
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return new ResponseEntity<>(currencyService.getAllCurrencies(), HttpStatus.OK);
    }

    @GetMapping("/currency/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable int id){
        return new ResponseEntity<>(currencyService.getCurrency(id),HttpStatus.OK);
    }
}

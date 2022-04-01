package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.Currency;

import java.util.List;

public interface CurrencyService {

    Boolean updateCurrencies();

    List<Currency> getAllCurrencies(int pageNo);

    Currency getCurrency(int id);
}

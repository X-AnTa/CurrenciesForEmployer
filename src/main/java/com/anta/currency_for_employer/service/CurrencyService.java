package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.Currency;

import java.util.List;

public interface CurrencyService {

    /**
     * Add or Update list currencies
     * @return true or false
     */
    Boolean updateCurrencies();

    /**
     * Find all currencies
     * @param pageNo page number
     * @param pageSize elements on the page
     * @return List currencies
     */
    List<Currency> getAllCurrencies(int pageNo, int pageSize);

    /**
     * Find currency by id
     * @param id currency id
     * @return currency by id
     */
    Currency getCurrency(int id);
}

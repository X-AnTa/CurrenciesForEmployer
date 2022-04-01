package com.anta.currency_for_employer.repository;

import com.anta.currency_for_employer.entity.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Integer> {

    Currency findByName(String name);
}

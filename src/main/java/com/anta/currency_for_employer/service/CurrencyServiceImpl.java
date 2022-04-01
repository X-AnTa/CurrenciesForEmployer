package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.Currency;
import com.anta.currency_for_employer.exception.NoSuchCurrencyException;
import com.anta.currency_for_employer.repository.CurrencyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private final static String url = "http://www.cbr.ru/scripts/XML_daily.asp";

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    @Override
    public Boolean updateCurrencies() {
        try {
            Currency currency = new Currency();
            Document page = Jsoup.connect(url).get();

            for (int i = 0; i < 34; i++) {
                Element name = page.select("Name").get(i);
                Element value = page.select("Value").get(i);
                Element nominal = page.select("Nominal").get(i);
                Element charCode = page.select("CharCode").get(i);
                currency.setName(String.valueOf(name.textNodes().get(0)));
                currency.setRate(nominal.textNodes().get(0) + " " + charCode.textNodes().get(0)+ " к "+ value.textNodes().get(0) +" RUB");
                currency.setId(i + 1);
                currencyRepository.save(currency);
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Currency> getAllCurrencies(int pageNo) {
        int pageSize = 10;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").ascending());
        Page<Currency> currencyPage = currencyRepository.findAll(paging);
        return currencyPage.toList();
    }

    @Override
    public Currency getCurrency(int id) {
        return currencyRepository.findById(id).orElseThrow(NoSuchCurrencyException::new);
    }
}

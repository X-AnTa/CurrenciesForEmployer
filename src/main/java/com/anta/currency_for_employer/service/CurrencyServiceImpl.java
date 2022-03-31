package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.Currency;
import com.anta.currency_for_employer.repository.CurrencyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Boolean updateCurrencies() {
        try {
            Currency currency = new Currency();
            String url = "http://www.cbr.ru/scripts/XML_daily.asp";
            Document page = null;
            try {
                page = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 34; i++) {
                Element name = page.select("Name").get(i);
                Element value = page.select("Value").get(i);
                Element nominal = page.select("Nominal").get(i);
                Element charCode = page.select("CharCode").get(i);
                currency.setName(String.valueOf(name.textNodes().get(0)));
                currency.setRate(nominal.textNodes().get(0) + " " + charCode.textNodes().get(0)+ " к "+ value.textNodes().get(0) +" RUB");
                currency.setId(i + 1);
                System.out.println(currency);
                currencyRepository.save(currency);
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrency(int id) {
        return currencyRepository.findById(id).orElseThrow(NoSuchCurrencyException::new);
    }
}

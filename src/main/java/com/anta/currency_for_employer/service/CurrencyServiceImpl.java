package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.dto.ValCursDto;
import com.anta.currency_for_employer.dto.ValuteDto;
import com.anta.currency_for_employer.entity.Currency;
import com.anta.currency_for_employer.exception.NoSuchCurrencyException;
import com.anta.currency_for_employer.repository.CurrencyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final static String url = "http://www.cbr.ru/scripts/XML_daily.asp";
    private final static File file = new File("src/main/resources/data.xml");


    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    @Override
    public Boolean updateCurrencies() {
        try {
            InputStream inputStream = new URL(url).openStream();
            Files.copy(inputStream, Paths.get("src/main/resources/data.xml"), StandardCopyOption.REPLACE_EXISTING);

            JAXBContext jaxbContext = JAXBContext.newInstance(ValCursDto.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ValCursDto valCursDto = (ValCursDto) jaxbUnmarshaller.unmarshal(file);

            List<ValuteDto> valuteDtos = valCursDto.getValuteDtoList();

            for (ValuteDto valuteDto : valuteDtos) {
                Currency byName = currencyRepository.findByName(valuteDto.getName());
                Currency currency = new Currency();
                if (byName != null) {
                    currency.setId(byName.getId());
                }
                currency.setName(valuteDto.getName());
                currency.setRate(valuteDto.getNominal() + " " + valuteDto.getCharCode() + " к " + valuteDto.getValue() + " RUB");

                currencyRepository.save(currency);
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Currency> getAllCurrencies(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").ascending());
        Page<Currency> currencyPage = currencyRepository.findAll(paging);
        return currencyPage.toList();
    }

    @Override
    public Currency getCurrency(int id) {
        return currencyRepository.findById(id).orElseThrow(NoSuchCurrencyException::new);
    }
}

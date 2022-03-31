package com.anta.currency_for_employer.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Currency not_found")
public class NoSuchCurrencyException extends RuntimeException{
}

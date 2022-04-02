package com.anta.currency_for_employer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "This login is already registered in the system")
public class ThisLoginIsAlreadyRegisteredInTheSystemException extends RuntimeException{

}

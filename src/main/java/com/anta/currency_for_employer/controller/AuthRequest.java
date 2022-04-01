package com.anta.currency_for_employer.controller;

import lombok.Data;

@Data
public class AuthRequest {

    private String login;
    private String password;
}

package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.User;

public interface UserService {

    User saveUser(User user);

    User findByLoginAndPassword(String login, String password);
}

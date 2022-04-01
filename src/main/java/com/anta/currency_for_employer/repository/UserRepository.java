package com.anta.currency_for_employer.repository;

import com.anta.currency_for_employer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}

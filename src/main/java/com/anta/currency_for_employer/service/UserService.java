package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.Role;
import com.anta.currency_for_employer.entity.User;
import com.anta.currency_for_employer.repository.RoleRepository;
import com.anta.currency_for_employer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User saveUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");

        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}

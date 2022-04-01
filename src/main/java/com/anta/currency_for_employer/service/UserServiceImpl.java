package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.Role;
import com.anta.currency_for_employer.entity.User;
import com.anta.currency_for_employer.repository.RoleRepository;
import com.anta.currency_for_employer.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");

        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login);

        if (user != null){
            if (passwordEncoder.matches(password, user.getPassword())){
                return user;
            }
        }
        return null;
    }
}

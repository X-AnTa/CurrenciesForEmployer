package com.anta.currency_for_employer.service;

import com.anta.currency_for_employer.entity.Role;
import com.anta.currency_for_employer.entity.User;
import com.anta.currency_for_employer.exception.ThisLoginIsAlreadyRegisteredInTheSystemException;
import com.anta.currency_for_employer.repository.RoleRepository;
import com.anta.currency_for_employer.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Add new User
     * @param user login and password
     */
    public void saveUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRepository.findByLogin(user.getLogin()) == null) {
            user.setRole(userRole);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else
            throw new ThisLoginIsAlreadyRegisteredInTheSystemException();
    }

    /**
     * Find user by login and password
     * @param login user login
     * @param password user password
     * @return found user or null
     */
    public User findByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Find user by login
     * @param login user login
     * @return found user
     */
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}

package com.bugztracker.service.impl;

import com.bugztracker.commons.entity.user.User;
import com.bugztracker.persistence.dao.IUserRepository;
import com.bugztracker.service.IUserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * Author: Yuliia Vovk
 * Date: 19.02.16
 * Time: 16:27
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<User> find(String email) {
        return Optional.ofNullable(userRepository.find(email));
    }

    @Override
    public void update(User user) {
        try {
            userRepository.update(user);
        } catch (ConstraintViolationException cve) {
            //TODO log
            System.out.printf("Updating user with id = %s failed, %s", user.getId(), cve.getMessage());
        }
    }

    @Override
    public void create(User user) {
        try {
            userRepository.add(user);
        } catch (ConstraintViolationException cve) {
            //TODO log
            System.out.printf("Creating user with email = %s failed, %s", user.getEmail(), cve.getMessage());
        }
    }

    @Override
    public Optional<User> getByRegistrationToken(String token) {
        return Optional.ofNullable(userRepository.findUserByRegistrationToken(token));
    }

    @Override
    public List<User> getByProject(String projectName) {
        return userRepository.getByProject(projectName);
    }

    @Override
    public void removeUsersWithRegistrationDatePassed() {
        userRepository.removeUsersWithRegistrationDatePassed(DateTime.now().toDate());
    }

    @Override
    public Optional<User> get(String id) {
        return Optional.ofNullable(userRepository.get(id));
    }
}

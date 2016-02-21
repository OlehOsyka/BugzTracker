package com.bugztracker.service;

import com.bugztracker.commons.entity.user.User;

import java.util.List;
import java.util.Optional;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface IUserService extends IService<User> {

    Optional<User> find(String email);
    void update(User user);
    void create(User user);
    Optional<User> getByRegistrationToken(String token);
    List<User> getByProject(String projectName);
//    List<User> findAll(String query);
//    List<User> findByIds(List<String> ids);
//    Map<String, String> login(User user, LoginBean loginBean);
//    Map<String, String> register(User user);
//    List<Integer> getProjectsIdsOfUser(User user);
//    List<User> getUsersByProjectId(int id, String query);
//    Map<String, String> activateAccount(String registrationToken);
    void removeUsersWithRegistrationDatePassed();
}

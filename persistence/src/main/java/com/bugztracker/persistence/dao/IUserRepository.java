package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.user.User;

import java.util.Date;
import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface IUserRepository extends IBaseDao<User> {

    User find(String email);

    List<User> findById(List<String> ids);

    List<User> findByFullName(String regexp);

    List<User> findUsersByProjectName(String projectName, String query);

    User findUserByRegistrationToken(String registrationToken);

    void removeUsersWithRegistrationDatePassed(Date date);

    List<User> getByProject(String projectName);

}


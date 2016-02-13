package com.bugztracker.persistence.dao;


import com.bugztracker.commons.entity.user.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Y. Vovk on 17.09.15.
 */
public interface IUserRepository extends IBaseDao<User> {

    User find(String email);

    List<User> findById(List<String> ids);

    List<User> findByFullName(String regexp);

    List<User> getUsersByProjectName(String projectName, String query);

    User getUserByRegistrationToken(String registrationToken);

    void removeUsersWithRegistrationDatePassed(Date date);
}

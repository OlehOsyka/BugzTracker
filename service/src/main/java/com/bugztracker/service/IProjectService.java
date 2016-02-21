package com.bugztracker.service;

import com.bugztracker.commons.entity.user.User;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:14
 */
public interface IProjectService {

    List<String> getProjectNames(User user);
}

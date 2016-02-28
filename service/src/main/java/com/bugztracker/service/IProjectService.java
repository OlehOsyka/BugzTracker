package com.bugztracker.service;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:14
 */
public interface IProjectService {

    List<String> getProjectNames(String userEmail);
}

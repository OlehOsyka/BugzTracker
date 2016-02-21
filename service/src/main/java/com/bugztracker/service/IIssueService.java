package com.bugztracker.service;

import com.bugztracker.commons.entity.issue.Issue;
import com.bugztracker.commons.entity.user.User;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:33
 */
public interface IIssueService {

    int getCountOfIssuesByProjectAndStatus(String projectName, String status);
    List<Issue> getByProjectAndStatus(String projectName, String status);
    List<Issue> getByProjectAndUserAndStatus(String projectName, String status, User user);
    List<Issue> getByProject(String projectName);
}

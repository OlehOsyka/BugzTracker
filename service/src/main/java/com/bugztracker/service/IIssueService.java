package com.bugztracker.service;

import com.bugztracker.commons.bean.StatusPoint;
import com.bugztracker.commons.entity.issue.Issue;

import java.util.Date;
import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:33
 */
public interface IIssueService {

    int getCountOfIssuesByProjectAndStatus(String projectName, String status);
    List<Issue> getByProjectAndStatus(String projectName, String status);
    List<Issue> getByProjectAndUserAndStatus(String projectName, String status, String userEmail);
    List<Issue> getByProjectAndAssignedUser(String projectName, String userEmail);
    List<Issue> getByProject(String projectName);
    List<StatusPoint> getStatusPointInRange(String projectName, Date from, Date to);

}

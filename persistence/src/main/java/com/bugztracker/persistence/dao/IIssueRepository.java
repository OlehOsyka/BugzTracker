package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.issue.Issue;

import java.util.Date;
import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface IIssueRepository extends IBaseDao<Issue> {

    List<Issue> getByProject(String projectName);
    List<Issue> getByProjectAndStatus(String projectName, String status);
    List<Issue> getByProjectAndUserAndStatus(String projectName, String status, String assignedUserEmail);
    List<Issue> getByProjectAndAssignedUser(String projectName, String assignedUserEmail);
    int getCountByDateAndOpenedStatus(String projectName, Date date);
    int getCountByDateAndClosedStatus(String projectName, Date date);
}

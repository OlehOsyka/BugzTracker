package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.issue.Issue;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
public interface IIssueRepository extends IBaseDao<Issue> {

    List<Issue> getByProject(String projectName);

    List<Issue> getByProjectAndAssignedUser(String projectName, String assignedUserEmail);

}

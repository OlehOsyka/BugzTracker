package com.bugztracker.persistence.dao;

import com.bugztracker.commons.entity.issue.Issue;

import java.util.List;

/**
 * Created by Y. Vovk
 * Date: 16.10.15
 * Time: 1:20
 */
public interface IIssueRepository extends IBaseDao<Issue> {

    List<Issue> getByProject(String projectName);

    List<Issue> getByProjectAndAssignedUser(String projectName, String assignedUserEmail);

}

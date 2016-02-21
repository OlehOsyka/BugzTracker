package com.bugztracker.service.impl;

import com.bugztracker.commons.entity.issue.Issue;
import com.bugztracker.commons.entity.user.User;
import com.bugztracker.persistence.dao.IIssueRepository;
import com.bugztracker.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:43
 */
@Service
public class IssueService implements IIssueService {

    @Autowired
    private IIssueRepository issueRepository;

    @Override
    public int getCountOfIssuesByProjectAndStatus(String projectName, String status) {
        return getByProjectAndStatus(projectName, status).size();
    }

    @Override
    public List<Issue> getByProjectAndStatus(String projectName, String status) {
        List<Issue> issues = issueRepository.getByProject(projectName);
        issues.removeIf(issue -> !issue.getStatus().name().equals(status));
        return issues;
    }

    @Override
    public List<Issue> getByProjectAndUserAndStatus(String projectName, String status, User user) {
        List<Issue> issues = issueRepository.getByProjectAndAssignedUser(projectName, user.getEmail());
        issues.removeIf(issue -> !issue.getStatus().name().equals(status));
        return issues;
    }

    @Override
    public List<Issue> getByProject(String projectName) {
        return issueRepository.getByProject(projectName);
    }

}

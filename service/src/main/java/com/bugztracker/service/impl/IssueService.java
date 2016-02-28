package com.bugztracker.service.impl;

import com.bugztracker.commons.bean.StatusPoint;
import com.bugztracker.commons.entity.issue.Issue;
import com.bugztracker.persistence.dao.IIssueRepository;
import com.bugztracker.service.IIssueService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
       return issueRepository.getByProjectAndStatus(projectName, status);
    }

    @Override
    public List<Issue> getByProjectAndUserAndStatus(String projectName, String status, String userEmail) {
        return issueRepository.getByProjectAndUserAndStatus(projectName, status, userEmail);
    }

    @Override
    public List<Issue> getByProjectAndAssignedUser(String projectName, String userEmail) {
        return issueRepository.getByProjectAndAssignedUser(projectName, userEmail);
    }

    @Override
    public List<Issue> getByProject(String projectName) {
        return issueRepository.getByProject(projectName);
    }

    @Override
    public List<StatusPoint> getStatusPointInRange(String projectName, Date from, Date to) {
        DateTime dateTimeFrom = new DateTime(from);
        DateTime dateTimeTo = new DateTime(to);
        List<StatusPoint> statusPoints = new ArrayList<>();

        while(dateTimeFrom.isBefore(dateTimeTo)) {
            StatusPoint statusPoint = new StatusPoint(
                    issueRepository.getCountByDateAndOpenedStatus(dateTimeFrom.toDate()),
                    issueRepository.getCountByDateAndClosedStatus(dateTimeTo.toDate()),
                    dateTimeFrom.toDate());
            statusPoints.add(statusPoint);
            dateTimeFrom = dateTimeFrom.plusDays(1);
        }

        return statusPoints;
    }

}

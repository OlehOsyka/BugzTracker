package com.bugztracker.web.controllers;

import com.bugztracker.commons.bean.StatusPoint;
import com.bugztracker.commons.entity.issue.Issue;
import com.bugztracker.service.IIssueService;
import com.bugztracker.web.helpers.IssueManagerHelper;
import com.bugztracker.web.helpers.Response;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:49
 */
@Controller
public class IssueController {

    @Autowired
    private IIssueService issueService;

    @Autowired
    private IssueManagerHelper managerHelper;

    @RequestMapping(value = "/{projectName}/issues/count", method = RequestMethod.GET, params = {"status"})
    public int getCountOfTasksByStatus(@PathVariable String projectName, @RequestParam String status) {
        return issueService.getCountOfIssuesByProjectAndStatus(projectName, status);
    }

    @RequestMapping(value = "/{projectName}/issues/", method = RequestMethod.GET, params = {"status", "assignee_email"})
    public ResponseEntity getCountOfTasksByStatus(@PathVariable String projectName, @RequestParam String status,
                                                  @RequestParam("assignee_email") String assigneeEmail) {
        List<Issue> issueList = issueService.getByProjectAndUserAndStatus(projectName, status, assigneeEmail);

        Response response = managerHelper.getIssuesByStatusAndProjectAndUser(issueList, status);

        return new ResponseEntity<>(response.getResponse(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{projectName}/statuspoints", method = RequestMethod.GET)
    public ResponseEntity getStatusPointForDefaultTime(@PathVariable String projectName) {
        List<StatusPoint> statusPoints = issueService.getStatusPointInRange(projectName,
                DateTime.now().minusWeeks(2).toDate(), DateTime.now().toDate());

        Response response = managerHelper.getStatusPointsForRange(statusPoints);

        return new ResponseEntity<>(response.getResponse(), HttpStatus.OK);
    }

}

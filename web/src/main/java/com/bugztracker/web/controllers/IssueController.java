package com.bugztracker.web.controllers;

import com.bugztracker.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:49
 */
@Controller
public class IssueController {

    @Autowired
    private IIssueService issueService;

    @RequestMapping(value = "/{projectName}/issues/count", method = RequestMethod.GET, params = {"status"})
    public int getCountOfTasksByStatus(@PathVariable String projectName, @RequestParam String status) {
        return issueService.getCountOfIssuesByProjectAndStatus(projectName, status);
    }

}

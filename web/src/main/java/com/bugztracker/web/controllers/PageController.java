package com.bugztracker.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Yuliia Vovk
 * Date: 04.11.15
 * Time: 10:56
 */
@Controller
public class PageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSignInPage() {
        return "signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String getSignInPageTwo() {
        return "signin";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignUpPage() {
        return "signup";
    }

    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String getProjectPage() {
        return "project";
    }

    @RequestMapping(value = "/issue", method = RequestMethod.GET)
    public String getIssueDetailPage(){return "issue";}

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage(){return "main";}

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String getCodePage(){return "code";}

    @RequestMapping(value = "/issue/new", method = RequestMethod.GET)
    public String getNewIssuePage(){return "issue_new";}

}

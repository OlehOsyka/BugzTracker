package com.bugztracker.web.controllers;

import com.bugztracker.service.IProjectService;
import com.bugztracker.web.helpers.ProjectManagerHelper;
import com.bugztracker.web.helpers.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:19
 */
@Controller
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ProjectManagerHelper managerHelper;

    @RequestMapping(value = "/{userEmail}/projects", method = RequestMethod.GET)
    public ResponseEntity getProjectNames(@PathVariable String userEmail) {
        List<String> projectList = projectService.getProjectNames(userEmail);

        Response response = managerHelper.getProjectNames(projectList);

        return new ResponseEntity<>(response.getResponse(), HttpStatus.OK);
    }



}

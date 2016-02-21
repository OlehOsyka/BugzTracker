package com.bugztracker.web.controllers;

import com.bugztracker.commons.entity.user.User;
import com.bugztracker.service.IProjectService;
import com.bugztracker.web.helpers.ProjectManagerHelper;
import com.bugztracker.web.helpers.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static com.bugztracker.web.Constants.USER_SESSION;

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

    @RequestMapping(value = "/user/projects", method = RequestMethod.GET)
    public ResponseEntity getProjectNames(WebRequest request) {
        User user = (User) request.getAttribute(USER_SESSION, WebRequest.SCOPE_SESSION);
        List<String> projectList = projectService.getProjectNames(user);

        Response response = managerHelper.getProjectNames(projectList);

        return new ResponseEntity<>(response.getResponse(), HttpStatus.OK);
    }

}

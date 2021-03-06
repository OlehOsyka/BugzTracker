package com.bugztracker.web.helpers;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.bugztracker.web.Constants.*;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 12:21
 */
@Component
public class ProjectManagerHelper {

    public Response getProjectNames(List<String> projectNames) {
        Response response = new Response();

        if (projectNames.isEmpty()) {
            response.add(MESSAGE_PROJECT_NAMES, "No projects found");
        } else {
            response.add(PROJECT_NAMES, projectNames);
        }

        return response;
    }
}

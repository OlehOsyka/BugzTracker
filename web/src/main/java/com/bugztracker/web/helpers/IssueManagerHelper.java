package com.bugztracker.web.helpers;

import com.bugztracker.commons.bean.StatusPoint;
import com.bugztracker.commons.entity.issue.Issue;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bugztracker.web.Constants.*;

/**
 * Author: Yuliia Vovk
 * Date: 22.02.16
 * Time: 10:27
 */
@Component
public class IssueManagerHelper {

    public Response getIssuesByStatusAndProjectAndUser(List<Issue> issues, String status) {
        Response response = new Response();
        if (issues.isEmpty()) {
            response.add(MESSAGE_STATUS, "No tasks with status" + status + " found");
        } else {
            response.add(USER_STATUS, issues);
        }
        return response;
    }

    public Response getStatusPointsForRange(List<StatusPoint> statusPoints) {
        Response response = new Response();
        if (statusPoints.isEmpty()) {
            response.add(MESSAGE_STATUS_POINTS, "No tasks were opened/closed in this period");
        } else {
            response.add(STATUS_POINTS, statusPoints);
        }
        return response;
    }


}

package bugztracker.controller;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.service.IIssueService;
import bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * Created by Y. Vovk on 16.10.15.
 */
@Controller
@SessionAttributes("user")
public class IssueController {

    @Autowired
    private IIssueService issueService;

    @Autowired
    private IProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/project/{id}/issues", method = RequestMethod.GET, params = {"my"})
    public List<Issue> getAll(@PathVariable long id, @RequestParam boolean my, WebRequest request) {
       //
        Project proj = projectService.get(id);
        if (!my) {
            return issueService.getByProject(proj);
        }
        User user = (User) request.getAttribute("user", RequestAttributes.SCOPE_SESSION);
        return issueService.getByProjectAndUser(proj, user);
    }

}

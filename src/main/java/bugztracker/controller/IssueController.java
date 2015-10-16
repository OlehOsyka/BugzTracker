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
    @RequestMapping(value = "/issues/{id}", method = RequestMethod.GET)
    public List<Issue> getAll(@PathVariable long id, WebRequest request) {
        User user = (User) request.getAttribute("user", RequestAttributes.SCOPE_SESSION);
        Project proj = projectService.get(id);
        return issueService.getByProject(proj);
    }

}

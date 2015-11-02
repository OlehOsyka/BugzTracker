package bugztracker.controller;

import bugztracker.entity.Issue;
import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.service.IIssueService;
import bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * Created by Y. Vovk
 * Date: 16.10.15
 * Time: 13:06
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
    public List<Issue> getAll(@PathVariable int id, @RequestParam boolean my, WebRequest request) {
        Project proj = projectService.get(id);
        if (!my) {
            return issueService.getByProject(proj);
        }

        User user = (User) request.getAttribute("user", RequestAttributes.SCOPE_SESSION);
        return issueService.getByProjectAndUser(proj, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    public void add(@RequestBody Issue issue, WebRequest request) {
        //validator
        User user = (User) request.getAttribute("user", RequestAttributes.SCOPE_SESSION);

        issueService.addIssue(issue, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/issue/update", method = RequestMethod.POST)
    public void update(@RequestBody Issue issue) {
        //validator

        issueService.updateIssue(issue);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/issue/delete/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable int id) {
        Issue issue = issueService.get(id);
        issueService.delete(issue);
    }

    @ResponseBody
    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET)
    public Issue get(@PathVariable int id) {
        return issueService.get(id);
    }



}

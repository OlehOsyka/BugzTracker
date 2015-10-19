package bugztracker.controller;

import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.service.IProjectService;
import bugztracker.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Controller
@SessionAttributes("user")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = {"my"})
    public List<Project> getAll(@RequestParam boolean my, WebRequest request) {
        User user = (User) request.getAttribute("user", RequestAttributes.SCOPE_SESSION);
        if (!my) {
            return projectService.getAllWithParticipants();
        }
        List<Project> prs = projectService.getProjectsOfUser(user);
        return prs;
    }

    @ResponseBody
    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public Project get(@PathVariable int id) {
        return projectService.getWithUsers(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/project/update", method = RequestMethod.POST)
    public void update(@RequestBody Project project) {
       projectService.updateProject(project);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public void add(@RequestBody Project project) {
        projectService.addProject(project);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public Boolean isMyProject(@PathVariable int id, WebRequest request) {
        List<Long> projectIds = (List<Long>)
                request.getAttribute("userProjectIds", RequestAttributes.SCOPE_SESSION);
        return projectIds.contains(id);
    }

}

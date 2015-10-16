package bugztracker.controller;

import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Controller
@SessionAttributes("user")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = {"my"})
    public List<Project> getAll(@RequestParam boolean my, WebRequest request) {
        User user = (User) request.getAttribute("user", RequestAttributes.SCOPE_SESSION);
        if(!my) {
            return projectService.getAll();
        }
        List<Project> proks = projectService.getProjectsOfUser(user);
        return proks;
    }

    @ResponseBody
    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public Project get(@PathVariable long id) {
       return  projectService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/project/update", method = RequestMethod.POST)
    public void update(@RequestBody Project project) {
        Project proj = projectService.get(project.getId());
        proj.setDescription(project.getDescription());
        proj.setName(project.getName());

        projectService.update(proj);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public void add(@RequestBody Project project) {
        project.setId(UUID.randomUUID().getMostSignificantBits());
        project.setDate(new Date(System.currentTimeMillis()));

        projectService.add(project);
    }

}

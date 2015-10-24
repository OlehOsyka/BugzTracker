package bugztracker.controller;

import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.exception.ValidationException;
import bugztracker.service.IProjectService;
import bugztracker.service.IUserService;
import bugztracker.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Controller
@SessionAttributes("user")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IValidator<Project> projectValidator;

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = {"my"})
    public List<Project> getAll(@RequestParam boolean my, WebRequest request) {
        User user = (User) request.getAttribute("user", SCOPE_SESSION);
        if (!my) {
            return projectService.getAllWithParticipants();
        }
        return projectService.getProjectsOfUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public Project get(@PathVariable int id) {
        return projectService.getWithUsers(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/project/update", method = RequestMethod.POST)
    public void update(@RequestBody Project project, WebRequest request) {
        projectValidator.validate(project);
        User user = (User) request.getAttribute("user", SCOPE_SESSION);

        projectService.updateProject(project);

        List<Integer> prIds = (List<Integer>) request.getAttribute("userProjectIds", WebRequest.SCOPE_SESSION);
        //to refactor
        if ((project.getParticipants().contains(user) && !prIds.contains(project.getId()))
        || (!project.getParticipants().contains(user) && prIds.contains(project.getId()))) {
            user = userService.find(user.getEmail());
            List<Integer> projectIds = userService.getProjectsIdsOfUser(user);
            request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
            request.setAttribute("userProjectIds", projectIds, WebRequest.SCOPE_SESSION);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public void add(@RequestBody Project project, WebRequest request) {
        projectValidator.validate(project);

        User user = (User) request.getAttribute("user", SCOPE_SESSION);

        projectService.addProject(project, user);

        //to refactor
        List<Integer> prIds = (List<Integer>) request.getAttribute("userProjectIds", WebRequest.SCOPE_SESSION);
        if ((project.getParticipants().contains(user) && !prIds.contains(project.getId()))
                || (!project.getParticipants().contains(user) && prIds.contains(project.getId()))) {
            user = userService.find(user.getEmail());
            List<Integer> projectIds = userService.getProjectsIdsOfUser(user);
            request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
            request.setAttribute("userProjectIds", projectIds, WebRequest.SCOPE_SESSION);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public Boolean isMyProject(@PathVariable int id, WebRequest request) {
        List<Long> projectIds = (List<Long>) request.getAttribute("userProjectIds", SCOPE_SESSION);
        return projectIds.contains(id);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity handleException(ValidationException exc) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}

package bugztracker.controller;

import bugztracker.entity.Project;
import bugztracker.entity.User;
import bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Controller
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = {"order","name"})
    public List<Project> getProjects(@RequestParam String order, @RequestParam String name, WebRequest request) {
        User user = (User) request.getAttribute("user", RequestAttributes.SCOPE_SESSION);
        //List<Project>  projects = projectService.getProjectsOfUser(user);
        if(name.isEmpty()) {
            return projectService.getSortedList("name", order);
        }
        return projectService.getSortedList(name, order);
    }

    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = {"search"})
    public List<Project> search(@RequestParam String search) {
        return projectService.search(search);
    }

}

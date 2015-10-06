package bugztracker.controller;

import bugztracker.entity.Project;
import bugztracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Y. Vovk on 06.10.15.
 */
@Controller
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = "order")
    public List<Project> getProjects(@RequestParam String order) {
        return projectService.getAll();
    }

}
